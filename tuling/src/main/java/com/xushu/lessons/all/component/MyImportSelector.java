package com.xushu.lessons.all.component;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		// beanName: 字符串  beanClass:完整限定名
		return new String[]{"com.xushu.lessons.all.service.XushuService"};
	}
}
