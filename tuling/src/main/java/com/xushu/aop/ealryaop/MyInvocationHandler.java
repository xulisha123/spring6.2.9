package com.xushu.aop.ealryaop;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xsls
 */
public class MyInvocationHandler implements InvocationHandler {

	private Object target;	// 当前正在创建的bean
	private List<Advisor> canApply; // 匹配成功通知

	public MyInvocationHandler(UserService bean, List<Advisor> canApply) {
		this.target = bean;
		this.canApply = canApply;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 哪个方法method


		// 一个个调用通知  Interceptor 跟 通知一一对象（组织好了通知执行流程） 为了更好的运用责任链设计模式
		List<MethodInterceptor> interceptors = canApply.stream()
				.map(advisor -> {
					if (advisor.getAdvice() instanceof MethodBeforeAdvice) {
						return new MethodBeforeAdviceInterceptor((MethodBeforeAdvice) advisor.getAdvice());
					}
					if (advisor.getAdvice() instanceof AfterReturningAdvice) {
						return new AfterReturningAdviceInterceptor((AfterReturningAdvice) advisor.getAdvice());
					}
					return null;
				})
				.collect(Collectors.toList());

		// 责任链调度器
		MainStart.MyMethodInvocation invocation=new MainStart.MyMethodInvocation(interceptors,target,method,args);
		return invocation.proceed();
	}
}
