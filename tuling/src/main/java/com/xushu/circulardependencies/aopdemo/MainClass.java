package com.xushu.circulardependencies.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by xsls on 2019/5/29.
 */
@ComponentScan
@EnableAspectJAutoProxy
@EnableAsync
public class MainClass {


    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ioc=
                new AnnotationConfigApplicationContext(MainClass.class);


        // 获取bean getBean 获取
		IInstanceA instanceA = (IInstanceA) ioc.getBean("instanceA");
        System.out.println(instanceA.getClass());
		instanceA.say();
    }


}
