package io.wangxiao.library.entity;

import java.io.Serializable;
import java.util.Date;

public class QueryLibrary implements Serializable{


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getSubjectOneId() {
        return subjectOneId;
    }

    public void setSubjectOneId(Long subjectOneId) {
        this.subjectOneId = subjectOneId;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(int browseNum) {
        this.browseNum = browseNum;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

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
