package com.xushu.circulardependencies.lazydemo;

/**
 * Created by xsls
 */
public interface IAService {

	void say();
	IBService getInstanceB();
}
