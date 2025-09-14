package com.xushu.aop.ealryaop;

import org.springframework.aop.AfterReturningAdvice;
import java.lang.reflect.Method;

public class CustomAfterAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, 
                             Object[] args, Object target) throws Throwable {
		// 调用后置通知代码
        System.out.printf("[后置Advice] 方法 %s 执行完成，返回值: %s\n",
           method.getName(), returnValue);
    }
}