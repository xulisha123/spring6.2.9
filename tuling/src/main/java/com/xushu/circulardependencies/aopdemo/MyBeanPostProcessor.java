package com.xushu.circulardependencies.aopdemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 循环依赖，初始化后改变了对象 报错
 * BeanCurrentlyInCreationException
 */
//@Component
public class MyBeanPostProcessor implements BeanPostProcessor{


	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof InstanceA){
			return new InstanceA();
		}
		return bean;
	}


}
