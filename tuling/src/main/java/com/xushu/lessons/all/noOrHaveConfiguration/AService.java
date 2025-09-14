package com.xushu.lessons.all.noOrHaveConfiguration;

import org.springframework.stereotype.Component;

public class AService {

	BService bService;

	public AService(BService bService) {
		this.bService = bService;
	}

	public void say()
	{
		System.out.println(bService);
	}
}
