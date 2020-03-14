//package com.atguigu.scw.webui.fegin;
//
//import com.atguigu.scw.common.bean.AppResponse;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
///**
// * @auther crush
// * @create 2020-03-14-16:44
// */
//@FeignClient(value = "SCW-USER",fallback = LoginControllerFeignClientHandler.class)
//public interface LoginControllerFeignClient {
//    @PostMapping("/admin/doLogin")
//    AppResponse<Object> login(@RequestParam("username") String username, @RequestParam("password") String password);
//}
