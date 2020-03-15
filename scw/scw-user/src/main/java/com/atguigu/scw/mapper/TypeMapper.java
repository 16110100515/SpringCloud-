package com.atguigu.scw.mapper;

import com.atguigu.scw.bean.Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @auther crush
 * @create 2020-03-15-23:06
 */
public interface TypeMapper {

    int save(@Param("type")Type type);

    Type findOne(Long id);

    Type findByName(String name);

    Page<Type> findAll(Pageable pageable);

    void delete(Long id);
}
