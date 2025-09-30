/*
 * Copyright 2002-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.aot;

import java.util.function.Supplier;

import org.springframework.aot.generate.GenerationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.javapoet.ClassName;

/**
 * Process an {@link ApplicationContext} and its {@link BeanFactory} to generate
 * code that represents the state of the bean factory, as well as the necessary
 * hints that can be used at runtime in a constrained environment.
 *
 * @author Stephane Nicoll
 * @author Phillip Webb
 * @since 6.0
 */
public class ApplicationContextAotGenerator {

	/**
	 * Process the specified {@link GenericApplicationContext} ahead-of-time using
	 * the specified {@link GenerationContext}.
	 * <p>Return the {@link ClassName} of the {@link ApplicationContextInitializer}
	 * to use to restore an optimized state of the application context.
	 * @param applicationContext the non-refreshed application context to process
	 * @param generationContext the generation context to use
	 * @return the {@code ClassName} of the {@code ApplicationContextInitializer}
	 * entry point
	 */
	public ClassName processAheadOfTime(GenericApplicationContext applicationContext,
			GenerationContext generationContext) {

		return withCglibClassHandler(new CglibClassHandler(generationContext), () -> {
			// 执行AOT专用的应用上下文刷新，只创建Bean定义不实例化Bean
			// 同时将RuntimeHints收集到generationContext的RuntimeHints中
			applicationContext.refreshForAotProcessing(generationContext.getRuntimeHints());
			// 创建应用上下文初始化代码生成器，负责生成ApplicationContextInitializer类（AOT启动入口）
			// 这个生成器会创建用于在运行时重建应用上下文状态的Java源码
			ApplicationContextInitializationCodeGenerator codeGenerator =
					new ApplicationContextInitializationCodeGenerator(applicationContext, generationContext);
			DefaultListableBeanFactory beanFactory = applicationContext.getDefaultListableBeanFactory();

			// 创建Bean工厂初始化AOT贡献集合，收集所有AOT处理器的贡献
			// 然后将这些贡献应用到代码生成器中，生成Bean注册和初始化的代码
			new BeanFactoryInitializationAotContributions(beanFactory).applyTo(generationContext, codeGenerator);
			// 返回生成的ApplicationContextInitializer类的完整类名
			// 这个类名将用作AOT优化应用的入口点
			return codeGenerator.getGeneratedClass().getName();
		});
	}

	private <T> T withCglibClassHandler(CglibClassHandler cglibClassHandler, Supplier<T> task) {
		try {
			ReflectUtils.setLoadedClassHandler(cglibClassHandler::handleLoadedClass);
			ReflectUtils.setGeneratedClassHandler(cglibClassHandler::handleGeneratedClass);
			return task.get();
		}
		finally {
			ReflectUtils.setLoadedClassHandler(null);
			ReflectUtils.setGeneratedClassHandler(null);
		}
	}

}
