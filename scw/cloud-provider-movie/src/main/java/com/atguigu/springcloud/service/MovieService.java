package com.atguigu.springcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.springcloud.bean.Movie;
import com.atguigu.springcloud.dao.MovieDao;

@Service
public class MovieService {
	@Autowired
	MovieDao movieDao;

	public Movie getNewMovie() {
		return movieDao.getNewMovie();
	}
}
