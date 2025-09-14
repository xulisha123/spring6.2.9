package com.xushu.circulardependencies.One;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by xsls
 *
 */

@Component
public class AService implements IAService {

	@Autowired
    private IBService bService;


	@Override
	public void say() {
		System.out.println("I'm A， My B is"+bService.toString());
	}

	public AService() throws InterruptedException {
		System.out.println("创建A");
	}


	@Override
    public String toString() {
        return "InstanceA{" +
                "instanceB=" + bService +
                '}';
    }



}
