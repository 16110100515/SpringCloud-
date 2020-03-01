package com.atguigu.scw.service;

import com.atguigu.scw.vo.HotProjectRespVo;
import com.atguigu.scw.vo.ProjectDetailsRespVo;

import java.util.List;

/**
 * @author zya
 * @create 2019-12-17 18:06
 */

public interface ProjectInfoService {
    List<HotProjectRespVo> getRecommendIndex();

    ProjectDetailsRespVo getProjectInfoDetail(Integer id);
}
