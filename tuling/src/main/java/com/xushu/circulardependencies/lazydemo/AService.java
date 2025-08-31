package com.xushu.circulardependencies.lazydemo;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by xsls on 2019/5/29.
 *
 */
@Component
public class AService implements IAService {

    private final IBService instanceB;

    public IBService getInstanceB() {
        return instanceB;
    }



    @Lazy
    public AService(BService instanceB) {
        System.out.println("AAAAAA");
        //instanceB.getInstanceA().say();
        this.instanceB = instanceB;
    }

    @Override
	public void say() {
		System.out.println("I'm A， My B is"+instanceB.getClass());
	}


    @Override
    public String toString() {
        return "InstanceA{" +
                "instanceB=" + instanceB +
                '}';
    }
}
