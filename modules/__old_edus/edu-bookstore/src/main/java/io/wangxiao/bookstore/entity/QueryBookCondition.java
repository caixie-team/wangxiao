package io.wangxiao.bookstore.entity;

import java.io.Serializable;
import java.util.Date;

public class QueryBookCondition implements Serializable{

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getBookSubjectid() {
        return bookSubjectid;
    }

    public void setBookSubjectid(Integer bookSubjectid) {
        this.bookSubjectid = bookSubjectid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public Date getDropTime() {
        return dropTime;
    }

    public void setDropTime(Date dropTime) {
        this.dropTime = dropTime;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getShopState() {
        return shopState;
    }

    public void setShopState(Integer shopState) {
        this.shopState = shopState;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDisProperty() {
        return disProperty;
    }

    public void setDisProperty(String disProperty) {
        this.disProperty = disProperty;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getSujectIds() {
        return sujectIds;
    }

    public void setSujectIds(String sujectIds) {
        this.sujectIds = sujectIds;
    }

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
