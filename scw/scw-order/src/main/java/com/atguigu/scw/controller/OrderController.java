package com.atguigu.scw.controller;

import com.atguigu.scw.common.bean.AppResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author zya
 * @create 2019-12-12 11:43
 */
@RestController
@Api(tags = "订单模块")
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/paystep1/{rtnid}")
    @ApiOperation("创建订单的第一步")
    public AppResponse<Object> createOrderStepOne(HttpSession session, @PathVariable("rtnid") Integer rtnid){
      return null;

    }



    @PostMapping("/cancel")
    @ApiOperation(value = "取消订单")
    public String cancelOrder(){
        return "";
    }


    @PostMapping("/create")
    @ApiOperation(value = "创建订单")
    public String createOrder(){
        return "";
    }
    @PostMapping("/pay")
    @ApiOperation(value = "立即付款")
    public String payOrder(){
        return "";
    }

}
