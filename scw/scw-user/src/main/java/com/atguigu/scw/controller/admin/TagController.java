package com.atguigu.scw.controller.admin;

import com.atguigu.scw.bean.Tag;
import com.atguigu.scw.bean.Type;
import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.service.TypeService;
import com.atguigu.scw.utils.Page;
import com.atguigu.scw.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther crush
 * @create 2020-03-19-21:18
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("/testsavetype_tag")
    @ResponseBody
    public int testsavetype_tag(){
        Type t1 = new Type();
        t1.setName("老司机666");
        return typeService.updateType(new Long(10),t1);
    }
    //设计Map聚合存储需要给页面的对象数据
    private Map<String,Object> result = new HashMap<String,Object>();

    /**
     * 分页查询
     */
    @RequestMapping("/listByPage_tag")
    @ResponseBody
    public AppResponse listByPage_tag(@RequestParam(value="pageno",required=false,defaultValue="1")  int pageno,
                                  @RequestParam(value="pagesize",required=false,defaultValue="10") int pagesize,
                                  @RequestParam(value="queryText",required=false,defaultValue="")String queryText){
        Map paramMap = new HashMap();
        paramMap.put("pageno", pageno);
        paramMap.put("pagesize", pagesize);
        if (StringUtil.isNotEmpty(queryText)){
            paramMap.put("queryText", queryText); //   \%

        }
        Page page = typeService.queryUserPage_tag(paramMap);

        return AppResponse.ok(page);
    }
    @PostMapping("/posttype_tag")
    @ResponseBody
    public AppResponse<Object> posttype_tag(String name){
        Tag tag = new Tag();
        tag.setName(name);
        return AppResponse.ok(typeService.saveType_tag(tag));
    }
    @PostMapping("/getById_tag")
    @ResponseBody
    AppResponse<Object> getById_tag(@RequestParam("id") Long id){

        return AppResponse.ok(typeService.getType_tag(id));
    }
    @PostMapping("/editPost_tag")
    @ResponseBody
    AppResponse<Object> editPost_tag(@RequestParam("id") Long id,@RequestParam("name") String name){
        Tag tag = new Tag();
        tag.setName(name);
        return AppResponse.ok(typeService.updateType_tag(id,tag));
    }
    @PostMapping("/delete_tag")
    @ResponseBody
    AppResponse<Object> delete_tag(@RequestParam("id") Long id){

        return AppResponse.ok(typeService.deleteType_tag(id));
    }

}
