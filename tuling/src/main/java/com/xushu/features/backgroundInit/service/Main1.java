package com.xushu.features.backgroundInit.service;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan
public class Main1 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ioc =
				new AnnotationConfigApplicationContext(JavaConfig.class);

	}





}