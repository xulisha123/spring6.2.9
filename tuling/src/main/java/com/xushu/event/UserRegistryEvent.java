package com.xushu.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 * 注册事件
 */
public class UserRegistryEvent  extends ApplicationEvent {
    public UserRegistryEvent(Object source) {
        super(source);
    }

}