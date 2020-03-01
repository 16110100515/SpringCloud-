package com.atguigu.scw.controller;

import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.service.ProjectInfoService;
import com.atguigu.scw.vo.HotProjectRespVo;
import com.atguigu.scw.vo.ProjectDetailsRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zya
 * @create 2019-12-12 11:49
 */
@RestController
@RequestMapping("/project")
@Api(tags = "项目信息模块")
public class ProjectInfoController {

    @Autowired
    ProjectInfoService projectInfoService;

    @GetMapping("/adv")
    @ApiOperation(value = "获取首页广告项目")
    public String getAdv(){
        return "";
    }
    @GetMapping("/all/index")
    @ApiOperation(value = "获取项目总列表")
    public void getAll() {

    }
    @GetMapping("/info/detail")
    @ApiOperation(value = "获取项目详细信息")
    public AppResponse<ProjectDetailsRespVo> getProjectInfoDetail(@RequestParam("id")Integer id) {
        ProjectDetailsRespVo vo = projectInfoService.getProjectInfoDetail(id);
        return AppResponse.ok(vo);
    }


    @GetMapping("/recommend/index")
    @ApiOperation(value = "获取首页热门项目推荐")
    public AppResponse<List<HotProjectRespVo>> getRecommendIndex() {
        //显示数据 项目头圆，项目name，项目简介，项目id
        return AppResponse.ok(projectInfoService.getRecommendIndex());
    }


    @GetMapping("/recommend/type")
    @ApiOperation(value = "获取首页分类项目推荐")
    public void getRecommendType() {

    }
    @GetMapping("/return/info")
    @ApiOperation(value = "获取项目回报档位信息")
    public void getReturnInfo(){

    }
    @GetMapping("/sys/tags")
    @ApiOperation(value = "获取项目系统标签信息")
    public void getSystemTags(){

    }
    @GetMapping("/sys/type")
    @ApiOperation(value = "获取项目系统分类信息")
    public void getSystemType(){

    }
}
