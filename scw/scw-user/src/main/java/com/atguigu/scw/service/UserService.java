package com.atguigu.scw.service;

import com.atguigu.scw.vo.UserAddressVo;
import com.atguigu.scw.vo.UserRegistVo;
import com.atguigu.scw.vo.UserRespVo;

import java.util.List;

/**
 * @author zya
 * @create 2019-12-14 9:22
 */

public interface UserService {
    void saveUser(UserRegistVo vo);

    UserRespVo doLogin(String loginacct, String userpswd);

    List<UserAddressVo> getUserAddress(Integer userRespVoId);
}
