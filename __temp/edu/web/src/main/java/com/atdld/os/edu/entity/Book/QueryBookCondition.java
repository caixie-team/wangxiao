package com.atdld.os.edu.entity.Book;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 图书查询类
 * @ClassName  com.atdld.edu.book.condition.QueryBookCondition
 * @description
 * @author :
 * @Create Date : 2014年8月15日 下午3:00:49
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class QueryBookCondition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer bookId;//图书Id
	private String bookName;//图书名
	private String subjectName;
    private Integer bookSubjectid;//专业Id（分类）
    private String author;//作者
    private String isbn;//isbn号
    private String bookType;//
    private java.util.Date dropTime;//下架时间
    private java.util.Date upTime;//上架时间
    private String keyword;//关键字
    private Integer shopState;
    private Integer status;
    private String disProperty;
    private String orderNum;
    private String sujectIds;

}
