package com.atdld.os.edu.entity.order;

import java.io.Serializable;

import lombok.Data;

@Data
public class TrxorderDetailDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String currentPirce;// 当前价格
	private String courseImgUrl; // 课程图片
	private String courseName;// 课程名称
	private String courseTitle; // 课程描述
	private String lessionNum;//课程数
	
}
