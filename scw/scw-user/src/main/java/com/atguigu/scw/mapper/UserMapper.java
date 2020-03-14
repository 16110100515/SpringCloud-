package com.atguigu.scw.mapper;

import com.atguigu.scw.bean.User;
import org.apache.ibatis.annotations.Param;

/**
 * @auther crush
 * @create 2020-03-14-15:07
 */
public interface UserMapper {
    int testUser(@Param("username")String username, @Param("password")String password);

    User checkUser(@Param("username")String username, @Param("password")String password);
}
