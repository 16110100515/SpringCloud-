package com.atguigu.scw.webui.controller.user;

import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.webui.bean.Type;
import com.atguigu.scw.webui.fegin.UserControllerFeginClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * @auther crush
 * @create 2020-03-16-13:37
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Resource
    UserControllerFeginClient userControllerFeginClient;

    @RequestMapping ("/types")
    public String types(@RequestParam(value="pageno",required=false,defaultValue="1")  int pageno,
                        @RequestParam(value="pagesize",required=false,defaultValue="5") int pagesize,
                        @RequestParam(value="queryText",required=false,defaultValue="")String queryText, Model model){
        AppResponse appResponse = userControllerFeginClient.listByPage(pageno,pagesize,"");
        model.addAttribute("page",appResponse.getData());
        return "admin/types";
    }
    @RequestMapping("/types/input")
    public String input(){
        return "admin/types-input";
    }
    @RequestMapping("/types/save")
    public String post(Type type, RedirectAttributes attributes){
        String name = type.getName();
        AppResponse appResponse = userControllerFeginClient.posttype(name);
        if(Integer.parseInt(appResponse.getData().toString())!=1){
            attributes.addFlashAttribute("message","操作失败");
            throw new RuntimeException("删除失败 from com.atguigu.scw.webui.controller.user.TypeController");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }

        return "redirect:/admin/types";
    }
}
