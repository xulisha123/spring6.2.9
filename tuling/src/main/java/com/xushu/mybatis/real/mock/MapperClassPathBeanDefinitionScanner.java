package com.xushu.mybatis.real.mock;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;

import java.util.Set;

public class MapperClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {
	public MapperClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
		super(registry);
		// 任何类都放行， 满足第一个isCandidateComponent
		super.addIncludeFilter((metadataReader, metadataReaderFactory) -> true);
	}

	// 第二个isCandidateComponent， 放行接口
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return  beanDefinition.getMetadata().isInterface();
	}

	@Override
	protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
		// 获取扫描到的mapper的BeanDefinitionHolder
		Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
		for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
			ScannedGenericBeanDefinition beanDefinition =(ScannedGenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();

			// 获取mapper接口类型
			String beanClassName = beanDefinition.getBeanClassName();
			beanDefinition.setBeanClass(MyFactoryBean.class);
			// 将mapper接口进行构造函数注入
			beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
		}


		return beanDefinitionHolders;
	}
}
