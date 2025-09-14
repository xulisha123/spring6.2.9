package com.xushu.transaction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainRun {

	public static void main(String[] args) throws Exception {
		// spring容器  ioc容器
		AnnotationConfigApplicationContext ioc =
				new AnnotationConfigApplicationContext(ConfigClass.class);


		// 获取bean  创建
		IOrderService service = (IOrderService)ioc.getBean("orderService");

		service.add();

	}
}
