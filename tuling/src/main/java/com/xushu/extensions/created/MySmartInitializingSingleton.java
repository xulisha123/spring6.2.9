package com.xushu.extensions.created;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 在所有单例bean创建完后调用, 做初始化工作
 * 比如需要依赖创建完后的bean 进行一些初始化工作 ，比如：
 *
 * SpringBatch   getType
 */


// 1.有别于初始化回调， 他会在所有单例bean创建完后调用
// 2.有别与refreshedEvent事件监听，依赖小
// 3.仅仅BeanFactory就有可以完成调用的扩展点
@Component
public class MySmartInitializingSingleton implements SmartInitializingSingleton, ApplicationContextAware {

	ApplicationContext applicationContext;
	@Override
	public void afterSingletonsInstantiated() {
		//  applicationContext.getBeansOfType()


		System.out.println("所有bean创建完后调用..");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		applicationContext=applicationContext;
	}
}
