package com.xushu.extensions.create.lifecicle;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


// 初始化  为依赖注入后的属性方法调用的初始化动作
public class Init implements InitializingBean  {


	// @Autowired
	// 线程池

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(2);
		// ....
	}

	@PostConstruct
	public void afterPropertiesSet2() throws Exception {

		System.out.println(1);
	}


	public void afterPropertiesSet3() throws Exception {

		System.out.println(3);
	}


}
