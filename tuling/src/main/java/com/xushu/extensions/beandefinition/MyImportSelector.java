package com.xushu.extensions.beandefinition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{
				"com.xushu.extensions.beandefinition.XushuService"
		};


	}
}
