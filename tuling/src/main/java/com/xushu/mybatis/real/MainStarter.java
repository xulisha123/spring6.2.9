package com.xushu.mybatis.real;

import com.xushu.mybatis.real.config.MyBatisConfig;
import com.xushu.mybatis.real.mapper.UserMapper;
import com.xushu.mybatis.real.service.IUserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，.致敬未来的你
 *
 *  mapper接口是通过jdk的动态代理   怎么将动态代理的实例注入ioc容器中
 */
public class MainStarter {

    public static void main(String[] args) {
    	// 容器创建   所有的bean创建完成
        AnnotationConfigApplicationContext ioc =
               new AnnotationConfigApplicationContext(MyBatisConfig.class);
        System.out.println("容器创建完成");

		IUserService bean = ioc.getBean(IUserService.class);
        System.out.println(bean.getClass());
		System.out.println(bean.selectById(1L));
	}

}
