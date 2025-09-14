package tuling.circularAopDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by xsls on 2019/5/29.
 */
public class MainClass {


    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ioc=new AnnotationConfigApplicationContext(MainConfig.class);

        IInstanceA instanceA = (IInstanceA) ioc.getBean("instanceA");

        instanceA.say();
    }


}
