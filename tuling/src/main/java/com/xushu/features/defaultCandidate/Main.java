package com.xushu.features.defaultCandidate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Main {


	public static void main(String[] args) {
		AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(Main.class);

		UserService bean = ioc.getBean(UserService.class);
		System.out.println(bean.getXushuService());
	}
}
