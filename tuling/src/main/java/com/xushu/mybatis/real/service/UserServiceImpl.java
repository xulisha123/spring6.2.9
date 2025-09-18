package com.xushu.mybatis.real.service;

import com.xushu.mybatis.real.entity.User;
import com.xushu.mybatis.real.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Component
public class UserServiceImpl implements IUserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public User selectById(Long id) {
        return userMapper.selectById(1L);
    }


}
