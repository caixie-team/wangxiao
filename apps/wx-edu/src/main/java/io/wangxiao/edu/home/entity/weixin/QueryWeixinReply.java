package io.wangxiao.edu.home.entity.weixin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询微信回复素材类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryWeixinReply implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     **/
    private Long id;

    /**
     * 消息创建时间
     **/
    private Date createTime;
    /**
     * 消息类型
     **/
    private int msgType;
    /**
     * 消息标题
     **/
    private String title;
    /**
     * 消息内容
     **/
    private String content;
    /**
     * 图片的url
     */
    private String imageUrl;
    /**
     * 关键字
     */
    private String keyword;

    /**
     * 点击图文消息跳转链接
     */
    private String url;
    /**
     * 更新 时间
     */
    private Date updateTime;

}