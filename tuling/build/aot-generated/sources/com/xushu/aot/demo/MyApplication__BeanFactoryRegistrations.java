package com.xushu.aot.demo;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.event.DefaultEventListenerFactory__BeanDefinitions;
import org.springframework.context.event.EventListenerMethodProcessor__BeanDefinitions;

/**
 * Register bean definitions for the bean factory.
 */
@Generated
public class MyApplication__BeanFactoryRegistrations {
  /**
   * Register the bean definitions.
   */
  public void registerBeanDefinitions(DefaultListableBeanFactory beanFactory) {
    beanFactory.registerBeanDefinition("org.springframework.context.event.internalEventListenerProcessor", EventListenerMethodProcessor__BeanDefinitions.getInternalEventListenerProcessorBeanDefinition());
    beanFactory.registerBeanDefinition("org.springframework.context.event.internalEventListenerFactory", DefaultEventListenerFactory__BeanDefinitions.getInternalEventListenerFactoryBeanDefinition());
    beanFactory.registerBeanDefinition("myApplication", MyApplication__BeanDefinitions.getMyApplicationBeanDefinition());
    beanFactory.registerBeanDefinition("greeting", MyApplication__BeanDefinitions.getGreetingBeanDefinition());
  }

  /**
   * Register the aliases.
   */
  public void registerAliases(DefaultListableBeanFactory beanFactory) {
  }
}
