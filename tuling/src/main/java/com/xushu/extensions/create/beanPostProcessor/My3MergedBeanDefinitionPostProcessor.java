package com.xushu.extensions.create.beanPostProcessor;

import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * 实例化后
 * 可以通过操作beanDefinition的PropertyValues后续进行自动装配
 */
@Component
public class My3MergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor {


	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {

		System.out.println("3."+beanName+"实例化后..为属性注入做准备，可以给beanDefinition指定注入的值");
	}
}
