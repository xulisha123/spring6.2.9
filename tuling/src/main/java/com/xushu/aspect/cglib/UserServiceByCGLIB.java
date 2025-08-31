/*
package com.xushu.aspect.cglib;


import com.xushu.service.UserService;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

*/
/**
 * @author 徐庶
 *//*

public class UserServiceByCGLIB extends UserService {

	MethodInterceptor interceptor;

	public void setInterceptor(MethodInterceptor interceptor) {
		this.interceptor = interceptor;
	}

	public void test() {
		MethodProxy methodProxy = MethodProxy.create(UserService.class, this.getClass(), "()V", "insert", "CGLIB$insert$0");
		try {
			interceptor.intercept(this,this.getClass().getMethod("test"),null,methodProxy);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}



}
*/
