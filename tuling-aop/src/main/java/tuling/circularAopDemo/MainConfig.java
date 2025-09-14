package tuling.circularAopDemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by xsls on 2019/5/29.
 */
@Configuration
@ComponentScan(basePackages = {"tuling.circularAopDemo"})
@EnableAspectJAutoProxy
//@ImportResource(value = {"classpath:beans/beans.xml"})
public class MainConfig {

}
