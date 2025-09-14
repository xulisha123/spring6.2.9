package com.xushu.aop.ealryaop;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class MainStart {


    public static void main(String[] args) throws Throwable {
 		// createBean

		// 正在创建的bean
		UserService bean=new UserService();

        // 1.解析 : 解析所有的@Aspect的Bean 拿到 切点+通知 最终解析到List<Advisor>
		// 1.1 拿到@Aspect的BeanDefinition BeanClass
		// @PointCut  = 封装到pointcut对象中
		// 通知 = 封装到advice对象中
		// (一个通知)Advisor = pointcut+advice
		List<Advisor> list=new ArrayList<>();

		SimplePointcut pointcut = new SimplePointcut();

        list.add(new MyInstantiationModelAwarePointcutAdvisorImpl(new CustomBeforeAdvice(), pointcut));
        list.add(new MyInstantiationModelAwarePointcutAdvisorImpl(new CustomAfterAdvice(), pointcut));


		// 初始化后
		// 2.匹配， 根据切点表达式进行匹配， 过滤出匹配的Advisor
		List<Advisor> canApply = list.stream().filter(advisor -> {
			Pointcut pc = ((MyInstantiationModelAwarePointcutAdvisorImpl) advisor).getPointcut();
			// 切点表达式匹配
			return pc.getClassFilter().matches(bean.getClass());
		}).collect(Collectors.toList());

		// if(canApply.size()>0) 说明有匹配成功的切点表达式

		// 创建代理 jdk
		IUserService proxy = (IUserService) Proxy.newProxyInstance(bean.getClass().getClassLoader(),
				bean.getClass().getInterfaces(),
				new MyInvocationHandler(bean, canApply)
		);

		// proxy存储到一级缓存
		// 容器解析完


		// ioc.getBean()
		// 3.调用阶段
		// 得到当前方法
		proxy.addUser("xushu");


    }



	public static class MyMethodInvocation implements MethodInvocation{
		protected List<MethodInterceptor> list;
		protected  Object target;
		protected Method method;
		protected Object[] arguments;
		int i=0;

		public MyMethodInvocation(List<MethodInterceptor> interceptors, Object target, Method method, Object[] args) {
			this.list = interceptors;
			this.target = target;	// 目标bean对象
			this.method = method;	// 方法
			this.arguments = args;	// 方法的参数
		}

		@Override
		public Object proceed() throws Throwable {
			//是否最后一个 ，最后一个执行目标方法
			if(i==list.size()){
				return getMethod().invoke(target,getArguments());
			}
			MethodInterceptor mi = list.get(i);
			i++;
			return  mi.invoke(this);
		}

		@Override
		public Object getThis() {
			return target;
		}

		@Override
		public AccessibleObject getStaticPart() {
			return null;
		}


		@Override
		public Method getMethod() {
			return method;
		}

		@Override
		public Object[] getArguments() {
			return arguments;
		}
	}
}
