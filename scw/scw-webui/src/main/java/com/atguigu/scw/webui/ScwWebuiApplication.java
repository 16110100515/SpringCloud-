package com.atguigu.scw.webui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients //启动feign实现远程调用
@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
public class ScwWebuiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScwWebuiApplication.class, args);
    }

}
