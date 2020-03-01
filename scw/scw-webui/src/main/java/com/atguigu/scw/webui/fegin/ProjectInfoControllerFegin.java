package com.atguigu.scw.webui.fegin;

import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.webui.vo.HotProjectRespVo;
import com.atguigu.scw.webui.vo.ProjectDetailsRespVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zya
 * @create 2019-12-17 19:23
 */
@FeignClient(value = "SCW-PROJECT")
public interface ProjectInfoControllerFegin {
    @GetMapping("/project/recommend/index")
    AppResponse<List<HotProjectRespVo>> getRecommendIndex();

    @GetMapping("/project/info/detail")
    AppResponse<ProjectDetailsRespVo> getProjectInfoDetail(@RequestParam("id")Integer id);
}
