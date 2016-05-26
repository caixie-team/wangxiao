package com.atdld.os.sns.entity.discuss;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;

/**
 * @author :
 * @ClassName com.atdld.os.core.entity.discuss.DisArticleReply
 * @description 文章回复
 * @Create Date : 2013-12-11 下午2:04:50
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DisArticleReply implements Serializable {

    private static final long serialVersionUID = 575040252343448416L;

    private Long id;// 回復id
    private Long groupId;// 小组id
    private Long articleId;// 文章Id
    private String replyContent;// 回复内容
    private Date replyTime;// 回复时间
    private Long recusId;// 回复用户Id
    private String showName;// 会员名
    private SnsUserExpandDto userExpandDto;// 用户信息
    private String modelStr;
    private String shortContent; 

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
        modelStr = StringUtils.getModelDate(replyTime);
    }
    
    public String getShortContent() {
        shortContent = WebUtils.replaceTagHTML(replyContent);
        // 截取长度超过截取30+...
        shortContent = StringUtils.getLength(shortContent, 30);
        return shortContent;
    }


}