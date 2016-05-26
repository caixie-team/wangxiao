package com.atdld.os.sns.entity.weibo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;

/**
 * @author :
 * @ClassName com.atdld.os.core.entity.weibo.WeiBo
 * @description 微博实体
 * @Create Date : 2013-12-13 下午12:51:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WeiBo implements Serializable {

    private static final long serialVersionUID = -2972088766561758830L;
    private Long id;// 主键Id
    private Long cusId;// 用户id
    private Date addTime;// 添加时间
    private Date updateTime;// 更新时间
    private String content;// 微博内容
    private int praiseNum;// 被赞数
    private int commentNum; // 评论数
    private int top;// 置顶数
    private String showname = "";// 会员名
    private String shortWeiBo;// 短微薄
    private String avatar;// 微博用户头像
    private int status;// 状态.0: 公开,1:不公开
    private Long forwardId;//转发的微博id
    private int forwardNum;//转发的微博数
    private String forwardContent;//转发微博的内容

    public String getShortWeiBo() {
        // 去掉html代码
        shortWeiBo = WebUtils.replaceTagHTML(content);
        // 截取长度超过截取30+...
        shortWeiBo = StringUtils.getLength(shortWeiBo, 30);
        return shortWeiBo;
    }

    public int getCommentNum() {// 微博评论数不能为负数
        if (commentNum >= 0) {
            return commentNum;
        } else {
            return 0;
        }

    }

}
