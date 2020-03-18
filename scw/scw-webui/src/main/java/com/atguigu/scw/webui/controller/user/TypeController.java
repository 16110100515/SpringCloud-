package com.atguigu.scw.webui.controller.user;

import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.webui.bean.Type;
import com.atguigu.scw.webui.fegin.UserControllerFeginClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;

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

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", userControllerFeginClient.getById(id).getData());
        return "admin/types-input-change";
    }

    @PostMapping("/types/{id}")
    public String editPost( Type type, @PathVariable Long id, RedirectAttributes attributes) {
//        Type type1 = typeService.getTypeByName(type.getName());
//        if (type1 != null) {
//            result.rejectValue("name","nameError","不能添加重复的分类");
//        }
//        if (result.hasErrors()) {
//            return "admin/types-input";
//        }
        String name = type.getName();
        AppResponse appResponse = userControllerFeginClient.editPost(id,name);

//        Type t = typeService.updateType(id,type);
        if (Integer.parseInt(appResponse.getData().toString())!=1) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

//    @GetMapping("/types/{id}/delete")
//    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
//        typeService.deleteType(id);
//        attributes.addFlashAttribute("message", "删除成功");
//        return "redirect:/admin/types";
//    }

}
