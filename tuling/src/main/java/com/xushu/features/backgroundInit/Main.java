package com.xushu.features.backgroundInit;


import org.springframework.context.annotation.*;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(Main.class);

		Main main = (Main) ioc.getBean("xushu");
		System.out.println(main.getClass());
	}

}