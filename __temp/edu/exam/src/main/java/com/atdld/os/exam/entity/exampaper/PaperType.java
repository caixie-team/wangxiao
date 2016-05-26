package com.atdld.os.exam.entity.exampaper;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.entity.BaseEntity;


/**
 * @author
 * @ClassName Paper
 * @package com.atdld.os.exam.entity.exampaper
 * @description
 * @Create Date: 2013-9-27 下午7:17:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PaperType extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 2863916048289247448L;
    /**
     * Id
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String describtion;
    /**
     * 按钮title
     */
    private String buttonTitle;
    /**
     * 图片url
     */
    private String imgUrl;
    /**
     * 添加时间
     */
    private Date addTime;
    //状态0为显示1为隐藏
    private int state;
    /**
     * 排序字段
     */
    private int sort;
}
