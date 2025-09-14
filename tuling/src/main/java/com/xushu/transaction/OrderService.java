package com.xushu.transaction;


import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.concurrent.TimeoutException;

/**
 * @author 徐庶
 */
@Component
public class OrderService implements IOrderService{

	@Autowired
	JdbcTemplate jdbcTemplate;


	@Transactional
	public void add()  {
		jdbcTemplate.execute("INSERT INTO `test`.`user` ( `age`, `name`, `city`) VALUES ( 55, 'xushu666', 'BeiJin');");
		((IOrderService)AopContext.currentProxy()).query();
	}


	// 场景二  同类嵌套调用
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void query()   {

		jdbcTemplate.execute("SELECT * FROM	`test`.`user`");
	}
}
