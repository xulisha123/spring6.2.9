package com.xushu.extensions.created;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 *  控制一个组件的生命周期 ，比如定时器组件\资源预热\缓存预热
 *  容器启动完：   定时任务启动
 *  容器关闭：     定时任务停止
 *
 *  SmartLifecycle和Lifecycle 区别
 *
 *  SmartLifecycl启动start 会在finishRefreshed自动调用
 *  Lifecycle  需要手动执行容器context.start();
 */
@Component
public class MyLifecycle implements SmartLifecycle {
	boolean isRunning;
	@Override
	public void start() {
		isRunning=true;
		System.out.println("容器加载完毕，组件启动！");
	}

	@Override
	public void stop() {
		isRunning=false;
		System.out.println("容器关闭，组件停止！");
	}

	// isRunning=false  调用 start      isRunning=true    调用stop
	@Override
	public boolean isRunning() {
		System.out.println("组件是否运行判断");
		return isRunning;
	}

	@Override
	public boolean isAutoStartup() {
		return SmartLifecycle.super.isAutoStartup();
	}
}
