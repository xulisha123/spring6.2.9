package com.xushu.one.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainRun {

	public static void main(String[] args) throws Exception {
		// spring容器  ioc容器
		AnnotationConfigApplicationContext ioc =
				new AnnotationConfigApplicationContext(ConfigClass.class);


		// 获取bean  创建
		OrderService service = (OrderService)ioc.getBean("orderService");

		service.mainThread();

		// 普通对象
		//1 .  没有被spring管理
		// 2. 不会进行依赖注入
		/*UserService userService2 = new UserService();
		userService2.test();*/		//



		// 实例化
		/*Class<?> beanClass = UserService.class;
		// 无参构造实例化
		Object bean = beanClass.getConstructor().newInstance();*/

		// 2.2 依赖注入
		/* for (Field declaredField : beanClass.getDeclaredFields()) {

			if (declaredField.getAnnotation(Autowired.class) != null) {

				//  bytype  byname
				declaredField.set(bean,getBean("orderService") );
			}
		}*/
		// 初始化
//		if(bean instanceof InitializingBean){
//			((InitializingBean)bean).afterPropertiesSet();
//		}*/
	}
}
