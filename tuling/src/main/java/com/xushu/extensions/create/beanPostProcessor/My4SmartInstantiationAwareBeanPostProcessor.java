package com.xushu.extensions.create.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 实例化后..解决循环依赖时bean和初始化后的bean不一致
 */
@Component
public class My4SmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {


	@Override
	public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {


		System.out.println("4."+beanName+"实例化后..解决循环依赖时bean和初始化后的bean不一致");


		return SmartInstantiationAwareBeanPostProcessor.super.getEarlyBeanReference(bean, beanName);
	}
}
