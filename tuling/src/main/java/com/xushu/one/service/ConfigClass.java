package com.xushu.one.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@ComponentScan
@Configuration	// spring.xml
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableTransactionManagement
public class ConfigClass {



	@Bean
	public DataSource dataSource(){
		DataSource dataSource=new DriverManagerDataSource(
				"jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=UTF-8","root","123456"
		);
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public TransactionManager transactionManager(DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
	}


	@Bean
	public TransactionTemplate transactionTemplate(TransactionManager transactionManager){
		return new TransactionTemplate((PlatformTransactionManager) transactionManager);
	}

}
