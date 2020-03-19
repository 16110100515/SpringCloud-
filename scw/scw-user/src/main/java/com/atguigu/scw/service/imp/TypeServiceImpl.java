package com.atguigu.scw.service.imp;

import com.atguigu.scw.bean.Tag;
import com.atguigu.scw.bean.Type;
import com.atguigu.scw.exception.NotFoundException;
import com.atguigu.scw.mapper.TypeMapper;
import com.atguigu.scw.service.TypeService;
//import com.lrm.NotFoundException;
//import com.lrm.dao.TypeRepository;
//import com.lrm.po.Type;
import com.atguigu.scw.utils.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    public Page queryUserPage(Map<String,Object> paramMap) {

        Page page = new Page((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<Type> datas = typeMapper.queryList(paramMap);

        page.setDatas(datas);
        Integer totalsize = typeMapper.queryCount(paramMap);
        page.setTotalsize(totalsize);

        return page;
    }

    @Override//修改
    public int updateType(Long id, Type type) {
        Type t = typeMapper.findOne(id);
        if(t==null){
            throw new NotFoundException("id不存在");
        }
        return typeMapper.updateType(id,type);
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
    public int deleteType(Long id) {
        return typeMapper.delete(id);
    }

    @Override
    public int deleteType_tag(Long id) {
        return typeMapper.delete_tag(id);
    }

    @Override
    public int updateType_tag(Long id, Tag tag) {
        Tag t = typeMapper.findOne_tag(id);
        if(t==null){
            throw new NotFoundException("id不存在");
        }
        return typeMapper.updateType_tag(id,tag);
    }

    @Override
    public Tag getType_tag(Long id) {
        return typeMapper.findOne_tag(id);
    }

    @Override
    public int saveType_tag(Tag tag) {
        return typeMapper.save_tag(tag);
    }

    @Override
    public Page queryUserPage_tag(Map paramMap) {
        Page page = new Page((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<Tag> datas = typeMapper.queryList_tag(paramMap);

        page.setDatas(datas);
        Integer totalsize = typeMapper.queryCount_tag(paramMap);
        page.setTotalsize(totalsize);

        return page;
    }
}
