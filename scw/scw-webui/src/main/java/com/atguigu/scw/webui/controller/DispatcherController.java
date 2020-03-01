package com.atguigu.scw.webui.controller;

import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.webui.fegin.ProjectInfoControllerFegin;
import com.atguigu.scw.webui.vo.HotProjectRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author zya
 * @create 2019-12-16 18:02
 */
@Controller
@Slf4j
public class DispatcherController {
    @Autowired
    ProjectInfoControllerFegin projectInfoControllerFegin;

    //处理跳转分发请求
    @GetMapping(value = {"/index","/","/index.html"})
    public String toIndexPage(Model model){
        //查询所有项目列表显示到首页中
        //显示数据 项目头圆，项目name，项目简介，项目id
        AppResponse<List<HotProjectRespVo>> recommendIndex =
                projectInfoControllerFegin.getRecommendIndex();
        model.addAttribute("HotProjectInfo",recommendIndex.getData());
        return "pages/index";
    }
}
