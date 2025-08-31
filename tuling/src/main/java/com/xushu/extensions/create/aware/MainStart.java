package com.xushu.extensions.create.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class MainStart {
	public static void main(String[] args) {

		// spring容器  ioc容器  spring上下文    创建bean   依赖注入
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(MainStart.class);

		System.out.println("容器已加载");
		System.out.println(context.getBean("userService"));
	}
}
