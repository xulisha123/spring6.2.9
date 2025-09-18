package com.xushu.extensions.create.aware.beanFoctory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class MyBeanFoctoryAware implements BeanFactoryAware {

	BeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory=beanFactory;
	}

	public void addUserService() {
		beanFactory.getBean("userService");
	}
}
