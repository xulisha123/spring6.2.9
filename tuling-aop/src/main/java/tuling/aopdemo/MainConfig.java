package tuling.aopdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by xsls on 2019/6/10.
 */
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)  //在线程中暴露代理对象
@ComponentScan("tuling")
public class MainConfig {


}
