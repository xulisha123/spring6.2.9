package com.xushu.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 *
 * 监听器
 */
@Component
public class OrderEventListener {//}  implements ApplicationListener<OrderEvent> {

    // 基于注解的
    @EventListener(OrderEvent.class)
    public void onApplicationEvent(OrderEvent event) {
        if(event.getName().equals("减库存")){
            System.out.println("减库存.......");
        }
        // 所有bean创建完做扩展
    }

}
