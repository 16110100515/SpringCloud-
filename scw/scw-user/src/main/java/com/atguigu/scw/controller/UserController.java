package com.atguigu.scw.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.consts.UserAppConsts;
import com.atguigu.scw.service.UserService;
import com.atguigu.scw.vo.UserRegistVo;
import com.atguigu.scw.vo.UserRespVo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.scw.common.utils.ScwAppUtils;
import com.atguigu.scw.utils.SmsTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@Api(tags="处理用户注册登录请求相关的模块")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	SmsTemplate smsTemplate;
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Autowired
	UserService userService;

	//3、处理登录的方法
	@ApiOperation("登录方法")
	@PostMapping("/doLogin")
	public AppResponse<Object> doLogin(String loginacct , String userpswd){
		UserRespVo vo = null;
		try {
			vo = userService.doLogin(loginacct,userpswd);
			//将vo转为json字符串
			String token = UUID.randomUUID().toString().replace("-", "");
			token= UserAppConsts.USER_LOGIN_PREFIX+ token;
			vo.setAccessToken(token);
			Gson gson = new Gson();
			String voStr = gson.toJson(vo);
			//将vo存到redis中
			stringRedisTemplate.opsForValue().set(token, voStr);
			return AppResponse.ok(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return AppResponse.fail("登录失败", e.getMessage());
		}
	}
	/**
	 * 使用数据库表的javabean来接受浏览器提交的请求数据并不合适
	 * 		浏览器提交的数据有些时暂时使用的不需要持久化保存的
	 * 		数据库中有些数据是不需要浏览器提交的，后台默认设置的
	 *
	 *   Javabean: 一般用来描述数据库和java交互的数据
	 *   VO:  view Object  一般用来描述 浏览器和java代码交互的数据
	 *   		- 浏览器提交的数据 都通过VO类封装描述
	 *   		- VO接受到数据后一般也会和数据库进行交互，所以VO需要存到数据库中的字段名一定要和数据库DAO  javabean一样
	 * 	 Pojo : springmvc使用的
	 */
	//2、处理用户注册的请求
	@ApiOperation("注册方法")
	@PostMapping("/doRegist")
	public AppResponse<Object> doRegist(UserRegistVo vo){
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
	//1、处理获取验证码的请求
	@ApiOperation("获取验证码的方法")
	@ApiImplicitParams({
		@ApiImplicitParam(name="phoneNum" , required=true , defaultValue="18642866683")
	})
	@GetMapping("/sendCode")
	public AppResponse sendCode(String phoneNum) {
		//1 正则验证手机号码格式
		boolean flag = ScwAppUtils.isPhone(phoneNum);
		if(!flag) {
			return AppResponse.fail("手机格式错误","请求失败 手机格式错误");
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
			return AppResponse.fail("获取验证码次数用尽","请求失败 手机次数没了");
		}
		//3 查看该手机号码是否存在未过期验证码
		String codeKey = UserAppConsts.PHONENUM_CODEKEY_PREFIX +phoneNum+UserAppConsts.PHONENUM_CODEKEY_SUFFIX;
		String code = stringRedisTemplate.opsForValue().get(codeKey);
		if(!StringUtils.isEmpty(code)) {
			return AppResponse.fail("请不要频繁获取验证码","请求失败咯 没到十分钟 之前发的验证码海能用");
		}
		//4 生成验证码字符串
		code = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
		//5 调用SmsTemplate发送短信
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("mobile", phoneNum);
		querys.put("param", "code:"+code);
		querys.put("tpl_id", "TP1711063");
        try {
//            smsTemplate.sendMessage(querys);
            //6 将短信验证码存到redis中10分钟
            stringRedisTemplate.opsForValue().set(codeKey, code, 10, TimeUnit.MINUTES);
            //7 更新该手机号码的获取验证码的次数
            stringRedisTemplate.opsForValue().increment(countKey);
            //8 响应结果 {code:10000  , msg:"ok" ,data: Object}
            return AppResponse.ok("短信发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AppResponse.fail(e.getMessage(), "请求失败");
        }
	}
    /**
     * 使用自定义类 封装  响应数据：
     * 		1、响应状态码：     服务器controller处理请求成功了,根据场景给请求访问者不同的描述
     * 			code	00000 响应成功
     * 					10001 响应失败
     * 		2、响应描述信息：
     * 			message  success/ok
     * 					 fail  /error
     *
     * 		3、响应数据：  响应时有时需要响应对象或集合 或一个字符串 或一个数字
     * 			data     T   使用泛型
     */
}
