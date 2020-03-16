package com.atguigu.scw.mapper;

import com.atguigu.scw.bean.Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @auther crush
 * @create 2020-03-15-23:06
 */
public interface TypeMapper {

    int save(@Param("type")Type type);

    Type findOne(Long id);

    Type findByName(String name);

    Page<Type> findAll(Pageable pageable);

    int delete(Long id);

    int updateType(@Param("id")Long id, @Param("type")Type type);

    List<Type> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);
}
