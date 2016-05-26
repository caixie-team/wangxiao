package com.atdld.os.edu.entity.Book;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图书类
 * @ClassName  com.atdld.edu.book.domain.Book
 * @description
 * @author :
 * @Create Date : 2014年8月15日 下午2:11:11
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private Integer bookId;//主键Id
    private float price;//原价
    private float nowPrice;//现价
    private float rebatePrice;//折扣价格
    private String bookName;//图书名
    private Integer bookSubjectid;//专业Id（分类）
    private String bookImg;//图片地址
    private String bookSmallimg;//缩略图
    private Integer stockNum;//库存量
    private String author;//作者
    private String press;//出版社
    private java.util.Date publishTime;//出版时间
    private String revision;//版次
    private Integer printNum;//印刷次数
    private Integer pageNum;//页数
    private Integer wordNum;//字数
    private String bookPackage;//包装
    private String pager;//纸张
    private String weight;//重量
    private String isbn;//isbn号
    private String remarks;//备注
    private String bookInfo;//简介
    private String directory;//目录
    private Integer status;//1是正常2是冻结3为删除
    private java.util.Date addTime;//创建时间
    private Integer shopState;//上下架状态 1为下架2为上架
    private java.util.Date dropTime;//下架时间
    private java.util.Date upTime;//上架时间
    private java.util.Date validityTime;//到期日期
    private java.util.Date updateTime;//更新时间
    private String updateUser;//更新时间
    private Integer bookType;//图书类别
    private Integer bookSize;//开本
    private String disProperty;//显示属性
    private String subjectName;//专业名称
    private String payUrl;//支付链接地址
}
