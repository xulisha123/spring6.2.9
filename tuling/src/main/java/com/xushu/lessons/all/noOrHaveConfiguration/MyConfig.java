package com.xushu.lessons.all.noOrHaveConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


// 不加@Configuration
// 在@bean方法调用另外一个@Bean方法， 直接调用(不能保证单例性）
// 加@Configuration
// 在@bean方法调用另外一个@Bean方法， 从Spring容器中获取（动态代理 cglib)
@Configuration  // full 配置类
@ComponentScan
public class MyConfig {

	@Bean
	public BService bService() {
		return new BService();
	}
	@Bean
	public AService aService(){
		BService bService = bService();
		return new AService(bService());
	}


}
