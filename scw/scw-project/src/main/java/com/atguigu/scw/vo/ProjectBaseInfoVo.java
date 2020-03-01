package com.atguigu.scw.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author zya
 * @create 2019-12-18 20:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectBaseInfoVo extends BaseVo {

    private String projectToken;// 项目的临时token

    private List<Integer> typeids; // 项目的分类id

    private List<Integer> tagids; // 项目的标签id

    private String name;// 项目名称

    private String remark;// 项目简介

    private Integer money;// 筹资金额

    private Integer day;// 筹资天数

    private String headerImage;// 项目头部图片

    private List<String> detailsImage;// 项目详情图片
}
