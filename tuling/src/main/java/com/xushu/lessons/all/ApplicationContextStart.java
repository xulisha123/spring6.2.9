package com.xushu.lessons.all;

import com.xushu.lessons.all.service.UserService;
import com.xushu.lessons.all.service.XushuService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
@ComponentScan
public class ApplicationContextStart {
	public static void main(String[] args) {
		// 加载IOC容器（自动化）
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(ApplicationContextStart.class);

		UserService userService = (UserService) ctx.getBean("userService");


	}





}
