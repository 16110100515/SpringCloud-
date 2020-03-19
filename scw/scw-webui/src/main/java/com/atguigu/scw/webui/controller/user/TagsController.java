package com.atguigu.scw.webui.controller.user;

import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.webui.bean.Tag;
import com.atguigu.scw.webui.bean.Type;
import com.atguigu.scw.webui.fegin.UserControllerFeginClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * @auther crush
 * @create 2020-03-19-21:09
 */
@Controller
@RequestMapping("/admin")
public class TagsController {
    @Resource
    UserControllerFeginClient userControllerFeginClient;

    @RequestMapping("/tags")
    public String types(@RequestParam(value="pageno",required=false,defaultValue="1")  int pageno,
                        @RequestParam(value="pagesize",required=false,defaultValue="3") int pagesize,
                        @RequestParam(value="queryText",required=false,defaultValue="")String queryText, Model model){
        AppResponse appResponse = userControllerFeginClient.listByPage_tag(pageno,pagesize,"");
        model.addAttribute("page",appResponse.getData());
        return "admin/tags";
    }
    @RequestMapping("/tags/input")
    public String input(){
        return "admin/tags-input";
    }
    @RequestMapping("/tags/save")
    public String post(Type type, RedirectAttributes attributes){
        String name = type.getName();
        AppResponse appResponse = userControllerFeginClient.posttype_tag(name);
        if(Integer.parseInt(appResponse.getData().toString())!=1){
            attributes.addFlashAttribute("message","操作失败");
            throw new RuntimeException("删除失败 from com.atguigu.scw.webui.controller.user.TypeController");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }

        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", userControllerFeginClient.getById_tag(id).getData());
        return "admin/tags-input-change";
    }

    @PostMapping("/tags/{id}")
    public String editPost(Tag tag, @PathVariable Long id, RedirectAttributes attributes) {
//        Type type1 = typeService.getTypeByName(type.getName());
//        if (type1 != null) {
//            result.rejectValue("name","nameError","不能添加重复的分类");
//        }
//        if (result.hasErrors()) {
//            return "admin/types-input";
//        }
        String name = tag.getName();
        AppResponse appResponse = userControllerFeginClient.editPost_tag(id,name);

//        Type t = typeService.updateType(id,type);
        if (Integer.parseInt(appResponse.getData().toString())!=1) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        AppResponse appResponse = userControllerFeginClient.delete_tag(id);
        if (Integer.parseInt(appResponse.getData().toString())!=1) {
            attributes.addFlashAttribute("message", "删除失败");
        } else {
            attributes.addFlashAttribute("message", "删除成功");
        }
        return "redirect:/admin/tags";
    }
}
