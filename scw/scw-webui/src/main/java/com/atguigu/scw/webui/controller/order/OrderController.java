package com.atguigu.scw.webui.controller.order;

import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.webui.bean.TReturn;
import com.atguigu.scw.webui.fegin.UserControllerFeginClient;
import com.atguigu.scw.webui.vo.ProjectDetailsRespVo;
import com.atguigu.scw.webui.vo.UserAddressVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author zya
 * @create 2019-12-19 16:15
 */
@RequestMapping("/order")
@Controller
@Slf4j
public class OrderController {
    @Resource
    UserControllerFeginClient userControllerFeginClient;
    @GetMapping("/pay-step-1.html/{rtnid}")
    public String payStep1(HttpSession session, @PathVariable("rtnid") Integer rtnid){
        //从session域中获取project对象和rtnid对应的回报对象
        ProjectDetailsRespVo projectDetailsRespVo = (ProjectDetailsRespVo)session.getAttribute("project");
        List<TReturn> projectReturns = projectDetailsRespVo.getProjectReturns();
        TReturn tReturn = null;
        for (TReturn projectReturn : projectReturns) {
            if (projectReturn.getId() == (int)rtnid){
                tReturn = projectReturn;
                break;
            }
        }
        session.setAttribute("rtn",tReturn);
        return "pages/pay-step-1";
    }
    @GetMapping("/pay-step-2.html/{rtncount}")
    public String toPayStepPage2(Model model, @RequestHeader("referer")String ref, HttpSession session, @PathVariable("rtncount") Integer rtncount){
        //判断用户是否登录
        Map user = (Map) session.getAttribute("user");
        if (CollectionUtils.isEmpty(user)){
            session.setAttribute("errorMsg","请登入后再支付！");
            session.setAttribute("ref", ref);
            return "redirect:/user/login.html";
        }
        //查询当前用户的地址信息，必须保证该用户已经登录
        //提交用户登录的token，后台可以验证身份查询用户的信息
        String  accessToken = (String) user.get("accessToken");
        //远程调用user项目实现地址列表的查询
        AppResponse<List<UserAddressVo>> userAddress = userControllerFeginClient.getUserAddress(accessToken);
        List<UserAddressVo> list = userAddress.getData();
        model.addAttribute("addresses",list);
        //用户需要结账
        model.addAttribute("count",rtncount);
        //生成防止订单提交页面的重复提交的token:解决订单重复创建的问题

        String token =  UUID.randomUUID().toString();
        session.setAttribute("token", token);


        session.setAttribute("rtncount",rtncount);

        return "pages/pay-step-2";
    }


}
