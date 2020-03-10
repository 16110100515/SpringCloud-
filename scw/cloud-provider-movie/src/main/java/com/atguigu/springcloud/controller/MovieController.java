package com.atguigu.springcloud.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.bean.Movie;
import com.atguigu.springcloud.service.MovieService;

@RestController
public class MovieController {
	
	@Autowired
	MovieService movieService;
	//@ConfigurationProperties(prefix=xxx)
	
	@Value("${server.port}")  //从配置中读取某个属性 设置给当前属性
	Integer port;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	//从session域中获取属性值
	@RequestMapping("/get")
	public String get(HttpSession session) {
		String sessionVal = (String) session.getAttribute("sessionKey");
		System.out.println("端口号："+port+",获取session域中的属性值："+ sessionVal);
		return "ok";
	}
	//向session域中设置属性值
	@RequestMapping("/set")
	public String set(HttpSession session) {
		System.out.println("端口号："+port);
		session.setAttribute("sessionKey", "hehe");
		return "ok";
	}
	
	
	
	
	
	/**
	 * 获取最新电影
	 */
	@GetMapping("/movie")
	public Movie getNewMovie() {
		logger.warn("movie服务,端口号为：{}   被访问了", port);
		return movieService.getNewMovie();
	}

}
