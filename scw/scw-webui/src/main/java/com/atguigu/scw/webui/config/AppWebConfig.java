package com.atguigu.scw.webui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lsj
 * @create 2020-03-12 18:07
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
