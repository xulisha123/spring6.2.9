package com.xushu.extensions.created;

import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;

/**
 *  与SmartLifecycle接口不同，
 *  Lifecycle接口没有提供自动启动和停止的特性。
 *  需要显式调用start()和stop()方法来管理自身的生命周期。
 */
@Component
public class MyLifecycle2 implements Lifecycle {
	boolean isRunning;
	@Override
	public void start() {
		isRunning=true;
		System.out.println("2容器加载完毕，组件启动！");
	}

	@Override
	public void stop() {
		isRunning=false;
		System.out.println("2容器关闭，组件停止！");
	}

	// isRunning=false  调用 start      isRunning=true    调用stop
	@Override
	public boolean isRunning() {
		System.out.println("2组件是否运行判断");
		return isRunning;
	}
}
