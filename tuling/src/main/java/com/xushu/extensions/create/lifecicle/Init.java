package com.xushu.extensions.create.lifecicle;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


// 初始化   如果要做初始化
@Component
public class Init implements InitializingBean  {



	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@PostConstruct
	public void afterPropertiesSet2() throws Exception {

	}

	public void afterPropertiesSet3() throws Exception {

	}


}
