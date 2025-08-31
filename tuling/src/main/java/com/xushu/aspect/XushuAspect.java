package com.xushu.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy
public class XushuAspect {

	@Pointcut("execution(public void com.xushu.service.UserService.test())")
	void pointcut() {
	}

	@Before("pointcut()")
	public void xushuBefore(JoinPoint joinPoint) {
		System.out.println("增强");
		Object target= joinPoint.getTarget();
	}

}
