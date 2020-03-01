package com.atguigu.scw.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author zya
 * @create 2019-12-12 14:25
 */
@RestController
@RequestMapping("/project/operation")
@Api(tags = "项目操作模块")
public class ProjectOperationController {
    @DeleteMapping("/delete")
    @ApiOperation(value = "项目删除")
    public void deleteProject(){

    }
    @GetMapping("/preshow")
    @ApiOperation(value = "项目预览")
    public void preshowProject(){

    }
    @GetMapping("/question")
    @ApiOperation(value = "项目问题查看")
    public void questionProject(){

    }
    @PostMapping("/question")
    @ApiOperation(value = "项目问题添加")
    public void addQuestion(){

    }
    @PostMapping("/question/answer")
    @ApiOperation(value = "项目问题答案添加")
    public void addQuestionAnswer(){

    }
    @PostMapping("/star")
    @ApiOperation(value = "项目关注")
    public void starProject(){

    }
    @DeleteMapping("/star")
    @ApiOperation(value = "项目取消关注")
    public void deleteStarProject(){

    }
    @PutMapping("/update")
    @ApiOperation(value = "项目修改")
    public void updateProject(){

    }


}
