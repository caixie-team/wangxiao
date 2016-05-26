package com.atdld.os.sns.entity.weibo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;

/**
 * @author :
 * @ClassName com.atdld.os.core.entity.weibo.QueryComment
 * @description 回复的查询类
 * @Create Date : 2013-12-13 下午12:44:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryComment implements Serializable {
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
    private String cusName;// 用户名称
    private String email;// 用户邮箱
    private String showname = "";// 会员名
    private String shortContent;// 缩短内容
    private String avatar;// 头像
    private String modelStr;//字符串时间

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
        //整理后的时间
        modelStr = StringUtils.getModelDate(addTime);
    }

    public String getShortContent() {
        // 去掉html代码
        shortContent = WebUtils.replaceTagHTML(content);
        // 截取长度超过截取30+...
        shortContent = StringUtils.getLength(shortContent, 70);
        return shortContent;
    }

}
