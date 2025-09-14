package com.xushu.lessons.all;

import com.xushu.extensions.create.aware.environmemt.Xushu;
import com.xushu.lessons.all.component.MyImportSelector;
import com.xushu.lessons.all.service.OrderService;
import com.xushu.lessons.all.service.UserService;
import com.xushu.lessons.all.service.XushuService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
@ComponentScan
@Import(MyImportSelector.class)
public class ApplicationContextStart {
	public static void main(String[] args) {
		// 加载IOC容器（自动化）
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(ApplicationContextStart.class);
		//ctx.addBeanFactoryPostProcessor();
		OrderService orderService = (OrderService) ctx.getBean("orderService2");


	}





}
