package com.xushu.features.backgroundInit.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class BatchBeanDefinitionRegister implements BeanDefinitionRegistryPostProcessor{


	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//		for (int i=0;i<10;i++){
//			RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(AService.class);
//			// spring6:
//			rootBeanDefinition.setBackgroundInit(true);
//			registry.registerBeanDefinition("AService"+i,rootBeanDefinition);
//		}
	}



	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//		AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) beanFactory.getBeanDefinition("");
//		beanDefinition.setBackgroundInit();

		new Thread(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			Object bean = beanFactory.getBean("aService");
			System.out.println("get:"+bean);
		}).start();
	}










}
