package com.xushu.extensions.beandefinition;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

public class XushuService  {
	String name;
	int age;


	@PostConstruct
	public void init(){
		System.out.println(name+"创建完成!");
	}

	public XushuService(String name) {
		this.name = name;
	}

	public XushuService() {
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "XushuService{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
