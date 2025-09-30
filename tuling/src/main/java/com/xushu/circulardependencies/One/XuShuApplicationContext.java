package com.xushu.circulardependencies.One;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class XuShuApplicationContext  {


    private Map<String, BeanDefinition> beanDefinitionMap = new LinkedHashMap <>(256);

	// 单例池 一级缓存
	private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

	// 二级缓存  提升性能的
	private final Map<String, Object> earyObjects = new ConcurrentHashMap<>(256);

	// 三级缓存  为什么要存函数式接口  --->  方便回调（判断是循环依赖）
	//  提升扩展性 --->BeanPostProcessor(扩展） 在初始化进行更改bean的动作
	private final Map<String, ObjectFactory> factoriesObjects = new ConcurrentHashMap<>(256);




	// 并没有传配置类
	public XuShuApplicationContext() throws Exception {

        // 加载ioc容器
        refresh();
    }

	// ioc容器加载
    private void refresh() throws Exception {

		// 读取BeanDefinition
        loadBeanDefinitions();

		// 创建一个个bean
		finishBeanFactoryInitialization();

    }

	private void finishBeanFactoryInitialization() throws Exception {
		Set<String> strings = beanDefinitionMap.keySet();

		for (String beanName : strings) {

			RootBeanDefinition bd = (RootBeanDefinition) beanDefinitionMap.get(beanName);
			if(bd.isSingleton() && !bd.isLazyInit()) {
				getBean(beanName);
			}
		}
	}


	// 获取/ 创建bean
	public Object getBean(String beanName) throws Exception {


		Object bean = getSingleton(beanName);
		if (bean != null) {
			return bean;
		}

		synchronized (singletonObjects) {	// 1.  0不能
			bean = getSingleton(beanName);
			if (bean != null) {
				return bean;
			}

			// 1.实例化
			RootBeanDefinition bd = (RootBeanDefinition) beanDefinitionMap.get(beanName);
			Class<?> beanClass = bd.getBeanClass();
			Object beanIntance = beanClass.getConstructor().newInstance();

			// 所有的bean  示例化后创建aop动态
			// 判断  只有在循环依赖的时候
			// 出口   第三级缓存
			factoriesObjects.put(beanName, () -> {
				//  aop 动态代理

				return new JdkProxyBeanPostProcessor()
						.getEarlyBeanReference(beanIntance, beanName);

			});
			// earyObjects.put(beanName, aopBean);

			// 2.@autowired byname
			Field[] declaredFields = beanClass.getDeclaredFields();
			for (Field declaredField : declaredFields) {
				if (declaredField.isAnnotationPresent(Autowired.class)) {
					// 获取属性名 bService  byname
					String fieldName = declaredField.getName();
					// 获取依赖的bean对象
					Object dependBean = getBean(fieldName);
					// 给属性赋值
					declaredField.setAccessible(true);
					declaredField.set(beanIntance, dependBean);
				}
			}

			// 初始化
			if (beanIntance instanceof InitializingBean) {
				((InitializingBean) beanIntance).afterPropertiesSet();
			}

			singletonObjects.put(beanName, beanIntance);
			earyObjects.remove(beanName);

			return beanIntance;
		}
	}

	private Object getSingleton(String beanName) {

		if(singletonObjects.containsKey(beanName)){
			return  singletonObjects.get(beanName);
		}

		synchronized (singletonObjects) {
			// 二级缓存
			if (earyObjects.containsKey(beanName)) {
				return earyObjects.get(beanName);
			}

			if(factoriesObjects.containsKey(beanName)){
				// 就说明当前是循环依赖
				ObjectFactory objectFactory = factoriesObjects.get(beanName);
				Object aopBean = objectFactory.getObject();
				// 存入二级缓存
				earyObjects.put(beanName,aopBean);
				return aopBean;
			}
		}
		return null;
	}


	/*
	 *
	 *  根据配置类  解析@ComponentScan   发现类上面有@Component
	 *  BeanDefinition  底层是通过解析配置类注册beandefinition
	 */
	private void loadBeanDefinitions() {
		// 创建A    BeanDefinition
		RootBeanDefinition aBeanDefinition = new RootBeanDefinition(AService.class);
		// 创建B    BeanDefinition
		RootBeanDefinition bBeanDefinition = new RootBeanDefinition(BService.class);
		beanDefinitionMap.put("aService", aBeanDefinition);
		beanDefinitionMap.put("bService", bBeanDefinition);
	}


}
