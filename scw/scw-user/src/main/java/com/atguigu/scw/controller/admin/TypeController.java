package com.atguigu.scw.controller.admin;

import com.atguigu.scw.bean.Type;
import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.service.TypeService;
import com.atguigu.scw.utils.Page;
import com.atguigu.scw.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther crush
 * @create 2020-03-15-23:19
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("/testsavetype")
    @ResponseBody
    public int testsavetype(){
        Type t1 = new Type();
        t1.setName("老司机666");
        return typeService.updateType(new Long(10),t1);
    }
    //设计Map聚合存储需要给页面的对象数据
    private Map<String,Object> result = new HashMap<String,Object>();

    /**
     * 分页查询
     */
    @RequestMapping("/listByPage")
    @ResponseBody
    public AppResponse listByPage(@RequestParam(value="pageno",required=false,defaultValue="1")  int pageno,
                                  @RequestParam(value="pagesize",required=false,defaultValue="10") int pagesize,
                                  @RequestParam(value="queryText",required=false,defaultValue="")String queryText){
        Map paramMap = new HashMap();
        paramMap.put("pageno", pageno);
        paramMap.put("pagesize", pagesize);
        if (StringUtil.isNotEmpty(queryText)){
            paramMap.put("queryText", queryText); //   \%

        }
        Page page = typeService.queryUserPage(paramMap);

        return AppResponse.ok(page);
    }
    @PostMapping("/posttype")
    @ResponseBody
    public AppResponse<Object> posttype(String name){
        Type type = new Type();
        type.setName(name);
        return AppResponse.ok(typeService.saveType(type));
    }
    @PostMapping("/getById")
    @ResponseBody
    AppResponse<Object> getById(@RequestParam("id") Long id){

        return AppResponse.ok(typeService.getType(id));
    }
    @PostMapping("/editPost")
    @ResponseBody
    AppResponse<Object> editPost(@RequestParam("id") Long id,@RequestParam("name") String name){
        Type type = new Type();
        type.setName(name);
        return AppResponse.ok(typeService.updateType(id,type));
    }

}
