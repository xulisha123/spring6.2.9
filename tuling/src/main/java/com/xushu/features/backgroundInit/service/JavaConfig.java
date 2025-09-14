package com.xushu.features.backgroundInit.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan
public class JavaConfig {

	// 1. 创建一个线程池（名字一定要叫：bootstrapExecutor）
	@Bean
	public Executor bootstrapExecutor(){
		return  Executors.newCachedThreadPool();
	}

	 //2. 设置异步创建的bean
		@Bean(bootstrap = Bean.Bootstrap.BACKGROUND)
		public AService aService() throws InterruptedException {
			return  new AService();
		}

}
