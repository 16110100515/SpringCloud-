package com.atguigu.scw.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zya
 * @create 2019-12-17 18:17
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HotProjectRespVo {
    private Integer id;
    private String name;
    private String remark;
    private String imgurl;

}
