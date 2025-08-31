package com.xushu.extensions.create.lifecicle;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;


public class Destory implements DisposableBean {
	@Override
	public void destroy() throws Exception {

	}

	@PreDestroy
	public void destroy2() throws Exception {

	}

	public void destroy3() throws Exception {

	}

}
