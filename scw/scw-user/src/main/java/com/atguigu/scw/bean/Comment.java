package com.atguigu.scw.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther crush
 * @create 2020-03-13-22:54
 */
@NoArgsConstructor //无参构造器
@AllArgsConstructor // 所有参数的构造器 //如果注解可以用代表 依赖引入成功， 如果注解用了 没有生成对应的方法，证明sts没有安装lombok插件
@Data // get set方法
@ToString // toString方法
@Slf4j  // 在当前类中引入slf4j的 log对象  用来打印日志
public class Comment {
    @Id
//    @GeneratedValue
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;//头像
//    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;


    private Blog blog;
    private List<Comment> comments = new ArrayList<>();
    private Comment parentComment;
}
