package com.atguigu.scw.webui.controller.user;

import com.atguigu.scw.common.bean.AppResponse;
//import com.atguigu.scw.webui.fegin.LoginControllerFeignClient;
import com.atguigu.scw.webui.fegin.UserControllerFeginClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author lsj
 * @create 2020-03-12 18:27
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    UserControllerFeginClient userControllerFeginClient;
    @Resource
//    LoginControllerFeignClient loginControllerFeignClient;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @PostMapping("/doLogin_blog")
    public String doLogin_blog(HttpSession session,String username, String password, Model model){
        //远程调用scw-user项目处理登入的业务
        //根据远程调用的controller创建接口FeginClient
        //自动装配FeginClient
        AppResponse<Object> response = userControllerFeginClient.login(username, password);
        if ("00000".equals(response.getCode())){
            session.setAttribute("user",response.getData());
            session.removeAttribute("errorMsg");
            //登入成功，重定向到首页响应给用户
            if (session.getAttribute("ref")!=null){
                return "redirect:"+session.getAttribute("ref");
            }else {
//                return "redirect:/index";
                return response.getMessage();
//                return "blogs/index";  //刷新的时候表单会重复提交
            }
        }else{
            //登入失败，设置错误消息到域中，转发到登入页面提示
            String message = response.getMessage();
            session.setAttribute("errorMsg",message);
            return response.getData().toString();
        }
    }

    @PostMapping("/doLogin")
    public String doLogin(HttpSession session,String loginacct, String userpswd, Model model){
        //远程调用scw-user项目处理登入的业务
        //根据远程调用的controller创建接口FeginClient
        //自动装配FeginClient
        AppResponse<Object> response = userControllerFeginClient.doLogin(loginacct, userpswd);
        if ("00000".equals(response.getCode())){
            session.setAttribute("user",response.getData());
            session.removeAttribute("errorMsg");
            //登入成功，重定向到首页响应给用户
            if (session.getAttribute("ref")!=null){
                return "redirect:"+session.getAttribute("ref");
            }else {
//                return "redirect:/index";
                return "redirect:/toBlogsIndex";
//                return "blogs/index";  //刷新的时候表单会重复提交
            }
        }else{
            //登入失败，设置错误消息到域中，转发到登入页面提示
            String message = response.getMessage();
            session.setAttribute("errorMsg",message);
            return "pages/login";
        }
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
//        stringRedisTemplate.delete("user:login:");//这里删不掉，需要再scw-user中处理
//        return "redirect:/index";
        return "redirect:/admin/login";
    }

}
