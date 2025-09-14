package com.xushu.aop.aopdemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by xsls on 2019/6/10.
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy(exposeProxy = true)   //才会启用AOP
// @EnableXXX   必定注册bean组件
public class MainConfig {


}
