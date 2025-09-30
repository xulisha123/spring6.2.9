package com.xushu.aot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.aot.ContextAotProcessor;
import org.springframework.context.support.GenericApplicationContext;

import java.nio.file.Path;

public class DemoContextAotProcessor extends ContextAotProcessor {

		AnnotationConfigApplicationContext context;

		public DemoContextAotProcessor(Class<?> application, Path rootPath) {
			this(application, rootPath.resolve("source"), rootPath.resolve("resource"), rootPath.resolve("class"));
		}

	    public DemoContextAotProcessor(Class<?> application, Path sourceOutput, Path resourceOutput, Path classOutput) {
			super(application, createSettings(sourceOutput, resourceOutput, classOutput, "com.example", "example"));
		}

		private static Settings createSettings(Path sourceOutput, Path resourceOutput,
				Path classOutput, String groupId, String artifactId) {
			return Settings.builder()
					.sourceOutput(sourceOutput)
					.resourceOutput(resourceOutput)
					.classOutput(classOutput)
					.artifactId(artifactId)
					.groupId(groupId)
					.build();
		}

		@Override
		protected GenericApplicationContext prepareApplicationContext(Class<?> application) {
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
			context.register(application);
			this.context = context;
			return context;
		}
	}


	@Configuration(proxyBeanMethods = false)
	 class SampleApplication {

		@Bean
		public String testBean() {
			return "Hello";
		}
	}

