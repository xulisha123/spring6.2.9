package com.xushu.aop.ealryaop;

import org.springframework.aop.MethodBeforeAdvice;
import java.lang.reflect.Method;
import java.util.Arrays;

public class CustomBeforeAdvice implements MethodBeforeAdvice {
    
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        // 调用@Before的方法
		System.out.printf("[前置Advice] 方法 %s 即将执行，参数: %s\n",
            method.getName(), Arrays.toString(args));
    }
}
