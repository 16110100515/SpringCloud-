package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//@EnableEurekaClient
@EnableDiscoveryClient   //启用eureka客户端功能
@SpringBootApplication
public class CloudProviderMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudProviderMovieApplication.class, args);
	}

}
