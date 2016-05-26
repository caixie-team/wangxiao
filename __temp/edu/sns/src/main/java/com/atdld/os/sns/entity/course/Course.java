package com.atdld.os.sns.entity.course;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class Course implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String name;//课程名称
    private Long isavaliable;//0可用(上架)1不可用(下架)
    private java.util.Date addtime;
    private java.math.BigDecimal sourceprice;//课程原价格（只显示）
    private java.math.BigDecimal currentprice;//课程销售价格（实际支付价格）设置为0则可免费观看
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
    private Long pageBuycount;
    private Long pageViewcount;
    private String freeurl;
    private String sellType;//课程的销售方式,COURSE,PACKAGE
    private Long recommendId;//推荐分类模块id
    private Long subjectId;
    @DateTimeFormat(pattern="yyyy-mm-dd hh-mm-ss")
    private Date liveBeginTime;//直播开始时间
    @DateTimeFormat(pattern="yyyy-mm-dd hh-mm-ss")
    private Date liveEndTime;//直播结束时间

}
