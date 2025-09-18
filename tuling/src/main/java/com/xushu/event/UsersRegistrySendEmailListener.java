package com.xushu.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 *
 * 监听器
 */
@Component
public class UsersRegistrySendEmailListener {//} implements ApplicationListener<UserRegistryEvent> {

	// 基于注解的
    @EventListener(UserRegistryEvent.class)
    public void onApplicationEvent(UserRegistryEvent event) {
		System.out.println("发Email");
    }

}
