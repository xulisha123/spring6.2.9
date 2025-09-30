package com.xushu.one.service;


import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author 徐庶
 *
 */
@Component
public class UserService implements BeanPostProcessor{


	@Autowired
	private OrderService orderService;
	@Autowired		// 不优雅  循环依赖
	private  UserService userService;

	// 无参=默认
	public UserService() {

		System.out.println("1");
	}

	public void say(){
		System.out.println(orderService.getClass());

		((UserService)AopContext.currentProxy()).say2();
		//userService.say2();
	}


	public void say2(){
		System.out.println(orderService.getClass());
	}


}