package com.atguigu.scw.controller;

import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.common.utils.ScwAppUtils;
import com.atguigu.scw.consts.UserAppConsts;
import com.atguigu.scw.service.UserService;
import com.atguigu.scw.utils.SmsTemplate;
import com.atguigu.scw.vo.UserRegistVo;
import com.atguigu.scw.vo.UserRespVo;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zya
 * @create 2019-12-12 11:09
 */
@RequestMapping("/user")
@RestController
@Api(tags = "用户登入注册模块")
public class UserLoginRegistController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    SmsTemplate smsTemplate;
    @Autowired
    UserService userService;

    @PostMapping("/doLogin")
    @ApiOperation(value = "用户登入")
    public AppResponse<Object> doLogin(@RequestParam("loginacct") String loginacct, @RequestParam("userpswd")String userpswd) {
        UserRespVo vo = null;
        try {
            vo = userService.doLogin(loginacct, userpswd);
            String token = UUID.randomUUID().toString().replace("-", "");
            vo.setAccessToken(token);
            Gson gson = new Gson();
            String voStr = gson.toJson(vo);
            //将vo存到redis中
            stringRedisTemplate.opsForValue().set(token,voStr,30,TimeUnit.MINUTES);
            return AppResponse.ok(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return AppResponse.fail("登入失败", e.getMessage());
        }
    }
    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public AppResponse<String> userRegister(UserRegistVo vo){
        //vo 一般用来描述浏览器与java交互的数据
        //1、验证验证码
        //1.1 获取redis中该手机号码的验证码
        String codeKey = UserAppConsts.PHONENUM_CODEKEY_PREFIX+vo.getLoginacct()+UserAppConsts.PHONENUM_CODEKEY_SUFFIX;
        String serverCode = stringRedisTemplate.opsForValue().get(codeKey);
        if(StringUtils.isEmpty(serverCode)) {
            return AppResponse.fail("验证码过期", "error");
        }
        //1.2 获取提交的验证码参数
        String clientCode = vo.getCode();
        //1.3 比较
        if(!serverCode.equalsIgnoreCase(clientCode)) {
            return AppResponse.fail("验证码输入错误", "error");
        }
        //2、 调用业务层处理注册的业务
        try {
            userService.saveUser(vo);
            //注册成功
            //2.4 删除redis中的未过期的验证码
            stringRedisTemplate.delete(codeKey);
            //3、给出成功响应
            return AppResponse.ok("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            //注册失败
            return AppResponse.fail("注册失败",e.getMessage());

        }
    }



    @PostMapping("/reset")
    @ApiOperation(value = "重置密码")
    public String resetUserPsw(){
        return "";
    }


    @PostMapping("/sendsms")
    @ApiOperation(value = "发送短信验证码")
    public AppResponse<String> sendMessages(String phoneNum){
        boolean flag = ScwAppUtils.isPhone(phoneNum);
        if(!flag) {
            return AppResponse.fail("手机格式错误", "请求失败");
        }
        //2 查看redis中该手机号码获取验证码的次数是否超过范围
        //同一个手机号码redis中会存储多个值，需要设置唯一键
        // 存储手机号码获取验证码次数的key
        String countKey = UserAppConsts.PHONENUM_COUNTKEY_PREFIX+phoneNum+UserAppConsts.PHONENUM_CODEKEY_SUFFIX;
        String countStr = stringRedisTemplate.opsForValue().get(countKey);
        int count= 0;
        if(!StringUtils.isEmpty(countStr)) {
            //获取验证码的次数不为0，可以将其转为int类型
            count = Integer.parseInt(countStr);
        }
        if(count>=3) {
            return AppResponse.fail("获取验证码次数用尽", "请求失败");
        }
        //3 查看该手机号码是否存在未过期验证码
        String codeKey = UserAppConsts.PHONENUM_CODEKEY_PREFIX+phoneNum+UserAppConsts.PHONENUM_CODEKEY_SUFFIX;
        String code = stringRedisTemplate.opsForValue().get(codeKey);
        if(!StringUtils.isEmpty(code)) {
            return AppResponse.fail("请不要频繁获取验证码", "请求失败");
        }
        //4 生成验证码字符串
        code = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        //5 调用SmsTemplate发送短信
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", phoneNum);
        querys.put("param", "code:"+code);
        querys.put("tpl_id", "TP1711063");
        try {
            smsTemplate.sendMessage(querys);
            //6 将短信验证码存到redis中10分钟
            stringRedisTemplate.opsForValue().set(codeKey, code, 10, TimeUnit.MINUTES);
            //7 更新该手机号码的获取验证码的次数
            stringRedisTemplate.opsForValue().increment(countKey);
            //8 响应结果   {code:10000  , msg:"ok" ,data: Object}
            return AppResponse.ok("短信发送成功");
        } catch (Exception e) {
            //e.printStackTrace();
            return AppResponse.fail(e.getMessage(), "请求失败");
        }
    }

}
