package com.xushu.circulardependencies.One;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by xsls
 */
@Component
public class BService implements IBService{


	@Autowired
    private IAService aService;

	@Lazy
	public BService(IAService aService) {
		this.aService = aService;
	}

	// 默认
	/*public BService() {
        System.out.println("创建B");
    }*/


	@Override
    public void say() {
        System.out.println("Hi~ I'm B, My A is:"+aService.getClass());
    }

}
