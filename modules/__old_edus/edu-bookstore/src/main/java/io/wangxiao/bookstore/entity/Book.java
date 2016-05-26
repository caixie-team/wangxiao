package io.wangxiao.bookstore.entity;

import org.beetl.sql.core.TailBean;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;

/**
 * 图书数据实体
 */
@Table(name="edu_book")
public class Book extends TailBean {

//    public Integer id;
//    public Integer getId(){
//        return this.id;
//    }
//    public void setId(int id){
//        this.id = id;
//    }
//    @AutoID
//    @AssignID
    public Integer getBookId() {

        return bookId;
    }

    public void setBookId(Integer bookId)
    {
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

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
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

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public Integer getPrintNum() {
        return printNum;
    }

    public void setPrintNum(Integer printNum) {
        this.printNum = printNum;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getWordNum() {
        return wordNum;
    }

    public void setWordNum(Integer wordNum) {
        this.wordNum = wordNum;
    }

    public String getBookPackage() {
        return bookPackage;
    }

    public void setBookPackage(String bookPackage) {
        this.bookPackage = bookPackage;
    }

    public String getPager() {
        return pager;
    }

    public void setPager(String pager) {
        this.pager = pager;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getShopState() {
        return shopState;
    }

    public void setShopState(Integer shopState) {
        this.shopState = shopState;
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

    public Date getValidityTime() {
        return validityTime;
    }

    public void setValidityTime(Date validityTime) {
        this.validityTime = validityTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    public Integer getBookSize() {
        return bookSize;
    }

    public void setBookSize(Integer bookSize) {
        this.bookSize = bookSize;
    }

    public String getDisProperty() {
        return disProperty;
    }

    public void setDisProperty(String disProperty) {
        this.disProperty = disProperty;
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

    @AssignID
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
