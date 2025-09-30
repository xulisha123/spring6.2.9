package com.xushu.two;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;


/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:IOC根容器,不扫描Controller的注解
* @author: xsls
* @createDate: 2019/7/31 20:20
* @version: 1.0
*/
@Configuration
@ComponentScan(basePackages = "com.xushu",excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION,value={Controller.class}),
		@ComponentScan.Filter(type = ASSIGNABLE_TYPE,value =WebConfig.class ),
})
public class RootConfig {

}
