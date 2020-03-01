package com.atguigu.scw.controller;

import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.common.utils.ScwAppUtils;
import com.atguigu.scw.service.UserService;
import com.atguigu.scw.vo.UserAddressVo;
import com.atguigu.scw.vo.UserRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zya
 * @create 2019-12-12 10:00
 */
@Api(tags = "用户信息模块")
@RestController
@RequestMapping("/user/info")
public class UserInfoController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    UserService userService;


    @GetMapping()
    @ApiOperation(value = "获取个人信息")
    public String getUserInfo(){
        return "";
    }
    @PostMapping
    @ApiOperation(value = "更新个人信息")
    public String updateInfo(){
        return "";
    }


    @GetMapping("/address")
    @ApiOperation(value = "获取用户收货地址")
    public AppResponse<List<UserAddressVo>> getUserAddress(@RequestParam("accessToken")String accessToken){
        UserAddressVo userAddressVo = new UserAddressVo();
        //验证登录状态
        UserRespVo userRespVo = ScwAppUtils.getObjectFromRedis(stringRedisTemplate, UserRespVo.class, accessToken);
        if(userRespVo==null) {
            return AppResponse.fail(null, "登录超时");
        }
        //获取登录状态的用户对象的 id
        Integer userRespVoId = userRespVo.getId();
        //根据用户id获取他的地址列表
        List<UserAddressVo> list = userService.getUserAddress(userRespVoId);
        return AppResponse.ok(list);

    }



    @PostMapping("/address")
    @ApiOperation(value = "新增用户收货地址")
    public String addUserAddress(){
        return "";
    }
    @DeleteMapping("/address")
    @ApiOperation(value = "删除用户收货地址")
    public String deleteUserAddress(){
        return "";
    }
    @PutMapping("/address")
    @ApiOperation(value = "修改用户收货地址")
    public String updateUserAddress(){
        return "";
    }
    @GetMapping("/create/project")
    @ApiOperation(value = "获取我发起的项目")
    public String getUserCreateProject(){
        return "";
    }
    @GetMapping("/message")
    @ApiOperation(value = "获取我的系统消息")
    public String getUserMessage(){
        return "";
    }
    @GetMapping("/order")
    @ApiOperation(value = "查看我的订单")
    public String getUserOrder(){
        return "";
    }
    @DeleteMapping("/order")
    @ApiOperation(value = "删除我的订单")
    public String deleteUserOrder(){
        return "";
    }
    @GetMapping("/star/project")
    @ApiOperation(value = "获取我关注的项目")
    public String getUserStarProject(){
        return "";
    }
    @GetMapping("/support/project")
    @ApiOperation(value = "获取我支持的项目")
    public String getUserSupportProject(){
        return "";
    }

}
