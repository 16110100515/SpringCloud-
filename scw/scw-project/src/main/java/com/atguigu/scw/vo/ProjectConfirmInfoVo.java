package com.atguigu.scw.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zya
 * @create 2019-12-19 9:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectConfirmInfoVo extends BaseVo{
    @ApiModelProperty("项目之前的临时token")
    private String projectToken;// 项目的临时token

    private String account;
    private String idcard;
    private Integer type;//提交时的类型：0提交 ， 1,表示保存草稿

}
