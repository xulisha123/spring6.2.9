package com.xushu.circulardependencies.One;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
@ComponentScan
@Configuration
public class RunMainSpring {


    public static void main(String[] args) {

		// ioc容器 spring容器
		AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(RunMainSpring.class);
		//ioc.setAllowCircularReferences(false);	// 设置是否开启循环依赖
		IAService aService = (IAService) ioc.getBean("AService");

	}


}
