package com.atguigu.scw.utils;

import com.atguigu.scw.exception.SendMessageException;
import com.google.gson.Gson;
import lombok.Data;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zya
 * @create 2019-12-13 20:46
 */
@Data
//发送短信相关的工具方法的模板类
public class SmsTemplate {
    private String host;
    private String path;
    private String method;
    private String appcode;

    //发送短信
    public void sendMessage(Map<String, String> querys) throws Exception {
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);

        Map<String, String> bodys = new HashMap<String, String>();



            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                // 响应成功
                //获取response的body 响应体内容
                String body = EntityUtils.toString(response.getEntity());
                //System.out.println();
                Gson gson = new Gson();
                Map map = gson.fromJson(body, Map.class);
                System.out.println(map);
                if ("00000".equals(map.get("return_code"))) {
                    //短信发送成功
                    System.out.println("短信发送成功,订单编号：" + map.get("order_id"));
                } else if ("10002".equals(map.get("return_code"))) {
                    //短信发送失败：模板不存在
                    throw new SendMessageException("短信发送失败,模板不存在");
                } else {
                    //短信发送失败
                    throw new SendMessageException("短信发送失败");
                }

            } else {
                //响应失败
                throw new SendMessageException("短信发送出现异常");
            }

    }
}
