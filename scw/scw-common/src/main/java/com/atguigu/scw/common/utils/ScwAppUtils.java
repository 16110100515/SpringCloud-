package com.atguigu.scw.common.utils;

import com.alibaba.fastjson.JSON;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.data.redis.core.StringRedisTemplate;
import springfox.documentation.spring.web.json.Json;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScwAppUtils {
	
	//正则验证手机号码格式是否正确的方法
	public static boolean isPhone(String phone) {
		String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
		if (phone.length() != 11) {
			return false;
		} else {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(phone);
			boolean isMatch = m.matches();
			return isMatch;
		}
	}
	//从redis中获取json字符串转为指定类型的对象的方法
	public static <T>T getObjectFromRedis(StringRedisTemplate stringRedisTemplate,Class<T> type,String token){
		String jsonStr = stringRedisTemplate.opsForValue().get(token);
		return JSON.parseObject(jsonStr,type);
	}
	//将对象转为json字符串
	public static <T>void setObjectToJson(T t,StringRedisTemplate stringRedisTemplate,String token){
		String jsonStr = JSON.toJSONString(t);
		stringRedisTemplate.opsForValue().set(token,jsonStr);
	}

}
