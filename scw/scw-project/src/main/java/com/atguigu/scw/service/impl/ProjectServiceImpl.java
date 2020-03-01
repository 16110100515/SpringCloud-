package com.atguigu.scw.service.impl;

import com.atguigu.scw.bean.*;
import com.atguigu.scw.common.utils.AppDateUtils;
import com.atguigu.scw.mapper.*;
import com.atguigu.scw.service.ProjectService;
import com.atguigu.scw.vo.ProjectRedisStorageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zya
 * @create 2019-12-19 11:03
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Resource
    TProjectMapper tProjectMapper;
    @Resource
    TProjectImagesMapper projectImagesMapper;
    @Resource
    TProjectTagMapper projectTagMapper;
    @Resource
    TProjectTypeMapper projectTypeMapper;
    @Resource
    TProjectInitiatorMapper projectInitiatorMapper;
    @Resource
    TReturnMapper tReturnMapper;


    @Override
    public void createProject(ProjectRedisStorageVo bigVo) {
        //拆分bigVo对象的数据 分解成 数据库表对应的bean的对象
        //TProject(需要先存 有外键)
        TProject tProject = new TProject();
        BeanUtils.copyProperties(bigVo,tProject);
        //手动设置初始化对拷失败的
        tProject.setMoney((long)(bigVo.getMoney()));
        tProject.setStatus("0");
        tProject.setCreatedate(AppDateUtils.getFormatTime());
        tProjectMapper.insertSelective(tProject);
        Integer projectId = tProject.getId();
        //TProjectImages
        List<TProjectImages> imagesList = new ArrayList<>();
        //转换头图信息
        TProjectImages headerProjectImages = new TProjectImages();
        headerProjectImages.setImgurl(bigVo.getHeaderImage());
        headerProjectImages.setImgtype((byte)0);
        headerProjectImages.setProjectid(projectId);
        imagesList.add(headerProjectImages);
        //转换详细图信息
        List<String> detailsImages = bigVo.getDetailsImage();
        for (String detailsImage : detailsImages) {
            TProjectImages d = new TProjectImages();
            d.setProjectid(projectId);
            d.setImgtype((byte)1);
            d.setImgurl(detailsImage);
            imagesList.add(d);
        }
        //批量插入
        projectImagesMapper.batchInsertProjectImages(imagesList);
        //TProjectTag
        //TProjectType
        List<Integer> tagids = bigVo.getTagids();
        List<Integer> typeids = bigVo.getTypeids();
        projectTagMapper.batchInsetProjectTagids(projectId,tagids);
        projectTypeMapper.batchInsetProjectTypeids(projectId,typeids);


        //TReturn
        List<TReturn> projectReturns = bigVo.getProjectReturns();
        for (TReturn projectReturn : projectReturns) {
            projectReturn.setProjectid(projectId);
            tReturnMapper.insertSelective(projectReturn);
        }
        //TProjectInitiator
        TProjectInitiator projectInitiator = bigVo.getProjectInitiator();
        projectInitiator.setProjectid(projectId);
        projectInitiatorMapper.insertSelective(projectInitiator);
    }
}
