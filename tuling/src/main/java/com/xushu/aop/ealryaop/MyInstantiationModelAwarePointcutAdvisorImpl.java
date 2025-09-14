package com.xushu.aop.ealryaop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;

public class MyInstantiationModelAwarePointcutAdvisorImpl extends AbstractPointcutAdvisor {
    private final Advice advice;
    private final Pointcut pointcut;
    
    public MyInstantiationModelAwarePointcutAdvisorImpl(Advice advice, Pointcut pointcut) {
        this.advice = advice;
        this.pointcut = pointcut;
    }
    
    @Override
    public Advice getAdvice() {
        return this.advice;
    }
    
    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }
}
