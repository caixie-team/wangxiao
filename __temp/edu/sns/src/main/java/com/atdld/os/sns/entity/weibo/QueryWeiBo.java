package com.atdld.os.sns.entity.weibo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;

/**
 * @author :
 * @ClassName com.atdld.os.core.entity.weibo.QueryWeiBo
 * @description 微博查询类
 * @Create Date : 2013-12-13 下午12:45:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryWeiBo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2972088766561758830L;
    private Long id;// 主键Id
    private Long cusId;// 用户id
    private Date addTime;// 添加时间
    private Date updateTime;// 更新时间
    private String content;// 微博内容
    private int praiseNum;// 被赞数
    private int commentNum;// 评论数
    private String cusName;// 用户名
    private String photo;// 头像
    private int praiseCusId;// 用户是否关注了微博的id
    private int favoriteId;// 用户是否收藏了微博的id
    private Long cusAttentionId;// 该微博用户是否被我关注过的表的id
    private Long friendId;// 该微博用户是否是我的好友的表的id
    private int top = -1;// 置顶数
    private Long weiBoNum;// 微博数
    private Long maxWeiBoId;// 最大微博id
    private String showname = "";// 会员名
    private String shortShowName;// 缩略会员名
    private String shortContent;// 缩短内容
    private String avatar;// 头像
    private String addTimeStr;
    private int status;
    private Long forwardId;//转发的微博id
    private int forwardNum;//转发的微博数
    private String forwardContent;//转发微博的内容
    private String modelStr;//字符串时间

    public String getShortShowName() {
        shortShowName = showname.substring(0, 8);
        return shortShowName;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
        //整理后的时间
        modelStr = StringUtils.getModelDate(addTime);
    }

    public String getShortContent() {
        // 去掉html代码
        shortContent = WebUtils.replaceTagHTML(content);
        // 截取长度超过截取30+...
        shortContent = StringUtils.getLength(shortContent, 45);
        return shortContent;
    }

    public int getCommentNum() {// 微博评论数不能为负数
        if (commentNum >= 0) {
            return commentNum;
        } else {
            return 0;
        }
    }
}
