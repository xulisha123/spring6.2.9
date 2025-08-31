package com.xushu.extensions.create.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class My8BeanPostProcessor implements BeanPostProcessor {


	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("8."+beanName+"初始化后：bean已经完整可以单独管理");

		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
}
