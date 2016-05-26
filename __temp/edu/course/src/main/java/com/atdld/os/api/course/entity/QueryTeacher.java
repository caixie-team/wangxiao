package com.atdld.os.api.course.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryTeacher implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -2260935476109762530L;
    private Long id;		//教师id
    private String name;	//教师名称
    private String education;//资质
    private String career;
    private Long isStar;	//
    private String picPath;	//图片地址
    private Long status;	
    private java.util.Date createTime;	//
    private java.util.Date updateTime;
    private List<Course> courseList;//课程列表
    
}
