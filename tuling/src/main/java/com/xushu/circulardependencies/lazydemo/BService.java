package com.xushu.circulardependencies.lazydemo;


import org.springframework.stereotype.Component;

/**
 * Created by xsls on 2019/5/29.
 */
@Component
public class BService implements IBService {


    private  final IAService instanceA;


    public IAService getInstanceA() {
        return instanceA;
    }

    //@Lazy //可以解决构造函数的循环依赖
    public BService(AService instanceA) {
        System.out.println("BBBBB");
        this.instanceA = instanceA;
    }


    @Override
    public void say() {
        System.out.println("Hi~ I'm B, My A is:"+getInstanceA().getClass());
    }
}
