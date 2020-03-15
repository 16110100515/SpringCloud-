package com.atguigu.scw.controller.admin;

//import com.lrm.po.User;
//import com.lrm.service.UserService;
import com.atguigu.scw.bean.User;
import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.consts.UserAppConsts;
import com.atguigu.scw.service.UserService;
import com.atguigu.scw.utils.MD5Utils;
import com.atguigu.scw.vo.UserRespVo;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by limi on 2017/10/15.
 */
@Controller
@RequestMapping("/admin")
public class LoginController {


    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

//    @ApiOperation("登录方法")
//    @PostMapping("/doLogin")
//    public AppResponse<Object> doLogin(String loginacct , String userpswd){
//        UserRespVo vo = null;
//        try {
//            vo = userService.doLogin(loginacct,userpswd);
//            //将vo转为json字符串
//            String token = UUID.randomUUID().toString().replace("-", "");
//            token= UserAppConsts.USER_LOGIN_PREFIX+ token;
//            vo.setAccessToken(token);
//            Gson gson = new Gson();
//            String voStr = gson.toJson(vo);
//            //将vo存到redis中
//            return AppResponse.ok(vo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return AppResponse.fail("登录失败", e.getMessage());
//        }
//    }

    @PostMapping("/doLogin")
    @ResponseBody
    public AppResponse<Object> login( String username, String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, MD5Utils.code(password));
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user",user);
            AppResponse appResponse = new AppResponse();
            appResponse.setCode("00000");
            appResponse.setMessage("admin/index");
            appResponse.setData(user);
            return  appResponse;
//            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return AppResponse.fail("redirect:/admin/login","密码不对  from  scw-user logincontroller提示");
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
