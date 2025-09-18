package com.xushu.mybatis.real.mock;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class MyMapperFactory {

    public static <T> T getMapper(Class<T> mapperInterface, SqlSessionFactory sqlSessionFactory){

        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),
                new Class[] {mapperInterface },
                new MyMapperProxy(mapperInterface,sqlSessionFactory));
    }


}
