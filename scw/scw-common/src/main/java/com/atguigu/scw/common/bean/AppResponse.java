package com.atguigu.scw.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

//响应数据的实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppResponse<T> implements Serializable {
	
	private String code; //响应状态码  00000代表响应成功
	private String message; //响应状态描述信息  success代表成功描述
	private T data; //响应数据
	
	
	//由于响应类对象创建时 一般只有成功或失败，可以将两个方法直接编写好，方便使用
	//成功响应
	public static <T> AppResponse<T> ok(T t){
		return new AppResponse<T>("00000", "success", t);
	}
	//失败响应   10001
	public static <T> AppResponse<T> fail(T t,String message){
		return new AppResponse<T>("10001", message, t);
	}
	
	
	
}
