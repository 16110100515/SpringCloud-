package com.atguigu.scw.controller.admin;

import com.atguigu.scw.bean.Type;
import com.atguigu.scw.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther crush
 * @create 2020-03-15-23:19
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @ResponseBody
    @RequestMapping("/testsavetype")
    public int testsavetype(){
        Type t1 = new Type();
        t1.setName("老司机");
        return typeService.saveType(t1);
    }

}
