package com.atdld.os.edu.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6849058590636336328L;
	private Long id;// 主键 id
	private String nickname="";//用户名
	private String email="";// 邮件
	private int emailIsavalible=0;// 邮件是否验证
	private String mobile="";// 手机号
	private int mobileIsavalible=0;// 手机号是否验证
	private String password;
	private int isavalible=0;// 是否激活(0正常 1冻结)
	private String customerkey;
	private Date createdate;
	private String userip="";
	
	private String startDate;//开始时间
	private String endDate;//结束时间
	private String courseName;//课程名称 用于查询
    private String registerFrom;//账号来源
}
