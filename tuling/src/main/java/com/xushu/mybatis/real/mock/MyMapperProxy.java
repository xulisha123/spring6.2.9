package com.xushu.mybatis.real.mock;

import com.xushu.mybatis.real.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class MyMapperProxy implements InvocationHandler {

	SqlSessionFactory sqlSessionFactory;
	Class  mapperInterface;

	public MyMapperProxy(Class mapperInterface, SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory=sqlSessionFactory;
		this.mapperInterface=mapperInterface;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 如果是equals 或 toString这些Object自带的方法就直接调用
		if (Object.class.equals(method.getDeclaringClass())) {
			return method.invoke(this, args);
		}

		// com.tuling.mapper.UserMapper.selectById
		String statementId = mapperInterface.getName() + "." + method.getName();
		System.out.println("查询数据库"+mapperInterface.getName()+"."+method.getName());

		if(sqlSessionFactory!=null) {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			sqlSession.selectOne(statementId,args[0]);

			// 拿到mybatis一些api  sqlsessionfacotry   configuration
			return new User();
		}
		return null;
	}
}
