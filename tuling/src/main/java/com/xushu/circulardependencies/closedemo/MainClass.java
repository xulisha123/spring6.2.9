package com.xushu.circulardependencies.closedemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by xsls on 2019/5/29.
 */
@ComponentScan

public class MainClass {


    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ioc=
                new AnnotationConfigApplicationContext();
		ioc.setAllowCircularReferences(false);
		ioc.register(MainClass.class);
		ioc.refresh();

        // 获取bean getBean 获取
		IInstanceA instanceA = (IInstanceA) ioc.getBean("instanceA");
        System.out.println(instanceA.getClass());
		instanceA.say();
    }


}
