package io.wangxiao.edu.home.entity.library;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryLibrary implements Serializable {


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
