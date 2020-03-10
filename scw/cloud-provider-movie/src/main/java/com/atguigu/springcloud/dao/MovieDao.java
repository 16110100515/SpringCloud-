package com.atguigu.springcloud.dao;

import org.springframework.stereotype.Repository;

import com.atguigu.springcloud.bean.Movie;
//dao: data access Object
@Repository
public class MovieDao {
	public Movie getNewMovie() {
		Movie movie = new Movie();
		movie.setId(1);
		movie.setMovieName("战狼");
		return movie;
	}
}
