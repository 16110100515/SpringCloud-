package com.atguigu.scw.vo;

import com.atguigu.scw.bean.TProjectInitiator;
import com.atguigu.scw.bean.TReturn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author lsj
 * @create 2020-03-04 15:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectRedisStorageVo extends BaseVo {
    //全量   增量
    private String projectToken;//项目的临时token  以后项目发起的多步请求 都是通过此token获取redis中的 vo对象进行修改
    private Integer memberid;//会员id
    private List<Integer> typeids; //项目的分类id
    private List<Integer> tagids; //项目的标签id
    private String name;//项目名称
    private String remark;//项目简介
    private Integer money;//筹资金额
    private Integer day;//筹资天数
    private String headerImage;//项目头部图片
    private List<String> detailsImage;//项目详情图片
    private List<TReturn> projectReturns;//项目回报
//发起人信息：自我介绍，详细自我介绍，联系电话，客服电话
    private TProjectInitiator projectInitiator;
}
