package tuling.circularAopDemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xsls on 2019/5/29.
 */
@Component
public class InstanceB implements IInstanceB  {


    @Autowired
    private  IInstanceA instanceA;


    public IInstanceA getInstanceA() {
        return instanceA;
    }

    public InstanceB() {
    }


    //
//    public InstanceB(InstanceA instanceA) {
//        System.out.println("BBBBB");
//        this.instanceA = instanceA;
//    }


    @Override
    public void say() {
        System.out.println("Hi~ I'm B, My A is:"+getInstanceA().getClass());
    }
}
