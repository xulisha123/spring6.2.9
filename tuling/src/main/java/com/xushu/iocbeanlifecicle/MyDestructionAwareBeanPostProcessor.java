package com.xushu.iocbeanlifecicle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        System.out.println(beanName+"销毁了");
    }
}
