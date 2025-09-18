package com.xushu.extensions.create.aware.environmemt;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

// 获取环境变量中的值  springboot 配置文件的数据
@Component
public class EnvironmentAwareService implements EnvironmentAware{

	@Override
	public void setEnvironment(Environment environment) {
		/*String property = environment.getProperty("xushu.name");
		System.out.println(property);*/

		String xushu = environment.getProperty("xushu.name", String.class);
		System.out.println(xushu);

	}
}