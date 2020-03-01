package com.atguigu.scw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients //启动feign实现远程调用
@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages="com.atguigu.scw.mapper")
@EnableTransactionManagement
public class ScwProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScwProjectApplication.class, args);
    }

}
