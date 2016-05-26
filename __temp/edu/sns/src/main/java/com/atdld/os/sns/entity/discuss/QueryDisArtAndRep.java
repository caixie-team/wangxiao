package com.atdld.os.sns.entity.discuss;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;

/**
 * @author :
 * @ClassName com.atdld.os.core.entity.discuss.QueryDisArtAndRep
 * @description 查询文章和回复详情
 * @Create Date : 2013-12-14 上午11:10:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryDisArtAndRep implements Serializable {

    private static final long serialVersionUID = -7515931717178789014L;

    private Long id;// 文章Id
    private String title;// 文章题目
    private String content;// 文章内容
    private Date createTime;// 发表时间
    private Long cusId;// 发表作者
    private String showName;// 用户名
    private int countView;// 浏览次数
    private int artClassifyId;// 文章分类
    private int reNum;// 回复的数量
    private String name;// 关联小组名字
    private String cusName;// 关联用户名字
    private Long reId;// 回復id
    private Long groupId;// 小组id
    private Long dis_articleId;// 文章Id
    private String replyContent;// 回复内容
    private Date replyTime;// 回复时间
    private Long recusId;// 回复用户Id
    private int top;// 置顶
    private String lastReply;// 最后回复人
    private Date lastTime;// 最后回复时间
    private String shortContent;// 小组文章内容
    private int selType;//文章类型
    private int status;//状态 0正常1禁言
    private String summary;//摘要
    private int favorCount;//喜欢
    private int recomCount;//推荐数
    private String modelStr;
    private String lastStr;

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        modelStr = StringUtils.getModelDate(createTime);
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
        lastStr = StringUtils.getModelDate(lastTime);
    }

    public String getShortContent() {
        shortContent = WebUtils.replaceTagHTML(content);

        shortContent = StringUtils.getLength(shortContent, 50);
        return shortContent;
    }

}
