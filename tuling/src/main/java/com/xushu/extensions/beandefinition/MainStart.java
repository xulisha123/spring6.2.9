package com.xushu.extensions.beandefinition;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;

import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.xushu.extensions.beandefinition")
@Import(MyImportBeanDefinitionRegistrar.class)
public class MainStart {
	public static void main(String[] args) {

		// spring容器  ioc容器  spring上下文    创建bean   依赖注入
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(MainStart.class);
		Map<String, XushuService> beans = context.getBeansOfType(XushuService.class);

		System.out.println(beans.keySet());

	}

	@Bean
	public XushuService xushuService2(){
		return new XushuService();
	}

}
