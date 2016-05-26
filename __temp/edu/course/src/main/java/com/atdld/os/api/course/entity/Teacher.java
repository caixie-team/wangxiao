package com.atdld.os.api.course.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName com.atdld.os.edu.entity.course.Teacher
 * @description 讲师实体
 * @author :
 * @Create Date : 2014年9月15日 下午10:03:42
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Teacher implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 838162101564081713L;
    private Long id;// 主键自增
    private String name;// 讲师名称
    private String education;// 讲师资历
    private String career;// 讲师简介
    private Long isStar;// 头衔 0高级讲师1首席讲师
    private String picPath;// 头像
    private Long status;// 状态:0正常1删除
    private java.util.Date createTime;// 创建时间
    private java.util.Date updateTime;// 更新时间
    private String checkboxFalg;
    private Long subjectId; //专业id
    private List<Course> courseList;		//课程集合
}
