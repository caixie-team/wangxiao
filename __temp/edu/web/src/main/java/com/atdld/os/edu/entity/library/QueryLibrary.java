package com.atdld.os.edu.entity.library;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName  com.atdld.edu.library.condition.QueryLibraryCondition
 * @description
 * @author :
 * @Create Date : 2014年8月14日 上午9:45:34
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class QueryLibrary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8094839272456036098L;
	
	private Long id;// 主键
	private Long subjectId;//专业Id
	private Long subjectOneId;//一级专业Id
	private int orderNum;//派讯
    private String name;//文库名
    private String describe;// 描述
    private int type;// 1:pdf 2:图片集
    private int browseNum;// 浏览次数
    private String intro;// 简介
    private String pdfUrl;// pdf文件地址
    private String imgUrl;// 图片集文件地址
    private Date addTime;

}
