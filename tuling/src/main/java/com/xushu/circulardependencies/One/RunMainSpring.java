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

			  ioc.getBean("AService");

//		 AService aService1 = new AService();
//		 BService bService1 = new BService();
		// a.setb(b)
		// b.seta(a)

	}


}
