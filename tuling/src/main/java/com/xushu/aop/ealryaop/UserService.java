package com.xushu.aop.ealryaop;

public class UserService implements IUserService {

	public String addUser(String name)
	{
		System.out.println("添加用户："+name);
		return "addUser"+name;
	}
}
