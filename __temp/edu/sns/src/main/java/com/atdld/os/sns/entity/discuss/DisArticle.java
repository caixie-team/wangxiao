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
 * @ClassName com.atdld.os.core.entity.discuss.DisArticle
 * @description 文章
 * @Create Date : 2013-12-11 下午2:04:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DisArticle implements Serializable {

    private static final long serialVersionUID = 4553504635564541526L;

    private Long id;// 文章Id
    private String title;// 文章题目
    private String content;// 文章内容
    private Date createTime;// 发表时间
    private Long cusId;// 发表作者
    private int countView;// 浏览次数
    private Long groupId;// 文章所属小组
    private Long artClassifyId;// 文章分类
    private int reNum;// 回复的数量
    private int top;// 置顶
    private String disname;// 关联小组名字
    private String showName;// 会员名
    private String classifyName;// 分类名称
    private String lastReply;// 最后回复人
    private Date lastTime;// 最后回复时间
    private String shortContent;// 小组文章内容
    private SnsUserExpandDto userExpandDto;// 用户信息
    private int selType;//类型1原创2转载
    private int status;//状态 0正常1禁言
    private String summary;//摘要
    private int favorCount;//喜欢
    private int recomCount;//推荐数
    private String modelStr;
    private String lastStr;
    private String groupName;//小组名称

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

    public int getReNum() {
        if (reNum >= 0) {
            return reNum;
        }
        return 0;
    }
}
