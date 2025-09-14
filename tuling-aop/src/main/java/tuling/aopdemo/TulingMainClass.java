package tuling.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by xsls on 2019/6/10.
 */
public class TulingMainClass {

    public static void main(String[] args) {

        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./");
    	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);

      /**/
        Calculate calculate = (Calculate) ctx.getBean("tulingCalculate");
        System.out.println(calculate.getClass());
        int retVal = calculate.mod(2,4);

        /*
         ProgramCalculate pcalculate = (ProgramCalculate) ctx.getBean("tulingCalculate");
        System.out.println(pcalculate.toBinary(100));
        */

    }

}
