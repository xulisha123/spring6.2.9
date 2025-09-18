package com.xushu.mybatis.real.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.xushu.mybatis.real.entity.User;
import com.xushu.mybatis.real.mapper.UserMapper;
import com.xushu.mybatis.real.mock.MyFactoryBean;
import com.xushu.mybatis.real.mock.MyMapperFactory;
import com.xushu.mybatis.real.mock.annotations.XushuMapperScan;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by xsls
 *
 * spring.xml
 */
@Configuration
//@XushuMapperScan(basePackages = "com.xushu.mybatis.real.mapper")
@MapperScan(basePackages = "com.xushu.mybatis.real.mapper")
@ComponentScan(basePackages = {"com.xushu.mybatis.real"})
public class MyBatisConfig {


/*	@Bean
    public MyFactoryBean userMapper(){
		return new MyFactoryBean(UserMapper.class);

	}*/




	/**
	 * <bean class="org.mybatis.spring.SqlSessionFactoryBean" id="sqlSessionFactory">
	 *     datasource
	 *     mapper文件的路径
	 *     别名
	 *
	 *   </bean>
	 *
	 *   <mapper-scan basePackage=""/>
	 *
	 * @return
	 * @throws IOException
	 */
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory( ) throws IOException {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		// 设置 MyBatis 配置文件路径
		//factoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis.xml"));
		// 设置 SQL 映射文件路径
		factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml"));
		factoryBean.setTypeAliasesPackage("com.xushu.mybatis.real.entity");
		return factoryBean;
	}

	@Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mybatis_example");
        return dataSource;
    }

	/*@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(){
		DataSourceTransactionManager manager = new DataSourceTransactionManager();
		manager.setDataSource(dataSource());
		return manager;
	}*/



	 // 一个mapper接口就要配置一个@Bean  自由控制实例化
	// 基础类型 Class
	/*  @Bean
	 public UserMapper userMapper(){
		  return MyMapperFactory.getMapper(UserMapper.class,null);
	 }*/




}
