package com.atdld.os.api.course.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryCourse implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 4550896941810655734L;
    private Long id;
    private Long subjectId;
    private String name;
    private Long isavaliable;
    private Date addtime;
    private Integer isPay;
    private java.math.BigDecimal sourceprice;
    private java.math.BigDecimal currentprice;
    private String title;
    private String context;
    private Long lessionnum;
    private String coursetag;
    private String logo;
    private String mobileLogo;
    private Date updateTime;
    private int losetype;
    private Date loseAbsTime;
    private String loseTime;
    private String updateuser;
    private Long membertype;
    private Long pageBuycount;
    private Long pageViewcount;
    private String freeurl;
    private Long teacherId;//查询课程传入教师id
    private int order;//查询课程传入排序标识
    private String sellType;//课程的销售方式,COURSE,PACKAGE
    private List<Teacher> teacherList;//该课程 下的老师list
    private Long userId;		//学员id
    @DateTimeFormat(pattern="yyyy-mm-dd hh-mm-ss")
    private Date liveBeginTime;//直播开始时间
    @DateTimeFormat(pattern="yyyy-mm-dd hh-mm-ss")
    private Date liveEndTime;//直播结束时间
    private int status;//直播状态 1未开始 2进行中 3已结束 
}
