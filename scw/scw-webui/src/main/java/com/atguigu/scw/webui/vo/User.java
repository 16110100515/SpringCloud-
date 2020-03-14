package com.atguigu.scw.webui.vo;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther crush
 * @create 2020-03-14-14:24
 */
public class User {
    @Id
//    @GeneratedValue
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
//    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
//    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

//    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();
}
