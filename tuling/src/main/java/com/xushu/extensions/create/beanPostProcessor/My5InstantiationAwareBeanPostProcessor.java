package com.xushu.extensions.create.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 在设置属性之前，让任何 InstantiationAwareBeanPostProcessors 都有机会修改 Bean 的状态。
 * 例如，这可用于支持现场注入样式。
 */
@Component
public class My5InstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		System.out.println("5."+beanName+"属性注入前..返回false中断后续依赖注入");

		return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
	}
}
