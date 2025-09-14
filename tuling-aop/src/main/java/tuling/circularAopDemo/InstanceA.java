package tuling.circularAopDemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xsls on 2019/5/29.
 *
 */
@Component
public class InstanceA implements IInstanceA {

    @Autowired
    private IInstanceB instanceB;

    public IInstanceB getInstanceB() {
        return instanceB;
    }


    /*
    public InstanceA(InstanceB instanceB) {
        System.out.println("AAAAAA");
        // 这个时候B里面的A 还是=null?
        //instanceB.getInstanceA().say();
        this.instanceB = instanceB;
    }*/

    public InstanceA() {
    }

    @Override
	public void say() {
		System.out.println("I'm A， My B is"+instanceB.toString());
	}


    @Override
    public String toString() {
        return "InstanceA{" +
                "instanceB=" + instanceB +
                '}';
    }
}
