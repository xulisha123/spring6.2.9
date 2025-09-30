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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.aot.generate.ClassNameGenerator;
import org.springframework.aot.generate.DefaultGenerationContext;
import org.springframework.aot.generate.FileSystemGeneratedFiles;
import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.ReflectionHints;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.javapoet.ClassName;
import org.springframework.util.CollectionUtils;

/**
 * Filesystem-based ahead-of-time (AOT) processing base implementation.
 *
 * <p>Concrete implementations are typically used to kick off optimization of an
 * application in a build tool.
 *
 * @author Stephane Nicoll
 * @author Andy Wilkinson
 * @author Phillip Webb
 * @author Sam Brannen
 * @since 6.0
 * @see org.springframework.test.context.aot.TestAotProcessor
 */
public abstract class ContextAotProcessor extends AbstractAotProcessor<ClassName> {

	private final Class<?> applicationClass;


	/**
	 * Create a new processor for the specified application entry point and
	 * common settings.
	 * @param applicationClass the application entry point (class with a {@code main()} method)
	 * @param settings the settings to apply
	 */
	protected ContextAotProcessor(Class<?> applicationClass, Settings settings) {
		super(settings);
		this.applicationClass = applicationClass;
	}


	/**
	 * Get the application entry point (typically a class with a {@code main()} method).
	 */
	protected Class<?> getApplicationClass() {
		return this.applicationClass;
	}


	/**
	 * Invoke the processing by clearing output directories first, followed by
	 * {@link #performAotProcessing(GenericApplicationContext)}.
	 * @return the {@code ClassName} of the {@code ApplicationContextInitializer}
	 * entry point
	 */
	@Override
	protected ClassName doProcess() {
		deleteExistingOutput();
		try (GenericApplicationContext applicationContext = prepareApplicationContext(getApplicationClass())) {
			return performAotProcessing(applicationContext);
		}
	}

	/**
	 * Prepare the {@link GenericApplicationContext} for the specified
	 * application entry point to be used against an {@link ApplicationContextAotGenerator}.
	 * @return a non-refreshed {@link GenericApplicationContext}
	 */
	protected abstract GenericApplicationContext prepareApplicationContext(Class<?> applicationClass);

	/**
	 * Perform ahead-of-time processing of the specified context.
	 * <p>Code, resources, and generated classes are stored in the configured
	 * output directories. In addition, run-time hints are registered for the
	 * application and its entry point.
	 * @param applicationContext the context to process
	 *
	 * 这个方法是AOT处理的核心，它的执行流程如下：
	 * 1.准备生成环境 - 创建文件管理器和生成上下文
	 * 2.执行AOT生成 - 使用 ApplicationContextAotGenerator 处理应用上下文
	 * 3.注册反射提示 - 确保生成的类可以在原生镜像中正确访问
	 * 4.输出文件 - 写入生成的源码、配置文件和原生镜像属性
	 */
	protected ClassName performAotProcessing(GenericApplicationContext applicationContext) {
		// 创建文件系统生成文件管理器，用于管理生成的源码、资源和类文件的输出
		FileSystemGeneratedFiles generatedFiles = createFileSystemGeneratedFiles();
		// 创建默认的代码生成上下文，包含类名生成器和文件管理器
		// 这个上下文将贯穿整个AOT处理过程，用于协调代码生成
		DefaultGenerationContext generationContext = new DefaultGenerationContext(
				createClassNameGenerator(), generatedFiles);
		// 创建应用上下文AOT生成器，这是执行AOT处理的核心组件
		ApplicationContextAotGenerator generator = new ApplicationContextAotGenerator();
		// 执行AOT处理的核心步骤：
		// 1. 对应用上下文进行AOT刷新处理
		// 2. 生成ApplicationContextInitializer类的源码
		// 3. 返回生成的初始化器类名
		ClassName generatedInitializerClassName = generator.processAheadOfTime(applicationContext, generationContext);
		// 为生成的ApplicationContextInitializer入口点注册反射提示
		// 确保在原生镜像中可以正确访问这个类
		registerEntryPointHint(generationContext, generatedInitializerClassName);
		// 将所有生成的内容（Java源码、资源文件等）写入到文件系统
		generationContext.writeGeneratedContent();
		// 将收集到的运行时提示写入reflect-config.json等配置文件
		// 这些文件用于GraalVM原生镜像编译
		writeHints(generationContext.getRuntimeHints());
		// 生成native-image.properties文件，包含原生镜像编译的默认参数
		writeNativeImageProperties(getDefaultNativeImageArguments(getApplicationClass().getName()));
		return generatedInitializerClassName;
	}

	/**
	 * Callback to customize the {@link ClassNameGenerator}.
	 * <p>By default, a standard {@link ClassNameGenerator} using the configured
	 * {@linkplain #getApplicationClass() application entry point} as the default
	 * target is used.
	 * @return the class name generator
	 */
	protected ClassNameGenerator createClassNameGenerator() {
		return new ClassNameGenerator(ClassName.get(getApplicationClass()));
	}

	/**
	 * Return the native image arguments to use.
	 * <p>By default, the main class to use, as well as standard application flags
	 * are added.
	 * <p>If the returned list is empty, no {@code native-image.properties} is
	 * contributed.
	 * @param applicationClassName the fully qualified class name of the application
	 * entry point
	 * @return the native image options to contribute
	 */
	protected List<String> getDefaultNativeImageArguments(String applicationClassName) {
		List<String> args = new ArrayList<>();
		args.add("-H:Class=" + applicationClassName);
		args.add("--report-unsupported-elements-at-runtime");
		args.add("--no-fallback");
		args.add("--install-exit-handlers");
		return args;
	}

	private void registerEntryPointHint(DefaultGenerationContext generationContext,
			ClassName generatedInitializerClassName) {

		TypeReference generatedType = TypeReference.of(generatedInitializerClassName.canonicalName());
		TypeReference applicationType = TypeReference.of(getApplicationClass());
		ReflectionHints reflection = generationContext.getRuntimeHints().reflection();
		reflection.registerType(applicationType);
		reflection.registerType(generatedType, typeHint -> typeHint.onReachableType(applicationType)
				.withConstructor(Collections.emptyList(), ExecutableMode.INVOKE));
	}

	private void writeNativeImageProperties(List<String> args) {
		if (CollectionUtils.isEmpty(args)) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Args = ");
		sb.append(String.join(String.format(" \\%n"), args));
		Path file = getSettings().getResourceOutput().resolve("META-INF/native-image/" +
				getSettings().getGroupId() + "/" + getSettings().getArtifactId() + "/native-image.properties");
		try {
			if (!Files.exists(file)) {
				Files.createDirectories(file.getParent());
				Files.createFile(file);
			}
			Files.writeString(file, sb.toString());
		}
		catch (IOException ex) {
			throw new IllegalStateException("Failed to write native-image.properties", ex);
		}
	}

}
