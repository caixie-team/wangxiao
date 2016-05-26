package io.wangxiao.commons.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 
 * @description 用到唯一字段时生成
 */
public class GuidItem implements Serializable {

    @Getter
    @Setter
    private static Long defaultAutoId = 1L;// 主键
    @Getter
    @Setter
    private Long id;// 主键
    @Getter
    @Setter
    private Long autoId = defaultAutoId;// 自增值,在此定义默认值
    @Getter
    @Setter
    private String project;// 关键字
    @Getter
    @Setter
    private String description = "";// 描述

}
