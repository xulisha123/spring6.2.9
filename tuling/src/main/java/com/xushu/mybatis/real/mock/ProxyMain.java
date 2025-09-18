package com.xushu.mybatis.real.mock;

import com.xushu.mybatis.real.mapper.UserMapper;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class ProxyMain {
    public static void main(String[] a) {
        //  动态代理对象 怎么注入spring容器中
        UserMapper userMapper =  MyMapperFactory.getMapper(UserMapper.class,null);

        System.out.println(userMapper.getClass());
        userMapper.selectById(1L);


    }

}
