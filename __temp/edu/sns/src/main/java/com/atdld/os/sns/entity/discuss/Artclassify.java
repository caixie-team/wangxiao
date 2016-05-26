package com.atdld.os.sns.entity.discuss;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :
 * @ClassName com.atdld.os.core.entity.discuss.Artclassify
 * @description 文章分类
 * @Create Date : 2013-12-14 下午2:48:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Artclassify implements Serializable {

    private static final long serialVersionUID = 4781738945151171112L;

    private Long artId;// 文章分类id
    private String name;// 文章分类
    private int sort;// 排序
    private Date addTime;// 添加时间
    private int blogNum;// 博客数
    private int disArticleNum;// 小组文章数
}
