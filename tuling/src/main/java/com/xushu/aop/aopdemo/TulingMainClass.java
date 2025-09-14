package com.xushu.aop.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by xsls on 2019/6/10.
 */
public class TulingMainClass {

    public static void main(String[] args) {

    	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);

        Calculate calculate = (Calculate) ctx.getBean("tulingCalculate");
        System.out.println(calculate.getClass());
        int retVal = calculate.add(2,4);
    }

}
