package com.xushu.extensions.create.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 *
 * 创建bean之前
 * postProcessBeforeInstantiation返回一个bean对象，如果返回了spring终止创建
 * 自己控制bean的创建
 */
@Component
public class My1InstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {


	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		// 如果执行控制某个bean 记得加上判断 if()

		System.out.println("1."+beanName+"实例化前.如果返回了对象会中断bean生命周期");
		// 初始化 AOP切面
		return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
	}

}
