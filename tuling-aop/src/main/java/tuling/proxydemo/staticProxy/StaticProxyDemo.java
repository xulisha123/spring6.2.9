package tuling.proxydemo.staticProxy;

import tuling.proxydemo.UserService;
import tuling.proxydemo.UserServiceImpl;

// 4. 使用示例
public class StaticProxyDemo {
	public static void main(String[] args) {
		UserService realService = new UserServiceImpl();
		UserService proxy = new UserServiceProxy(realService);
		proxy.addUser("张三");
	}
}
