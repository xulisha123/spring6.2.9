package com.xushu.extensions.created.event;

/**
 * Created by xsls on 2019/7/15.
 */
/*@Configuration
@ComponentScan
@EnableAsync  //异步事件*/
public class MainConfig {

   /*往SimpleApplicationEventMulticaster设置taskExecutor则为异步事件
     或者使用@Async*/
    /*@Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster
                = new SimpleApplicationEventMulticaster();

        //ThreadPoolTaskExecutor
        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }*/

}
