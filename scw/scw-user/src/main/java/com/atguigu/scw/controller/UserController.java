package com.atguigu.scw.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.scw.common.utils.ScwAppUtils;
import com.atguigu.scw.utils.SmsTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@Api(tags="处理用户注册登录请求相关的模块")
@RestController
public class UserController {
	@Autowired
	SmsTemplate smsTemplate;
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	//1、处理获取验证码的请求
	@ApiOperation("获取验证码的方法")
	@ApiImplicitParams({
		@ApiImplicitParam(name="phoneNum" , required=true , defaultValue="18642866683")
	})
	@GetMapping("/sendCode")
	public String sendCode(String phoneNum) {
		//1 正则验证手机号码格式
		boolean flag = ScwAppUtils.isPhone(phoneNum);
		if(!flag) {
			return "手机格式错误";
		}
		//2 查看redis中该手机号码获取验证码的次数是否超过范围
		//同一个手机号码redis中会存储多个值，需要设置唯一键
		// 存储手机号码获取验证码次数的key
		String countKey = "phonenum:count:"+phoneNum+":code";
		String countStr = stringRedisTemplate.opsForValue().get(countKey);
		int count= 0;
		if(!StringUtils.isEmpty(countStr)) {
			//获取验证码的次数不为0，可以将其转为int类型
			count = Integer.parseInt(countStr);
		}
		if(count>=3) {
			return "获取验证码次数用尽";
		}
		//3 查看该手机号码是否存在未过期验证码
		String codeKey = "phonenum:code:"+phoneNum+":code";
		String code = stringRedisTemplate.opsForValue().get(codeKey);
		if(!StringUtils.isEmpty(code)) {
			return "请不要频繁获取验证码";
		}
		//4 生成验证码字符串
		code = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
		//5 调用SmsTemplate发送短信
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("mobile", phoneNum);
		querys.put("param", "code:"+code);
		querys.put("tpl_id", "TP1711063");
		smsTemplate.sendMessage(querys);
		//6 将短信验证码存到redis中10分钟
		stringRedisTemplate.opsForValue().set(codeKey, code, 10, TimeUnit.MINUTES);
		//7 更新该手机号码的获取验证码的次数
		stringRedisTemplate.opsForValue().increment(countKey);
		//8 响应结果
		return "ok";
	}
}
