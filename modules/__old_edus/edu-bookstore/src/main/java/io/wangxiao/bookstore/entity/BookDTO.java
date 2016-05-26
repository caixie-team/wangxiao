package io.wangxiao.bookstore.entity;

import java.io.Serializable;
import java.util.Date;

public class BookDTO implements Serializable{


    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(float nowPrice) {
        this.nowPrice = nowPrice;
    }

    public float getRebatePrice() {
        return rebatePrice;
    }

    public void setRebatePrice(float rebatePrice) {
        this.rebatePrice = rebatePrice;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookSubjectid() {
        return bookSubjectid;
    }

    public void setBookSubjectid(Integer bookSubjectid) {
        this.bookSubjectid = bookSubjectid;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getBookSmallimg() {
        return bookSmallimg;
    }

    public void setBookSmallimg(String bookSmallimg) {
        this.bookSmallimg = bookSmallimg;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

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
