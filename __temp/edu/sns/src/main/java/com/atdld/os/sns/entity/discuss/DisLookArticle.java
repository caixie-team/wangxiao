package com.atdld.os.sns.entity.discuss;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :
 * @ClassName com.atdld.os.core.entity.discuss.DisLookArticle
 * @description 文章浏览
 * @Create Date : 2013-12-11 下午2:05:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DisLookArticle implements Serializable {

    private static final long serialVersionUID = -9110161214103037519L;

    private Long id;// 浏览记录Id
    private Long bizId;// 浏览文章id
    private int type;// 0博客1小组文章
    private Long cusId;// 用户id
    private Date addTime;// 浏览的时间
    private String showName;// 浏览用户
    private String viewDay;// 每天记一次存年月日
    private String avatar;// 头像
}
