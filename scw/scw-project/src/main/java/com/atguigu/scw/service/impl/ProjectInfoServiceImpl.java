package com.atguigu.scw.service.impl;

import com.atguigu.scw.bean.*;
import com.atguigu.scw.mapper.TProjectImagesMapper;
import com.atguigu.scw.mapper.TProjectInitiatorMapper;
import com.atguigu.scw.mapper.TProjectMapper;
import com.atguigu.scw.mapper.TReturnMapper;
import com.atguigu.scw.service.ProjectInfoService;
import com.atguigu.scw.vo.HotProjectRespVo;
import com.atguigu.scw.vo.ProjectDetailsRespVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zya
 * @create 2019-12-17 18:07
 */
@Service
public class ProjectInfoServiceImpl implements ProjectInfoService {
    @Resource
    TProjectMapper projectMapper;
    @Resource
    TProjectImagesMapper projectImagesMapper;
    @Resource
    TReturnMapper tReturnMapper;
    @Resource
    TProjectInitiatorMapper tProjectInitiatorMapper;
    @Override
    public List<HotProjectRespVo> getRecommendIndex() {
        return projectMapper.selectHotProject();
    }

    @Override
    public ProjectDetailsRespVo getProjectInfoDetail(Integer id) {
        ProjectDetailsRespVo vo = new ProjectDetailsRespVo();
        TProject tProject = projectMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(tProject,vo);
        //detailsImage
        TProjectImagesExample example = new TProjectImagesExample();
        example.createCriteria().andProjectidEqualTo(id);
        List<TProjectImages> tProjectImages = projectImagesMapper.selectByExample(example);
        List<String> imges = new ArrayList<>();
        for (TProjectImages tProjectImage : tProjectImages) {
            if (tProjectImage.getImgtype()==0){
                vo.setHeaderImage(tProjectImage.getImgurl());
            }else {
                imges.add(tProjectImage.getImgurl());
            }
        }
        vo.setDetailsImage(imges);
//        List<TReturn> projectReturns;项目回报
        TReturnExample example2 = new TReturnExample();
        example2.createCriteria().andProjectidEqualTo(id);
        List<TReturn> tReturns = tReturnMapper.selectByExample(example2);
        vo.setProjectReturns(tReturns);
        // 发起人信息：自我介绍，详细自我介绍，联系电话，客服电话
//        private TProjectInitiator projectInitiator;项目发起人信息
        TProjectInitiatorExample example3 = new TProjectInitiatorExample();
        example3.createCriteria().andProjectidEqualTo(id);
        List<TProjectInitiator> tProjectInitiators = tProjectInitiatorMapper.selectByExample(example3);
        vo.setProjectInitiator(tProjectInitiators.get(0));
        return vo;
    }
}
