package io.wangxiao.website.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: 分类类型表
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebsiteClassify implements Serializable {


    private Long id;//主键id
    private String name;//分类名称
    private String explain;//说明
    private String type;//所属模块
    private Long level;//级别
    private int sort;//排序值
}
