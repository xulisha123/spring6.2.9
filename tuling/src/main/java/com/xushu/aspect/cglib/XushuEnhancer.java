package com.xushu.aspect.cglib;


import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class XushuEnhancer {

	Class   superClass;

	MethodInterceptor callBack;

	public Class getSuperClass() {
		return superClass;
	}

	public void setSuperClass(Class superClass) {
		this.superClass = superClass;
	}

	public MethodInterceptor getCallBack() {
		return callBack;
	}

	public void setCallBack(MethodInterceptor callBack) {
		this.callBack = callBack;
	}

	public Object create(){
		try {

			Object o = superClass.newInstance();

			Method setInterceptor = superClass.getMethod("setInterceptor");
			setInterceptor.invoke(o,callBack);
			return o;

		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
		return null;
    }
}
