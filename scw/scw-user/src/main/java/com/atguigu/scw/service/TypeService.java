package com.atguigu.scw.service;

//import com.lrm.po.Type;
import com.atguigu.scw.bean.Type;

import com.atguigu.scw.utils.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * Created by limi on 2017/10/16.
 */
public interface TypeService {

    int saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    Page queryUserPage(Map<String,Object> paramMap);

    int updateType(Long id, Type type);

    int deleteType(Long id);

//    Type getById(Long id);

//    int editPost(Long id, Type type);
}
