package com.xushu.features.backgroundInit.service;


import org.springframework.context.annotation.*;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main2 {
	public static void main(String[] args) {
		// 开始时间
		long beginTime = System.currentTimeMillis();
		AnnotationConfigApplicationContext ioc =
				new AnnotationConfigApplicationContext(JavaConfig.class);
		// 结束时间
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-beginTime);
	}


	// 1. 创建一个线程池（名字一定要叫：bootstrapExecutor）

	// 2. 设置异步创建的bean
//	@Bean(bootstrap = Bean.Bootstrap.BACKGROUND)
//	public AService aService9() throws InterruptedException {
//		return  new AService();
//	}



}