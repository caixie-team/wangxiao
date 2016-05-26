package io.wangxiao.edu.home.entity.course;

import io.wangxiao.edu.home.entity.member.MemberType;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class Course implements Serializable {

    private Long id;
    private String name;// 课程名称
    private Long isavaliable;// 0可用(上架)1不可用(下架)
    private java.util.Date addtime;
    private int isPay;
    private java.math.BigDecimal sourceprice;// 课程原价格（只显示）
    private java.math.BigDecimal currentprice;// 课程销售价格（实际支付价格）设置为0则可免费观看
    private String title;
    private String context;
    private Long lessionnum;// 课时数
    private Long totalLessionnum;//总课时数
    private Long minutes = 0L;// 学时数
    private String coursetag;
    private String logo;
    private java.util.Date updateTime;
    private int losetype;
    private java.util.Date loseAbsTime;
    private String loseTime;
    private String updateuser;
    private List<MemberType> memberTypes;// 会员类型集合
    private Long pageBuycount = 0L;
    private Long pageViewcount = 0L;
    private String freeurl;
    private String sellType;// 课程的销售方式,COURSE,PACKAGE
    private Long recommendId;// 推荐分类模块id
    private String teacherIds;
    private List<Teacher> teacherList;// 该课程 下的老师list
    private Long subjectId;
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private Date liveBeginTime;// 直播开始时间
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private Date liveEndTime;// 直播结束时间
    private int isReserve;// 是否预约审核
    private Long order;// 排序
    private int isRestrict;// 直播是否限定人数
    private Long restrictNum;// 直播限定人数
    private Long orderNum; // 排序值
    private String companySellType;// 课程权限:内部课程INNER，外部课程OUTTER
    /**
     * ----------------------------------------------------
     **/
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private Date lectureTime;// 讲课时间
    private Integer typeSubjectId;// 专业类型课程

    private String groupIds;// 员工id集合
    private String teacherVideoUrl;// 视频教师开播地址
    private String videoPassword;// 视频密码
    private Long classTypeId;// 课程类型id
    private Long sysUserId;// 系统用户id
    private String ids;// 课程id组

    private Long playTime = 0L;//播放时长(总计)
}
