package com.xushu.circulardependencies.closedemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xsls on 2019/5/29.
 */
@Component
public class InstanceB implements IInstanceB {


    @Autowired
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
        System.out.println("Hi~ I'm B, My A is:"+getInstanceA().getClass());
    }


}
