package com.xushu.mybatis.real.mock;

import com.xushu.mybatis.real.mapper.UserMapper;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/***
 * @Author 公众号：程序员徐庶
 * @Slogan 致敬大师，致敬未来的你
 * 1. BeanFactory   接口     创建bean
 * 1.FactoryBean   接口   被他修饰bean会成为一个特殊的bean:
 * 当通过bean名称获取的时候返回的是getObject()的对象，
 *
 * 懒加载 ，当用到FactoryBean 才会调用getObject()
 */
public class MyFactoryBean implements FactoryBean {

	Class<?> mapperInterface;

    // getObject的反值就是真正注册的那个bean
    @Override
    public Object getObject() throws Exception {
		return MyMapperFactory.getMapper(mapperInterface,null);

	}

	public MyFactoryBean(Class<?> mapperInterface) {
		this.mapperInterface = mapperInterface;
	}

	//
    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }
}
