package com.xushu.iocbeanlifecicle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by xsls
 */
public class MainStarter {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(MainConfig.class);
		System.out.println("容器加载完成");
		Object bean = ioc.getBean("car");
		System.out.println(bean.getClass());

	}


}
