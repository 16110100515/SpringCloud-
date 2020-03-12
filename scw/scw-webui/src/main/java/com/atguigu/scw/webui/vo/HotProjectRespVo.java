package com.atguigu.scw.webui.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author lsj
 * @create 2020-03-12 18:02
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
