package com.xushu.aot.demo;

import com.xushu.aot.DemoContextAotProcessor;
import org.springframework.aot.AotDetector;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.test.tools.SourceFile;
import org.springframework.core.test.tools.SourceFiles;
import org.springframework.core.test.tools.TestCompiler;
import org.springframework.javapoet.ClassName;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CompleteAotExample {

	public static void main(String[] args) throws Exception {
		// 第一步：生成AOT文件
		generateAotFiles();

		// 第二步：编译生成的代码
		compileGeneratedCode();

		// 第三步：运行AOT优化的应用
		runAotApplication();
	}

	private static void generateAotFiles() throws Exception {


		Path buildDir = Paths.get("tuling/build/aot-generated");
		Path sourceOutput = buildDir.resolve("sources");
		Path resourceOutput = buildDir.resolve("resources");
		Path classOutput = buildDir.resolve("classes");
		// 创建AOT处理器
		DemoContextAotProcessor processor = new DemoContextAotProcessor(
				MyApplication.class, sourceOutput, resourceOutput, classOutput);

		// 执行AOT处理，生成文件
		ClassName generatedClassName = processor.process();
		System.out.println(generatedClassName);
	}

	private static void compileGeneratedCode() throws IOException {
		Path buildDir = Paths.get("tuling/build/aot-generated");
		Path sourceDir = buildDir.resolve("sources");
		Path classDir = buildDir.resolve("classes");

		// 创建输出目录
		Files.createDirectories(classDir);

		// 获取系统Java编译器
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

		// 收集所有.java文件
		List<File> javaFiles = Files.walk(sourceDir)
				.filter(path -> path.toString().endsWith(".java"))
				.map(Path::toFile)
				.collect(Collectors.toList());

		// 设置编译选项
		List<String> options = Arrays.asList(
				"-d", classDir.toString(),
				"-cp", System.getProperty("java.class.path")
		);

		// 执行编译
		Iterable<? extends JavaFileObject> compilationUnits =
				fileManager.getJavaFileObjectsFromFiles(javaFiles);

		boolean success = compiler.getTask(null, fileManager, null, options, null, compilationUnits).call();

		if (success) {
			System.out.println("Compilation successful!");
		} else {
			System.err.println("Compilation failed!");
		}

		fileManager.close();
	}

	private static void runAotApplication() throws Exception {
		try {
			// 启用AOT模式
			System.setProperty(AotDetector.AOT_ENABLED, "true");

			// 加载生成的ApplicationContextInitializer
			Class<?> initializerClass = Class.forName(
					MyApplication.class.getPackageName()+".MyApplication__ApplicationContextInitializer");
			ApplicationContextInitializer<GenericApplicationContext> initializer =
					(ApplicationContextInitializer<GenericApplicationContext>)
							initializerClass.getDeclaredConstructor().newInstance();

			// 使用AOT初始化器创建ApplicationContext
			GenericApplicationContext context = new GenericApplicationContext();
			initializer.initialize(context);
			context.refresh();

			// 验证Bean正常工作
			String greeting = context.getBean("greeting", String.class);
			System.out.println("AOT application running: " + greeting);

			context.close();
		} finally {
			System.clearProperty(AotDetector.AOT_ENABLED);
		}


	}
}