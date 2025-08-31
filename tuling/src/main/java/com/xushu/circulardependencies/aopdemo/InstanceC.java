package com.xushu.circulardependencies.aopdemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xsls on 2019/5/29.
 */
@Component
public class InstanceC implements IInstanceC {


    @Autowired
    private IInstanceA instanceA;

    public void setInstanceA(IInstanceA instanceA) {
        this.instanceA = instanceA;
    }

    public IInstanceA getInstanceA() {
        return instanceA;
    }

    public InstanceC() {
        System.out.println("创建C");
    }

    @Override
    public void say() {
        System.out.println("Hi~ I'm C, My A is:"+getInstanceA().getClass());
    }


}
