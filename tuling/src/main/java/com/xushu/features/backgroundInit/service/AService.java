package com.xushu.features.backgroundInit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


public class AService {
	@Autowired
	BService bService;

	public AService() throws InterruptedException {
		Thread.sleep(500000);
	}
}
