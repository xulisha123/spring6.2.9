package com.xushu.iocbeanlifecicle;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xsls
 * InitializingBean 初始化生命周期回调
 */
@Component
public  class Car implements FactoryBean<Tank> {

	@Autowired
	private Tank tank;

	public Car() {
		System.out.println("car加载....");
	}


	@Override
	public Tank getObject() throws Exception {
		System.out.println("FactoryBean");
		return new Tank();
	}

	@Override
	public Class<?> getObjectType() {
		return Tank.class;
	}
}
