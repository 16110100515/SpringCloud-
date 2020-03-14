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
        registry.addViewController("/toBlogsIndex").setViewName("blogs/index");
        registry.addViewController("/toBlogsIndex2").setViewName("index2");
        registry.addViewController("/blog").setViewName("blog");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/archives").setViewName("archives");
        registry.addViewController("/tags").setViewName("tags");
        registry.addViewController("/types").setViewName("types");
        registry.addViewController("/blogs").setViewName("admin/blogs");
        registry.addViewController("/blogs-input").setViewName("admin/blogs-input");
        registry.addViewController("/admin/index").setViewName("admin/index");
        registry.addViewController("/admin/login").setViewName("admin/login");
    }
}
