package com.xushu.extensions.create.aware.environmemt;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("classpath:/application.properties")
public class MainStart {
	public static void main(String[] args) {

		// spring容器  ioc容器  spring上下文    创建bean   依赖注入
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(MainStart.class);

	}
}
