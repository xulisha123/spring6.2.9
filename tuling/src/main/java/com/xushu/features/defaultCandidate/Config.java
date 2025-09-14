package com.xushu.features.defaultCandidate;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean(defaultCandidate = false)
	public XushuService xushuService1(){
		return new XushuService();
	}
}
