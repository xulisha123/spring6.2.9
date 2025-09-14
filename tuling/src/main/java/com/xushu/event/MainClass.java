package com.xushu.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by 公众号：程序员徐庶
 */
public class MainClass {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(MainConfig.class);
//		ioc.addApplicationListener();      // 保证在refresh()之前添加
//		ioc.addBeanFactoryPostProcessor(); // 保证在refresh()之前添加

		// new AnnotationConfigApplicationContext(MainConfig.class);
		//ioc.register(MainConfig.class);
		//ioc.refresh();


		UserService userService = ioc.getBean(UserService.class);
		userService.registry(new User());

		ioc.start();
		ioc.stop();
		ioc.close();		// 销毁
	}
}
