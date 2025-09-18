package com.xushu.mybatis.real.service;

import com.xushu.mybatis.real.entity.User;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public interface IUserService {
    User selectById(Long id);
}
