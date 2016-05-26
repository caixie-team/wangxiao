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
 * @ClassName com.atdld.os.sns.entity.blog.BlogReply
 * @description 回复博客
 * @Create Date : 2013-12-30 上午9:17:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BlogReply implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8091533346127000257L;
    private Long id;// 主键
    private Long cusId;// 用户id
    private String showName;// 会员名
    private Long blogId;// 博客id
    private String content;// 回复内容
    private java.util.Date addTime;// 回复时间
    private java.util.Date updateTime;// 更新时间
    private String shortContent;// 短内容
    private SnsUserExpandDto userExpandDto;// 获得用户信息
    private String modelStr;

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
        modelStr = StringUtils.getModelDate(addTime);
    }

    public String getShortContent() {
        shortContent = WebUtils.replaceTagHTML(content);
        // 截取长度超过截取30+...
        shortContent = StringUtils.getLength(shortContent, 30);
        return shortContent;
    }
}
