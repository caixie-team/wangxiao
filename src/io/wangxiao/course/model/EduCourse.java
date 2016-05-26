package io.wangxiao.course.model;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

/*
* 
* gen by beetlsql 2016-05-25
*/
public class EduCourse  {
	//课程编号
	private Integer id ;
	//课程类型id
	private Integer classTypeId ;
	//0免费1付费
	private Integer isPay ;
	//是否限制人数，0否，1是
	private Integer isRestrict ;
	//0可用(上架)1不可用(下架)
	private Integer isavaliable ;
	//是否预约，0否，1是
	private Integer isreserve ;
	//课时数
	private Integer lessionnum ;
	//有效期类型，0：到期时间，1：按天数
	private Integer losetype ;
	//学时(分钟)
	private Integer minutes ;
	//排序
	private Integer order ;
	//初始化假的销售数量
	private Integer pageBuycount ;
	//初始化假的浏览数量
	private Integer pageViewcount ;
	//播放时长
	private Integer playTime ;
	//限制人数
	private Integer restrictnum ;
	//系统用户id
	private Integer sysUserId ;
	//总课时数
	private Integer totalLessionnum ;
	//专业类型课程
	private Integer typeSubjectId ;
	//内部，部门课程
	private String companySellType ;
	//课程详情
	private String context ;
	//课程标签，多个以空格间隔
	private String coursetag ;
	//课程销售价格（实际支付价格）设置为0则可免费观看
	private Double currentprice ;
	//试听地址
	private String freeurl ;
	//讲课时间
	private String lectureTime ;
	//图片路径
	private String logo ;
	//有效期:商品订单过期时间点
	private String loseTime ;
	//课程名称
	private String name ;
	//销售形式：课程、套餐
	private String sellType ;
	private String showType ;
	//课程原价格（只显示）
	private Double sourceprice ;
	//直播课程，教师链接地址
	private String teacherVideoUrl ;
	//课程简介
	private String title ;
	//最后更新人
	private String updateuser ;
	//直播课程，教师密码
	private String videoPassword ;
	//添加时间
	private Timestamp addtime ;
	//直播开始时间
	private Timestamp liveBeginTime ;
	//直播结束时间
	private Timestamp liveEndTime ;
	//有效期:商品订单过期时间段。比如用户购买后10天后，则此订单过期
	private Timestamp loseAbsTime ;
	//最后更新时间
	private Timestamp updateTime ;

}
