package com.atdld.os.app.entity.website;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * banner图管理
 * @ClassName  com.atdld.os.edu.entity.website.WebsiteImages
 * @description
 * @author :
 * @Create Date : 2014年6月9日 下午1:52:49
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class AppWebsiteImages implements Serializable{


	private static final long serialVersionUID = 1L;
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
