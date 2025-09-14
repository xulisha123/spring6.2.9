package com.xushu.features.aot;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class MainClass {
	public static void main(String[] args) {
		RuntimeHints hints = new RuntimeHints();
		AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext();
		ioc.register(MainClass.class);

		// 通过这个方法，  加载ioc , 跟refresh有别是不会创建bean ， 只会注册beandefinition
		ioc.refreshForAotProcessing(hints);

	}
}
