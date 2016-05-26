package io.wangxiao.edu.home.entity.Book;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName  com.business.platform.edu.entity.Book.BookDTO
 * @description
 * @author :xull
 * @Create Date : 2014年10月28日 下午1:06:16
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BookDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5756368802612785862L;
	
	private Integer bookId;//主键Id
    private float price;//原价
    private float nowPrice;//现价
    private float rebatePrice;//折扣价格
    private String bookName;//图书名
    private Integer bookSubjectid;//专业Id（分类）
    private String bookImg;//图片地址
    private String bookSmallimg;//缩略图
    private String author;//作者
    private String press;//出版社
    private java.util.Date publishTime;//出版时间
    private String subjectName;//专业名称
    private String payUrl;//支付链接地址

}
