package com.atdld.os.sns.entity.blog;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;

/**
 * @author :
 * @ClassName com.atdld.os.sns.entity.blog.QueryBlogAndReply
 * @description
 * @Create Date : 2013-12-31 下午1:06:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryBlogAndReply implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1120546584773911068L;

    private Long blogId;// 博客id
    private Long cusId;// 创建者
    private String showName;// 会员名
    private String title;// 博文标题
    private String content;// 博文内容
    private int viewCount;// 查看数
    private int replyCount;// 回复数
    private java.util.Date addTime;// 添加时间
    private Long recusId;// 用户id
    private java.util.Date readdTime;// 回复时间
    private String replyName;// 回复人名字
    private Date updateTime;// 更新时间
    private int blognum;// 博客数
    private String shortShowName;// 短名字
    private String shortReplyName;// 短回复人名字
    private SnsUserExpandDto userExpandDto;// 用户
    private String shortContent;// 缩短内容
    private int selType;// 标示1原创2转载
    private Long id;// 主键自增
    private int type;// 博客分类
    private int status;// 状态
    private int isBest;// 是否被财经采纳，0默认，1被采纳。超天才采纳后回调修改此值
    private int activity;// 活跃度 财经采纳+500回复+10，浏览+1
    private int top;// 置顶博文
    private String articleName;// 分类名字
    private int lineNum;// 行数
    private int maxId;// 最大id
    private String strTime;// 按时间查询
    private int flag;// 同步财经标示默认0未同步1同步
    private String summary;//摘要
    private String modelStr;
    private String upmodelStr;//更新时间

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        upmodelStr = StringUtils.getModelDate(updateTime);
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
        modelStr = StringUtils.getModelDate(addTime);
    }

    public String getShortShowName() {
        // 截取长度超过截取30+...
        shortShowName = StringUtils.getLength(showName, 6);
        return shortShowName;
    }

    public String getShortReplyName() {
        // 截取长度超过截取30+...
        shortReplyName = StringUtils.getLength(replyName, 6);
        return shortReplyName;
    }

    public String getShortContent() {
        // 去掉html代码
        shortContent = WebUtils.replaceTagHTML(content);
        // 截取长度超过截取30+...
        shortContent = StringUtils.getLength(shortContent, 50);
        return shortContent;
    }
}
