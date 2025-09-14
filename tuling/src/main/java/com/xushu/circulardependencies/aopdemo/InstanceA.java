package com.xushu.circulardependencies.aopdemo;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by xsls on 2019/5/29.
 *
 */
@Component
public class InstanceA implements IInstanceA,InitializingBean{

    @Autowired
    private IInstanceB instanceB;

    public IInstanceB getInstanceB() {
        return instanceB;
    }

    public void setInstanceB(IInstanceB instanceB) {
        this.instanceB = instanceB;
    }

	public InstanceA() {
		System.out.println("A创建");
	}

	@Override
	@Async   	//  AOP    初始化 另外一个bp     创建动态代理
	public void say() {
		System.out.println("I'm AA， My BB is"+instanceB.getClass());
	}



    @Override
    public String toString() {
        return "InstanceA{" +
                "instanceB=" + instanceB +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //  生命周期 初始化回调方法   todo ..
    }


}
