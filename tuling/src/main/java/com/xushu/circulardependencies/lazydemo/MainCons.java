package com.xushu.circulardependencies.lazydemo;

import org.springframework.objenesis.instantiator.sun.SunReflectionFactoryInstantiator;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class MainCons {
    public static void main(String[] args) {
        // 为这个class创建一个新的constractor
        SunReflectionFactoryInstantiator instantiator=new SunReflectionFactoryInstantiator(AService.class);
        Object o = instantiator.newInstance();
        System.out.println(o.getClass());
    }
}
