package com.atdld.os.edu.entity.suggest;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.edu.entity.user.UserExpandDto;

@Data
@EqualsAndHashCode(callSuper = false)
public class SugSuggest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 344955134777363393L;
    private Long id;// 主键
    private Long cusId;// 创建者
    private String title;// 标题
    private String content;// 建议内容
    private int type;// 类型 1务实2意境
    private int status;// 状态 0可回复1不可回复（采纳最佳答案后改为1 ）
    private Long courseId;// 课程id
    private Long kpointId;// 小节id
    private int replycount;// 回复数量
    private java.util.Date addtime;// 添加时间
    private int browseNum;// 浏览次数
    private int top;// 置顶
    private int heat;// 热度
    private String showname = "";// 会员名
    private Date updatetime;// 更新时间
    private Long acceptuid;// 采纳者id
    private String acceptshowname = "";// 采纳者会员名
    private String shortContent;// 缩短内容
    private String summary;// 内容摘要
    private UserExpandDto queryCustomer;

    public String getShortContent() {
        // 去掉html代码
        shortContent = WebUtils.replaceTagHTML(content);
        // 截取长度超过截取50+...
        shortContent = StringUtils.getLength(shortContent, 50);
        return shortContent;
    }

    public int getReplycount() {
        if (replycount >= 0) {
            return replycount;
        } else {
            return 0;
        }
    }
}
