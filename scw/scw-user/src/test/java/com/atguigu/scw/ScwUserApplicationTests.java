package com.atguigu.scw;

import com.atguigu.scw.utils.HttpUtils;
import com.atguigu.scw.utils.SmsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScwUserApplicationTests {
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplatel;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SmsTemplate smsTemplate;
    @Test
    public  void testSendMessage2() {
        Map<String,String> querys = new HashMap<>();
        querys.put("mobile", "18642866683");
        querys.put("param", "code:"+666666);
        querys.put("tpl_id", "TP1711063");
        try {
            smsTemplate.sendMessage(querys);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void testSendMessage() {
        String host = "http://dingxin.market.alicloudapi.com";
        String path = "/dx/sendSms";
        String method = "POST";
        String appcode = "d35ada27625c445184c5594e2af20511";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", "18642866683");
        String code = UUID.randomUUID().toString().replace("-","").substring(0,6);
        querys.put("param", "code:"+code);
        querys.put("tpl_id", "TP1711063");
        Map<String, String> bodys = new HashMap<String, String>();


        try {
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
            int status = response.getStatusLine().getStatusCode();
            log.debug("请求响应的状态码:{}",status);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Test
    public void contextLoads() {
        logger.info("redisTemplate哈哈"+redisTemplate);
        Boolean k1 = stringRedisTemplatel.hasKey("k1");//直接能操作的方法一般是操作健的
        logger.info("k1是否存在"+k1);
        stringRedisTemplatel.opsForValue().set("k8","哈哈");
        stringRedisTemplatel.expire("k8",10, TimeUnit.SECONDS);

    }

}
