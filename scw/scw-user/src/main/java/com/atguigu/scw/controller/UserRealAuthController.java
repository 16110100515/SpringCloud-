package com.atguigu.scw.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zya
 * @create 2019-12-12 10:44
 */
@RestController
@Api(tags = "用户实名审核模块")
@RequestMapping("/user/auth")
public class UserRealAuthController {
    @GetMapping("/start")
    @ApiOperation(value ="认证申请第1步-用户认证申请开始")
    public String startUserAuth(){
        return "";
    }
    @PostMapping("/baseinfo")
    @ApiOperation(value = "认证申请第2步-提交基本信息")
    public String postUserAuth(){
        return "";
    }
    @PostMapping("/certs")
    @ApiOperation(value = "认证申请第3步-上传资质信息")
    public String postUserCerts(){
        return "";
    }
    @PostMapping("/email")
    @ApiOperation(value = "认证申请第4步-确认邮箱信息")
    public String postUserEmail(){
        return "";
    }
    @PostMapping("/submit")
    @ApiOperation(value = "认证申请第5步-提交实名认证申请")
    public String submitUser(){
        return "";
    }
    @GetMapping("/certs2upload")
    @ApiOperation(value = "获取需要上传的资质信息")
    public String getUserCerts(){
        return "";
    }

}
