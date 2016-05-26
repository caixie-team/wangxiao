package io.wangxiao.edu.home.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryTeacher implements Serializable {
    private Long id;        //教师id
    private String name;    //教师名称
    private String education;//资质
    private String career;
    private Long isStar;    //
    private String picPath;    //图片地址
    private Long status;
    private java.util.Date createTime;    //
    private java.util.Date updateTime;
    private List<Course> courseList;//课程列表

}
