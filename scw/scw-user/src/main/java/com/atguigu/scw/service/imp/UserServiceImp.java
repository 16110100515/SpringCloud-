package com.atguigu.scw.service.imp;

import com.atguigu.scw.bean.*;
import com.atguigu.scw.exception.UserAcctException;
import com.atguigu.scw.mapper.TMemberAddressMapper;
import com.atguigu.scw.mapper.TMemberMapper;
//import com.atguigu.scw.mapper.UserMapper;
import com.atguigu.scw.mapper.UserMapper;
import com.atguigu.scw.service.UserService;
import com.atguigu.scw.vo.UserAddressVo;
import com.atguigu.scw.vo.UserRegistVo;
import com.atguigu.scw.vo.UserRespVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zya
 * @create 2019-12-14 14:22
 */
@Service
public class UserServiceImp implements UserService {
    @Resource
    TMemberMapper tMemberMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Resource
    TMemberAddressMapper tMemberAddressMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public void saveUser(UserRegistVo vo) {
//        2.1检查账号唯一性
        String loginacct = vo.getLoginacct();
        TMemberExample example = new TMemberExample();
        example.createCriteria().andLoginacctEqualTo(loginacct);
        long count = tMemberMapper.countByExample(example);
        if (count>0){
            throw  new UserAcctException("账号已经存在");
        }
//         2.2密码加密(BCryptPasswordEncoder)
        vo.setUserpswd(passwordEncoder.encode(vo.getUserpswd()));
        //2.3设置默认信息
        TMember member = new TMember();
        //使用member对象接受vo的数据并设置默认的数据
        //vo的属性名和属性值的类型和member是对应的
        BeanUtils.copyProperties(vo,member);
        member.setUsername(loginacct);
        member.setAuthstatus("0");

        //2.4保存注册信息到数据库中
        tMemberMapper.insertSelective(member);
    }

    @Override
    public UserRespVo doLogin(String loginacct, String userpswd) {
        TMemberExample example = new TMemberExample();
        example.createCriteria().andLoginacctEqualTo(loginacct);
        List<TMember> tMembers = tMemberMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(tMembers)||tMembers.size()>1){
            throw new UserAcctException("登入账号不存在");
        }
        TMember member = tMembers.get(0);
        String encodPwd = member.getUserpswd();
        boolean matches = passwordEncoder.matches(userpswd, encodPwd);
        if (!matches){
            throw new UserAcctException("登入密码错误");
        }
        UserRespVo userRespVo = new UserRespVo();
        BeanUtils.copyProperties(member,userRespVo);
        return userRespVo;
    }

    @Override
    public List<UserAddressVo> getUserAddress(Integer userRespVoId) {
        TMemberAddressExample example = new TMemberAddressExample();
        example.createCriteria().andMemberidEqualTo(userRespVoId);
        List<TMemberAddress> tMemberAddresses = tMemberAddressMapper.selectByExample(example);
        List<UserAddressVo> userAddressVos = new ArrayList<>();
        for (TMemberAddress tMemberAddress : tMemberAddresses) {
            userAddressVos.add(new UserAddressVo(userRespVoId,tMemberAddress.getAddress()));
        }
        return userAddressVos;
    }



    @Override
    public User checkUser(String username, String password) {
        return userMapper.checkUser(username,password);
    }

}
