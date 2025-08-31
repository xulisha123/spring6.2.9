package com.xushu.service;


import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
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
 */
@Component
public class OrderService {

	//  1. 是不是bean  2. 有没有加@Transactional 3. 是不是private  4.是不是自己try
	@Autowired
	JdbcTemplate jdbcTemplate;


	// 异常抛出的RuntimeException同级别的异常
	@Transactional(rollbackFor = TimeoutException.class)
	public void add() throws TimeoutException {
		jdbcTemplate.execute("INSERT INTO `test`.`user` ( `age`, `name`, `city`) VALUES ( 55, 'xushu666', 'BeiJin');");
		//throw new TimeoutException("超时");

		((OrderService)AopContext.currentProxy()).query();
	}


	// 场景二  同类嵌套调用
	@Transactional(propagation = Propagation.NEVER)
	public void query()   {

		jdbcTemplate.execute("SELECT * FROM	`test`.`user`");
	}

	@Autowired
	DataSource dataSource;

	// 场景三， 多线程事务: 导致事务传播失效   分布式事务 2PC 3PC SAGA  XA    编程式事务
	@Transactional
	public void mainThread() throws Exception {
		// 拿到外层事务的connection
		ConnectionHolder connectionHolder = (ConnectionHolder)
				TransactionSynchronizationManager.getResource(dataSource);

		jdbcTemplate.execute("INSERT INTO `test`.`user` ( `age`, `name`, `city`) VALUES ( 18, 'xushu1', 'BeiJin1');");
		OrderService orderService = (OrderService) AopContext.currentProxy();
		Thread thread = new Thread(() -> {
			// 保存外层事务的connection
			TransactionSynchronizationManager.bindResource(dataSource,connectionHolder);
			// 将外层connection绑定到当前线程threadlocal
			orderService.childThread();
		});
		int a=1/0;
		thread.start();
		thread.join();

	}

	@Transactional
	public void childThread() {

		jdbcTemplate.execute("INSERT INTO `test`.`user` ( `age`, `name`, `city`) VALUES ( 66, 'xushu2', 'BeiJin2');");

		//int a=1/0;
	}
}
