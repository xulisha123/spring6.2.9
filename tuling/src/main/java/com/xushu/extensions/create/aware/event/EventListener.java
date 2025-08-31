package com.xushu.extensions.create.aware.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener implements ApplicationListener<EmailEvent> {

    @Override
    public void onApplicationEvent(EmailEvent event) {

        //发邮件
        System.out.println("正在发送邮件至： " + event.getUser().getEmail());

        //发短信
        System.out.println("正在发短信到： " + event.getUser().getPhoneNum());
    }
}