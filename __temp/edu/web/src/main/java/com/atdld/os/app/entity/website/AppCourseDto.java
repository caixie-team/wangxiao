package com.atdld.os.app.entity.website;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * app课程管理
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppCourseDto implements Serializable {
	private static final long serialVersionUID = 1383373953853661904L;
	private long mainId;//app课程id
	private long courseId;// 课程id
	private String courseName;//课程名称
	private Date addTime;//添加时间
	private int lessionnum;//总课时
	private int isavaliable;//状态
}
