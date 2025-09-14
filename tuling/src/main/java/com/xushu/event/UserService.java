package com.xushu.event;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserService{
	@Autowired
	private ApplicationEventPublisher publisher;

	public void registry(User user)
	{
		System.out.println("用户注册成功：" + user);
		publisher.publishEvent(new UserRegistryEvent(user));
		System.out.println("返回");
	}



}
