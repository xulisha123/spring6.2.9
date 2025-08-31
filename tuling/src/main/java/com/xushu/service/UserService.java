package com.xushu.service;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

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