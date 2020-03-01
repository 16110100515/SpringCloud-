package com.atguigu.scw.webui.controller.project;

import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.webui.fegin.ProjectInfoControllerFegin;
import com.atguigu.scw.webui.vo.ProjectDetailsRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author zya
 * @create 2019-12-17 19:54
 */
@Controller
public class ProjectController {
    @Autowired
    ProjectInfoControllerFegin projectInfoControllerFegin;
    @GetMapping("/project.html")
    public String toProjectPage(@RequestParam("id") Integer id, HttpSession session){
//        远程调用project模块的查询id对应的项目详细
        AppResponse<ProjectDetailsRespVo> projectInfoDetail =
                projectInfoControllerFegin.getProjectInfoDetail(id);
        session.setAttribute("project",projectInfoDetail.getData());
        return "pages/project";
    }
}
