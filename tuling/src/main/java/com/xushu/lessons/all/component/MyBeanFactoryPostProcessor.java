package com.xushu.lessons.all.component;

import com.xushu.lessons.all.service.OrderService;
import com.xushu.lessons.all.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	// 动态注册beanDefinition
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		registry.registerBeanDefinition("orderService2", new RootBeanDefinition(OrderService.class));

		// 解析配置类

		// 扫描包

		// registry注册beandefinition
	}

	// 所有beanDefinition注册完后调用
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) beanFactory.getBeanDefinition("orderService");

		// 依赖注入的属性
		//beanDefinition.getPropertyValues()
		// <property name="x" value="xx"/>

		beanDefinition.setBeanClass(UserService.class);
	}
}
