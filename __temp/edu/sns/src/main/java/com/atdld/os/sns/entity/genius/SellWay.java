package com.atdld.os.sns.entity.genius;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :
 * @ClassName com.atdld.os.sns.entity.genius.SellWay
 * @description
 * @Create Date : 2014年5月8日 下午4:24:50
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SellWay implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 2912334977728782996L;
    private String sellId;
    private String sellName;
    private String imagesUrl;
    private String vedioUrl;
    private String browseNum;// 商品浏览数量
}
