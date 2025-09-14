package tuling.proxydemo.staticProxy;

import tuling.proxydemo.UserService;

// 3. 代理类(实现相同接口)
public class UserServiceProxy implements UserService {
	private UserService target;

	public UserServiceProxy(UserService target) {
		this.target = target;
	}

	@Override
	public void addUser(String name) {
		System.out.println("前置处理: 验证权限");
		target.addUser(name); // 调用真实对象方法
		System.out.println("后置处理: 记录日志");
	}
}
