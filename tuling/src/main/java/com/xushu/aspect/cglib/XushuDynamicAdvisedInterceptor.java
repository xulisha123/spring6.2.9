package com.xushu.aspect.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class XushuDynamicAdvisedInterceptor  implements MethodInterceptor {

	Object target;

	public XushuDynamicAdvisedInterceptor(Object target) {
		this.target = target;
	}

	/**
	 * @see org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor#intercept(Object, Method, Object[], org.springframework.cglib.proxy.MethodProxy)
	 */
	@Override
	public Object intercept(Object o, Method method, Object[] arges, MethodProxy methodProxy) throws Throwable {
		System.out.println("before....");
		Object value= methodProxy.invoke(target,arges);
		System.out.println("after....");
		return value;
	}
}
