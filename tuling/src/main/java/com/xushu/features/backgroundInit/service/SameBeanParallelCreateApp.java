package com.xushu.features.backgroundInit.service;


import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.*;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SameBeanParallelCreateApp {
	public static void main(String[] args) {
	 	AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SameBeanParallelCreateApp.class);

		new Thread(() -> {
			System.out.println(ioc.getBean("aService9"));
		}).start();
		new Thread(() -> {
			System.out.println(ioc.getBean("aService9"));
		}).start();

	}


 }