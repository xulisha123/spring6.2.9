package com.xushu.extensions.beandefinition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

@Component
public class MyBeanDefinitionRegistryPostProcessor
		implements BeanDefinitionRegistryPostProcessor {
	@Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {


		// 动态注册beanDefinition = 注册bean
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(XushuService.class);
		 AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();

		//RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(XushuService.class);

		// 动态注入属性 @Value
		beanDefinition.getPropertyValues().add("age",18);
		// 动态设置定义信息
		// beanDefinition.setLazyInit(true);
		// beanDefinition.setScope();
		// beanDefinition.setInitMethodName();

		//...

		// 动态设置构造函数
		beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0,"xushu");


        registry.registerBeanDefinition("xushuService3", beanDefinition);

    }

	// 修改beandefinition
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		// todo....
		// beanFactory.getBeanDefinition()// 修改
		//((DefaultListableBeanFactory)beanFactory).registerBeanDefinition();
	}

}