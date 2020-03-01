package com.atguigu.scw;

import com.atguigu.scw.utils.SmsTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients //启动feign实现远程调用
@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages="com.atguigu.scw.mapper")

public class ScwUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScwUserApplication.class, args);
    }

    //注入smsTemplate 模板类的对象
    @ConfigurationProperties(prefix = "sms")//从配置文件中获取属性
    @Bean
    public SmsTemplate getSmsTemplate(){
        return new SmsTemplate();
    }
}
