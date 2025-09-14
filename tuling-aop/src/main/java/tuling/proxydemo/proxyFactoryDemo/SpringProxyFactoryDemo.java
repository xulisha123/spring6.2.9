package tuling.proxydemo.proxyFactoryDemo;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.AfterReturningAdvice;
import tuling.proxydemo.UserService;
import tuling.proxydemo.UserServiceImpl;

public class SpringProxyFactoryDemo {
    public static void main(String[] args) {
        UserService realService = new UserServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(realService);

        // 添加前置通知
        proxyFactory.addAdvice((MethodBeforeAdvice) (method, args1, target) -> 
                                System.out.println("ProxyFactory-前置通知: " + method.getName()));

        // 添加后置通知
        proxyFactory.addAdvice((AfterReturningAdvice) (returnValue, method, args1, target) -> 
                                System.out.println("ProxyFactory-后置通知: " + method.getName()));

        UserService proxy = (UserService) proxyFactory.getProxy();
        proxy.addUser("赵六");
    }
}