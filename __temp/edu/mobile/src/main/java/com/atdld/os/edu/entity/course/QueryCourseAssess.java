package com.atdld.os.edu.entity.course;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName com.atdld.os.edu.entity.course.QueryCourseAssess
 * @description 课程评论查询
 * @author :
 * @Create Date : 2014年9月23日 上午11:59:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryCourseAssess implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long userId;
	private Long courseId;
	private Long kpointId;
	private String content;
	private int status;
	private java.util.Date createTime;
	private String nickname = "";// 用户名
	private String email = "";// 邮件
	private int emailIsavalible = 0;// 邮件是否验证
	private String mobile = "";// 手机号
	private int mobileIsavalible = 0;// 手机号是否验证
	private String password;
	private int isavalible = 0;// 是否激活(0冻结 1已激活)
	private String customerkey;
	private Date createdate;
	private String userip = "";
	private String avatar;// 头像
	private String courseName;//课程名
	private String pointName;//课程名
	private String startDate;//开始时间
	private String endDate;//结束时间
}
