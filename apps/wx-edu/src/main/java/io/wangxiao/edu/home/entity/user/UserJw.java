package io.wangxiao.edu.home.entity.user;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class UserJw implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long jwStudentId;	//教务学员id
	private Long netSchoolUserId;	//网校学员id
	private String studentName;	//学员名称
	private String phoneNumber;	//手机号
	private String email;	//邮箱
	
}
