package io.wangxiao.edu.app.entity.website;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * banner图管理
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppWebsiteImages implements Serializable {


    private Long id;
    private String imagesUrl;//banner图地址
    private Long courseId;//banner图连接地址
    private String title;//banner图标题
    /**
     * indexCenterBanner,//首页banner图轮播
     */
    private String keyWord;//关键字
    private Integer seriesNumber;//序列号
    private String color;
    private String previewUrl;
    private String name;
}
