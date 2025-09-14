package com.xushu.lessons.all.noOrHaveConfiguration;

import com.xushu.lessons.all.component.MyImportSelector;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

public class ApplicationContextStart {
	public static void main(String[] args) {
		// 加载IOC容器（自动化）
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(MyConfig.class);


		AService aService = ctx.getBean(AService.class);
		aService.say();

	}





}
