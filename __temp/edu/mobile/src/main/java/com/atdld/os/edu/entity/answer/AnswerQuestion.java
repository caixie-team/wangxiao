package com.atdld.os.edu.entity.answer;

import java.io.Serializable;
import java.util.Date;

import com.atdld.os.edu.entity.user.UserExpandDto;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 学生答疑提问表
 * */
@Data
@EqualsAndHashCode(callSuper = false)
public class AnswerQuestion implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 6869877857043193533L;
	
	private Long id;
	private Long userId;//提问者Id
	private String type;//提问的类型 课程或是考试
	private String content; //提问的内容
	private Date addTime;//添加时间
	private Long parentId;//提问节点的父Id或试卷的Id
	private Long sonId;//节点Id或者试题ID
	private int status;//状态  0正常1隐藏
	private int replyCount;//回复次数
	private int isReply;//是否已经有老师回复
	private String email;//用户邮箱
	private UserExpandDto queryCustomer;  
	private String showName;
	private String parentName;
	private String sonName;
	private String nickName;
	
	
	private String beginTime;//查询开始时间
	private String endTime;//结束时间
	private Long subjectId;
}
