package com.xushu.extensions.create.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class My1InstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {


	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		System.out.println("1."+beanName+"实例化前.如果返回了对象会中断bean生命周期");

		return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
	}
}
