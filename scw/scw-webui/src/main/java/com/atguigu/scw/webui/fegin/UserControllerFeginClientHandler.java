package com.atguigu.scw.webui.fegin;

import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.webui.bean.Type;
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
    public AppResponse<Object> posttype(String name) {
        return AppResponse.fail("加载失败","远程调用超时，连接失败fromUserControllerFeginClientHandler");
    }

    @Override
    public AppResponse<Object> listByPage(int pageno, int pagesize,String queryText) {
        return AppResponse.fail("加载失败","远程调用超时，连接失败fromUserControllerFeginClientHandler");
    }

    @Override
    public AppResponse<Object> login(String username, String password) {
        return AppResponse.fail("加载失败","远程调用超时，连接失败fromUserControllerFeginClientHandler");
    }
    @Override
    public AppResponse<Object> doLogin(String loginacct, String userpswd) {
        return AppResponse.fail("登入失败","远程调用超时，连接失败fromUserControllerFeginClientHandler");
    }

    @Override
    public AppResponse<List<UserAddressVo>> getUserAddress(String accessToken) {
        return null;
    }


}
