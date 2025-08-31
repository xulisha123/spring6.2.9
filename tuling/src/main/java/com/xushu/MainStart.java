package com.xushu;

import com.xushu.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xsls on 2019/7/7.
 */
public class MainStart {

	public static void main(String[] args) {
		/*AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		Car car = (Car) ctx.getBean("car");
		System.out.println(car);*/
		/*System.out.println(ctx.getBean(TulingDao.class).getTulingDataSource().getFlag());*/


		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
		UserService bean = context.getBean(UserService.class);
	}

}
