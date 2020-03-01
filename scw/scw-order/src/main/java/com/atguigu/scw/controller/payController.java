package com.atguigu.scw.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zya
 * @create 2019-12-12 11:27
 */
@RequestMapping("/order/pay")
@RestController
@Api(tags = "支付模块")
public class payController {

    @PostMapping("/alipay")
    @ApiOperation(value = "支付宝支付")
    public String alipay(){
        return "";
    }
    @PostMapping("/weixin")
    @ApiOperation(value = "微信支付")
    public String weixinpay(){
        return "";
    }
}
