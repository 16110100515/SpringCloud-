package com.atguigu.scw.webui.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther crush
 * @create 2020-03-15-18:53
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @GetMapping("/blogs")
    public String blogs() {
        return "admin/blogs";
    }
}
