package com.xushu.circulardependencies.One;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

/**
 * Created by xsls
 */
//@Component
public class JdkProxyBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		return createProxy(bean,beanName);
	}

	public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {

		return createProxy(bean,beanName);
	}

	public Object createProxy(Object bean, String beanName)
	{
		// 假设:A 被切点命中 需要创建代理  @PointCut("execution(* *..InstanceA.*(..))")
		if(bean instanceof AService) {
			JdkDynimcProxy jdkDynimcProxy = new JdkDynimcProxy(bean);
			return  jdkDynimcProxy.getProxy();
		}
		return bean;
	}
}
