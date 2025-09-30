package com.xushu.aot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.javapoet.ClassName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class ContextAotProcessorTests {

	@Configuration(proxyBeanMethods = false)
	static class SampleApplication {

		@Bean
		public String testBean() {
			return "Hello";
		}
	}

	public static void main(String[] args) throws IOException {
		Path directory = Paths.get("./tuling/generated");
		GenericApplicationContext context = new AnnotationConfigApplicationContext();
		context.registerBean(SampleApplication.class);
		DemoContextAotProcessor processor = new DemoContextAotProcessor(SampleApplication.class, directory);
		ClassName className = processor.process();
		context.close();
	}

}