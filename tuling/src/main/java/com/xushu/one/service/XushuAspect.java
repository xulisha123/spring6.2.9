package com.xushu.one.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//@Aspect
//@Component
public class XushuAspect {

	@Pointcut("execution(* com.xushu.one.service.UserService.*(..))")
	void pointcut() {
	}

	@Before("pointcut()")
	public void xushuBefore(JoinPoint joinPoint) {
		// 日志记录  、方法耗时  ...
		System.out.println("增强");
		Object target= joinPoint.getTarget();
	}

}
