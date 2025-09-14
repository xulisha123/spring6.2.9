package tuling.proxydemo;

// 2. 实现类(真实对象)
public class UserServiceImpl implements UserService {
	@Override
	public void addUser(String name) {
		System.out.println("添加用户: " + name);
	}
}
