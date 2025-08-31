package com.xushu.iocbeanlifecicle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {

        return null;
    }

    public MyInstantiationAwareBeanPostProcessor() {
        System.out.println(1111);
    }
}
