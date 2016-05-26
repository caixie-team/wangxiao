package io.wangxiao.edu.home.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserIntegralGift implements Serializable {
    private Long id;//id
    private String name;//礼品名称
    private Long score;//所需分数
    private String logo;//图片
    private String content;//内容
    private java.util.Date createTime;//创建时间
    private java.util.Date updateTime;//更新时间
    private Long courseId;//课程id
    private Long status;//状态1为正常，2为删除
    //课程礼品所需属性
    private String courseName;//课程名
    private String courseTitle;//简介
    private String courseLogo;//课程图片
}
