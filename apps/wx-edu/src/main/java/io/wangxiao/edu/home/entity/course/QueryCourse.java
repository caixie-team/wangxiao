package io.wangxiao.edu.home.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryCourse implements Serializable {
    private Long id;
    private Long subjectId;
    private String name;
    private Long isavaliable;
    private java.util.Date addtime;
    private Integer isPay = -1;
    private java.math.BigDecimal sourceprice;
    private java.math.BigDecimal currentprice;
    private String title;
    private String context;
    private Long lessionnum;
    private String coursetag;
    private String logo;
    private java.util.Date updateTime;
    private int losetype;
    private java.util.Date loseAbsTime;
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
    private Long userId;        //学员id
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private Date liveBeginTime;//直播开始时间
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private Date liveEndTime;//直播结束时间
    private int status;//直播状态 1未开始 2进行中 3已结束

    private String companySellType;// 课程权限:内部课程INNER，外部课程OUTTER

    private int typeSubjectId;//专业类型
    private int classTypeSubjectId; //课程专业id
    private Long groupId; //学员组id
    private String showType;//展示形式
    private String groupIds;//学员组集合
    private String teacherName;//教师名称
    private Long sysUserId;//系统学员id

    private Long isGroup;// 是否是小组课程查询 1小组查询
}
