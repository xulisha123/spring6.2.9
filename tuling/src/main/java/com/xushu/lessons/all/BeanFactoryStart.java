package com.xushu.lessons.all;

import com.xushu.lessons.all.service.UserService;
import com.xushu.lessons.all.service.XushuService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ComponentScan;

import java.util.Map;

public class BeanFactoryStart {
	public static void main(String[] args) {

		// 通过BeanFactory加载
		// IOC容器加载！=bean创建
		// IOC容器加载=配置读取、扫描、beanpostproceesor注册、bean创建
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 读取配置类
		AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);
		reader.register(MyConfig.class);

		// 解析配置类
		AnnotatedGenericBeanDefinition beanDefinition =(AnnotatedGenericBeanDefinition) beanFactory.getBeanDefinition("myConfig");
		// 判断是否有@ComponentScan  元数据   ConfigurationClassParser  ComponentScanParser
		if (beanDefinition.getMetadata().hasAnnotation(ComponentScan.class.getName())) {
			Map<String, Object> annotationAttributes = beanDefinition.getMetadata().getAnnotationAttributes(ComponentScan.class.getName());
			Object value = annotationAttributes.get("value");

			// if value==null 将当前ComponentScan所在的包当做是扫描包


			// 扫描
			ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanFactory);
			scanner.scan("com.xushu.lessons.all");
		}


		// 先获取--->没有获取到根据beanDefinition创建
		XushuService xushuService = beanFactory.getBean(XushuService.class);
		xushuService.say();
	}





}
