package com.atguigu.scw.webui.fegin;

import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.webui.vo.UserAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zya
 * @create 2019-12-16 19:07
 */
@FeignClient(value = "SCW-USER",fallback = UserControllerFeginClientHandler.class)
public interface UserControllerFeginClient {
    @PostMapping("/user/doLogin")
    AppResponse<Object> doLogin(@RequestParam("loginacct") String loginacct,@RequestParam("userpswd") String userpswd);
    @GetMapping("/user/address")
    AppResponse<List<UserAddressVo>> getUserAddress(@RequestParam("accessToken")String accessToken);

}
