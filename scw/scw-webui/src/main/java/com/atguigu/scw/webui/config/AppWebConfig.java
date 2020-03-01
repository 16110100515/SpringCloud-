package com.atguigu.scw.webui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zya
 * @create 2019-12-16 18:38
 */
@Configuration
public class AppWebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("pages/index");
//        registry.addViewController("/index").setViewName("pages/index");
//        registry.addViewController("/index.html").setViewName("pages/index");
        registry.addViewController("/user/login.html").setViewName("pages/login");
    }
}
