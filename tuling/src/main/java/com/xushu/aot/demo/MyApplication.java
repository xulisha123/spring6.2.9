package com.xushu.aot.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods=false)
@ComponentScan
public class MyApplication {  
      
    @Bean
    public String greeting() {  
        return "Hello AOT!";  
    }  
}