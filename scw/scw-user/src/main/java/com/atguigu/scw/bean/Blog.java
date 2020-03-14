package com.atguigu.scw.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther crush
 * @create 2020-03-13-22:46
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id
//    @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    private Date createTime;
    private Date updateTime;

    private Type type;
    private List<Tag> tags = new ArrayList<>();
    private User user;
    private List<Comment> comments = new ArrayList<>();


}