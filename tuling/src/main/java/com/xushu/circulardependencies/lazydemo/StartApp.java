package com.xushu.circulardependencies.lazydemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@ComponentScan
public class StartApp {
    public static void main(String[] args)   {
        // 加载spring上下文  创建
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(StartApp.class);
        // 获取Bean
        IAService instance =  (IAService) context.getBean("AService");
        instance.say();
		instance.getInstanceB().say();
    }
}
