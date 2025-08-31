package com.xushu.extensions.beandefinition;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
@Import(MyImportBeanDefinitionRegistrar.class)
public class MainStart {
	public static void main(String[] args) {

		// spring容器  ioc容器  spring上下文    创建bean   依赖注入
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(MainStart.class);
		System.out.println(context.getBeansOfType(XushuService.class));
	}

	@Bean
	public XushuService xushuService2(){
		return new XushuService();
	}

}
