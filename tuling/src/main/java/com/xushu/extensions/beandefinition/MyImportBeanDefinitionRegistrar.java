package com.xushu.extensions.beandefinition;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, BeanFactoryAware {


	BeanFactory beanFactory;


	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		System.out.println("====================");
		System.out.println(beanFactory.getBean(XushuService.class));

		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(XushuService.class);
		BeanDefinition beanDefinition = builder.getBeanDefinition();

		//RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(XushuService.class);

		registry.registerBeanDefinition("xushuService4", beanDefinition);


		// 获取所在注解的信息
		System.out.println(importingClassMetadata.getAnnotationAttributes(ComponentScan.class.getName()));
	}


	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory=beanFactory;
	}
}
