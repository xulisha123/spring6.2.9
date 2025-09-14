package com.xushu.circulardependencies.aopdemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by xsls on 2019/5/29.
 */
@Component
public class InstanceB implements IInstanceB {


    @Autowired
	@Lazy
    private IInstanceA instanceA;

    public void setInstanceA(IInstanceA instanceA) {
        this.instanceA = instanceA;
    }

    public IInstanceA getInstanceA() {
        return instanceA;
    }

    public InstanceB() {
        System.out.println("创建B");
    }


	@Override
	public void say() {
        System.out.println("I'm B, My A is:"+getInstanceA().getClass());
    }


}
