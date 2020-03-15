package com.atguigu.scw.service.imp;

import com.atguigu.scw.bean.Type;
import com.atguigu.scw.exception.NotFoundException;
import com.atguigu.scw.mapper.TypeMapper;
import com.atguigu.scw.service.TypeService;
//import com.lrm.NotFoundException;
//import com.lrm.dao.TypeRepository;
//import com.lrm.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by limi on 2017/10/16.
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Resource
    private TypeMapper typeMapper;

    @Transactional
    @Override
    public int saveType(Type type) {
         return typeMapper.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeMapper.findOne(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.findByName(name);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeMapper.findAll(pageable);
    }

    @Override
    public Type updateType(Long id, Type type) {
        return null;
    }


//    @Transactional
//    @Override
//    public Type updateType(Long id, Type type) {
//        Type t = typeMapper.findOne(id);
//        if (t == null) {
//            throw new NotFoundException("不存在该类型");
//        }
//        BeanUtils.copyProperties(type,t);
//        return typeMapper.save(t);
//    }



    @Transactional
    @Override
    public void deleteType(Long id) {
        typeMapper.delete(id);
    }
}
