package com.atguigu.scw.service;

//import com.lrm.po.Type;
import com.atguigu.scw.bean.Tag;
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

    int deleteType_tag(Long id);

    int updateType_tag(Long id, Tag tag);

    Tag getType_tag(Long id);

    int saveType_tag(Tag tag);

    Page queryUserPage_tag(Map paramMap);

//    Type getById(Long id);

//    int editPost(Long id, Type type);
}
