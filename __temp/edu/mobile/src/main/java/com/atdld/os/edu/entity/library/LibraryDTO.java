package com.atdld.os.edu.entity.library;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName com.atdld.edu.library.domain.Library
 * @description
 * @author :
 * @Create Date : 2014年8月11日 下午2:08:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LibraryDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6194657438794919239L;
	
	private Long id;// 主键
	private Long subjectId;// 专业Id
	private String subjectName;// 专业名称
	private String name;//文库名
	private String describe;// 描述
	private int type;// 1:pdf 2:图片集
	private int browseNum;// 浏览次数
	private String intro;// 简介
	private String pdfUrl;// pdf文件地址
	private String imgUrl;// 图片集文件地址
	private Date addTime;
}
