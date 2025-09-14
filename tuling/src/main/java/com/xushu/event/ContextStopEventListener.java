package com.xushu.event;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Component
public class ContextStopEventListener {

    @EventListener(ContextStoppedEvent.class)
    public void onApplicationEvent()  {
            System.out.println("\n\n\n\n\n______________\n\n\n暂停了\n\n_________\n\n");


    }
}
