package com.xushu.extensions.create.aware.beanName;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class Tiger implements BeanNameAware {

    @Override
    public void setBeanName(String name) {

        System.err.println("Bean的名字:" + name);
    }
    
}