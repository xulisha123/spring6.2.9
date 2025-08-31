package com.xushu.extensions.create.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class My7BeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("7."+beanName+"初始化前");

		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}
}
