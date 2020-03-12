package com.atguigu.scw.webui.fegin;

import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.webui.vo.UserAddressVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lsj
 * @create 2020-03-12 18:55
 */
@Service
public class UserControllerFeginClientHandler implements UserControllerFeginClient {
    @Override
    public AppResponse<Object> doLogin(String loginacct, String userpswd) {
        return AppResponse.fail("登入失败","远程调用超时，连接失败");
    }

    @Override
    public AppResponse<List<UserAddressVo>> getUserAddress(String accessToken) {
        return null;
    }


}
