package com.xushu.mybatis.real.mapper;

import com.xushu.mybatis.real.entity.User;
import org.springframework.stereotype.Component;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 *
 * MyBatis的Mapper接口  交给Spring去管理
 *
 *  1.没有@Component  2. 他是接口
 */
public interface UserMapper {
    User selectById(Long id);
}
