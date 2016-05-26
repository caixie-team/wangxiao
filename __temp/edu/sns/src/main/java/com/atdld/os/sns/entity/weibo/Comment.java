package com.atdld.os.sns.entity.weibo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :
 * @ClassName com.atdld.os.core.entity.weibo.Comment
 * @description 回复
 * @Create Date : 2013-12-13 下午12:40:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Comment implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2972088766561758830L;
    private Long id;// 主键Id
    private Long cusId;// 用户id
    private String content;// 微博内容
    private Long weiboId;// 微博id
    private Date addTime;// 添加时间
    private Date updateTime;// 更新时间
    private String showname = "";// 会员名

}
