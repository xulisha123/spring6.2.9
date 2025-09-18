package com.xushu.mybatis.real.mock;

import com.xushu.mybatis.real.mock.annotations.XushuMapperScan;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.Map;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
         // 获取包
		Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(XushuMapperScan.class.getName());
		String basePackages = annotationAttributes.get("basePackages").toString();
		if(!StringUtils.hasText(basePackages)){
			throw new RuntimeException("请指定扫描包");
		}
		// 根据包扫描
		MapperClassPathBeanDefinitionScanner scanner = new MapperClassPathBeanDefinitionScanner(registry);
		scanner.scan(basePackages);
		// mapper接口就扫描进来了，mapper不能实例化的

		// 把扫描进的beanDefinition的BeanClass更换（偷天换日）
    }
}
