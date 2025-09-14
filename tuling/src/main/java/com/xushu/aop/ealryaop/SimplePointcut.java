package com.xushu.aop.ealryaop;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.reflect.Method;

public class SimplePointcut implements Pointcut {
    @Override
    public ClassFilter getClassFilter() {
        return ClassFilter.TRUE; // 匹配所有类
    }
    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
				// 切点表达式匹配  Aspectj框架能力
                return true;
            }
            
            @Override
            public boolean isRuntime() {
                return false;
            }
            
            @Override
            public boolean matches(Method method, Class<?> targetClass, Object... args) {
                return matches(method, targetClass);
            }
        };
    }
}
