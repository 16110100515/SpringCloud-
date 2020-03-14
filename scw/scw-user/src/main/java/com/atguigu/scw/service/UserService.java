package com.atguigu.scw.service;

import com.atguigu.scw.bean.User;
import com.atguigu.scw.vo.UserAddressVo;
import com.atguigu.scw.vo.UserRegistVo;
import com.atguigu.scw.vo.UserRespVo;

import java.util.List;

/**
 * @author LSJ
 * @create 2020-03-14 15:05
 */

public interface UserService {
    void saveUser(UserRegistVo vo);

    UserRespVo doLogin(String loginacct, String userpswd);

    List<UserAddressVo> getUserAddress(Integer userRespVoId);

    User checkUser(String username,String password);
}
