package com.atguigu.scw.webui.fegin;

import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.webui.bean.Type;
import com.atguigu.scw.webui.vo.UserAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author lsj
 * @create 2020-03-12 18:50
 */
@FeignClient(value = "SCW-USER",fallback = UserControllerFeginClientHandler.class)
public interface UserControllerFeginClient {
    @PostMapping("/admin/posttype")
    AppResponse<Object> posttype(@RequestParam("name") String name);
    @PostMapping("/admin/listByPage")
    AppResponse<Object> listByPage(@RequestParam("pageno") int pageno, @RequestParam("pagesize") int pagesize, @RequestParam("queryText") String queryText);//
    @PostMapping("/admin/doLogin")
    AppResponse<Object> login(@RequestParam("username") String username, @RequestParam("password") String password);
    @PostMapping("/user/doLogin")
    AppResponse<Object> doLogin(@RequestParam("loginacct") String loginacct,@RequestParam("userpswd") String userpswd);
    @GetMapping("/user/address")
    AppResponse<List<UserAddressVo>> getUserAddress(@RequestParam("accessToken")String accessToken);

}
