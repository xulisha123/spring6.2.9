package com.xushu.aot.demo;

import java.lang.String;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link MyApplication}.
 */
@Generated
public class MyApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'myApplication'.
   */
  public static BeanDefinition getMyApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MyApplication.class);
    beanDefinition.setInstanceSupplier(MyApplication::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'greeting'.
   */
  private static BeanInstanceSupplier<String> getGreetingInstanceSupplier() {
    return BeanInstanceSupplier.<String>forFactoryMethod(MyApplication.class, "greeting")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean("myApplication", MyApplication.class).greeting());
  }

  /**
   * Get the bean definition for 'greeting'.
   */
  public static BeanDefinition getGreetingBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(String.class);
    beanDefinition.setFactoryBeanName("myApplication");
    beanDefinition.setInstanceSupplier(getGreetingInstanceSupplier());
    return beanDefinition;
  }
}
