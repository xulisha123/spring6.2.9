package com.xushu.lessons.all.service;

import jakarta.annotation.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

public class XushuService  {
	@Autowired
	private  UserService userService;


	public XushuService() {
	}

	public void say() {
		System.out.println(userService);
	}
}
