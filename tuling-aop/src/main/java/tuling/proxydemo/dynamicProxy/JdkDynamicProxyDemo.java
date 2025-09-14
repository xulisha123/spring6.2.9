package tuling.proxydemo.dynamicProxy;

import tuling.proxydemo.UserService;
import tuling.proxydemo.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicProxyDemo {
    public static void main(String[] args) {
        UserService realService = new UserServiceImpl();

        // 创建代理对象
        UserService proxy = (UserService) Proxy.newProxyInstance(
            UserService.class.getClassLoader(),
            new Class[]{UserService.class},
            new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("JDK动态代理-前置处理");
                    Object result = method.invoke(realService, args);
                    System.out.println("JDK动态代理-后置处理");
                    return result;
                }
            });

        proxy.addUser("李四");
    }
}