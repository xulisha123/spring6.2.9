package tuling.EalyAopDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tuling.aopdemo.Calculate;

/**
 * Created by xsls on 2019/6/10.
 */
public class TulingMainClass {

    public static void main(String[] args) {

       /* */
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(EalyAopMainConfig.class);
        Calculate tulingCalculate = ctx.getBean("tulingCalculate",Calculate.class);
        tulingCalculate.mod(1,1);



    }
}
