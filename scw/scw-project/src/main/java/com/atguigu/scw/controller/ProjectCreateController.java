package com.atguigu.scw.controller;

import com.alibaba.fastjson.JSON;
import com.atguigu.scw.bean.TReturn;
import com.atguigu.scw.common.bean.AppResponse;
import com.atguigu.scw.common.utils.ScwAppUtils;
import com.atguigu.scw.service.ProjectService;
import com.atguigu.scw.utils.OSSTemplate;
import com.atguigu.scw.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zya
 * @create 2019-12-12 14:11
 */
@Api(tags = "项目发起模块")
@RestController
@RequestMapping("/project/create")
@Slf4j
public class ProjectCreateController {
    @Autowired
    OSSTemplate ossTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    ProjectService projectService;

    // 阅读并同意协议：初始化ProjectRedisStorageVo对象，分配唯一的token
    @PostMapping("/init")
    @ApiOperation(value = "项目初始创建")
    public AppResponse<Object> initProject(String accessToken){
        UserRespVo objectFromRedis = ScwAppUtils.getObjectFromRedis(stringRedisTemplate, UserRespVo.class, accessToken);
        if (objectFromRedis == null){
            return AppResponse.fail("初始化发布项目失败","请登入后再发布项目");
        }
        ProjectRedisStorageVo bigVo = new ProjectRedisStorageVo();
        bigVo.setMemberid(objectFromRedis.getId());
        bigVo.setAccessToken(accessToken);
        //生成临时token
        String projectToken ="project:create:temp"+ UUID.randomUUID().toString().replace("-","");
        bigVo.setProjectToken(projectToken);
        ScwAppUtils.setObjectToJson(bigVo,stringRedisTemplate,projectToken);
        return AppResponse.ok(bigVo);
    }

//    第一步 ：收集项目信息，项目发起人信息
    @PostMapping("/submit")
    @ApiOperation(value = "项目提交审核申请")
    public AppResponse<Object> confirmLegal(ProjectBaseInfoVo vo){
//    检查登入状态
        String accessToken = vo.getAccessToken();
        UserRespVo objectFromRedis = ScwAppUtils.getObjectFromRedis(stringRedisTemplate, UserRespVo.class, accessToken);
        if (objectFromRedis == null){
            return AppResponse.fail("初始化发布项目失败","请登入后再发布项目");
        }
//    获取redis中的bigVo
        ProjectRedisStorageVo bigVo = ScwAppUtils.getObjectFromRedis(stringRedisTemplate, ProjectRedisStorageVo.class, vo.getProjectToken());
//    将接受的vo对象的属性值对拷给bigVo
        BeanUtils.copyProperties(vo,bigVo);
//    价格修改后的bigVo重新设置到redis中覆盖之前的vo
        ScwAppUtils.setObjectToJson(bigVo,stringRedisTemplate,bigVo.getProjectToken());
        return AppResponse.ok(bigVo);
    }

    //@ResponseBoby表示响应的数据转为json响应，@RequestBody表示需要接受json类型的请求数据并自动将json对象转为java中的对象
    //第二步 回报信息收集
    @PostMapping("/return")
    @ApiOperation(value = "添加项目回报档位")
    public AppResponse<Object> returnProject(@RequestBody List<ProjectReturnVo> rtnVos){
        if(CollectionUtils.isEmpty(rtnVos)){
            return AppResponse.fail("没有获取到回报信息","请设置回报后再提交");
        }
        ProjectReturnVo projectReturnVo = rtnVos.get(0);
        String accessToken = projectReturnVo.getAccessToken();
        //    检查登入状态
        UserRespVo objectFromRedis = ScwAppUtils.getObjectFromRedis(stringRedisTemplate, UserRespVo.class, accessToken);
        if (objectFromRedis == null){
            return AppResponse.fail("初始化发布项目失败","请登入后再发布项目");
        }
//        获取redis中的bigvo对象
        String projectToken = projectReturnVo.getProjectToken();
        ProjectRedisStorageVo bigVo = ScwAppUtils.getObjectFromRedis(stringRedisTemplate, ProjectRedisStorageVo.class, projectToken);
        if (bigVo == null){
            return AppResponse.fail("收集回报信息失败","数据读取异常");
        }
        //        将return信息设置给bigVo
        List<TReturn> rtns = new ArrayList<>();
        for (ProjectReturnVo rtnVo : rtnVos) {
            TReturn tReturn = new TReturn();
            BeanUtils.copyProperties(rtnVo,tReturn);
            rtns.add(tReturn);
        }
        bigVo.setProjectReturns(rtns);
//        将bigVo设置到redis中
        ScwAppUtils.setObjectToJson(bigVo,stringRedisTemplate,projectToken);
        return AppResponse.ok(bigVo);
    }

    @PostMapping("/confirm/legal")
    @ApiOperation(value = "确认项目及法人信息")
    public AppResponse<Object> projectSubmit(ProjectConfirmInfoVo vo){
       //检查登入状况
        String accessToken = vo.getAccessToken();
        UserRespVo objectFromRedis = ScwAppUtils.getObjectFromRedis(stringRedisTemplate, UserRespVo.class, accessToken);
        if (objectFromRedis == null){
            return AppResponse.fail("初始化发布项目失败","请登入后再发布项目");
        }
        //        获取redis中的bigvo对象
        String projectToken = vo.getProjectToken();
        ProjectRedisStorageVo bigVo = ScwAppUtils.getObjectFromRedis(stringRedisTemplate, ProjectRedisStorageVo.class, projectToken);
        if (bigVo == null){
            return AppResponse.fail("收集回报信息失败","数据读取异常");
        }
        //将法人信息设置给bigVo
        bigVo.getProjectInitiator().setAccount(vo.getAccount());
        bigVo.getProjectInitiator().setIdcard(vo.getIdcard());
        //将bigVo对象转为数据库表对应的bean对象，然后存到数据库中
        log.info("接受封装了所有项目数据的bigVo对象:{}",bigVo);
        if (vo.getType()== 0) {
            projectService.createProject(bigVo);
            //保存成功，删除redis中的缓存
            stringRedisTemplate.delete(vo.getProjectToken());
            return AppResponse.ok("提交成功");
        }else {
            //保存草稿
            stringRedisTemplate.opsForValue().set(vo.getProjectToken(), JSON.toJSONString(bigVo),7, TimeUnit.DAYS);
            return AppResponse.ok("保存草稿成功");
        }
    }



    @DeleteMapping("/return")
    @ApiOperation(value = "删除项目回报档位")
    public void deleteReturnProject(){

    }

    @PostMapping("/tempsave")
    @ApiOperation(value = "项目草稿保存")
    public void saveTempProject(){

    }


    @PostMapping("/upload")
    @ApiOperation(value = "上传图片")
    public AppResponse<Object> uploadPhoto(MultipartFile[] imgs){
        if(ArrayUtils.isEmpty(imgs)){
            return AppResponse.fail("上传失败","请选择图片后上传");
        }
        List<String> imgPaths = new ArrayList<>();
        for (MultipartFile img : imgs) {
            String s = ossTemplate.uploadImg(img);
            imgPaths.add(s);
        }
        return AppResponse.ok(imgPaths);
    }


}
