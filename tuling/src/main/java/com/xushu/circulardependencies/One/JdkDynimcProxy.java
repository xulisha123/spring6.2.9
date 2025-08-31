package com.xushu.circulardependencies.One;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xsls
 */
public class JdkDynimcProxy implements InvocationHandler {

	private Object target;

	public JdkDynimcProxy(Object target) {
		this.target = target;
	}

	public <T> T getProxy() {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				this);
	}

	//  XushuService.add();   //创建动态代理
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		System.out.println("proxy增强");
		return method.invoke(target,args);
		// 后置
	}
}
