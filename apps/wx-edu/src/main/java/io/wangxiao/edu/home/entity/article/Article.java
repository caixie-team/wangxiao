package io.wangxiao.edu.home.entity.article;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class Article implements Serializable {

    private Long id;// 主键
    private String title;// 标题
    private String meta;// 标签
    private String description;// 简介（自动生成，前200个汉字）
    private String content;// 内容
    private String picture;// 图片
    private java.util.Date createTime;// 创建时间
    private java.util.Date updateTime;// 修改时间
    private String author;// 作者
    private Long clickTimes;// 点击数量
    private String type;//类型
}
