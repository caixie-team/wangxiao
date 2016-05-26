package io.wangxiao.edu.home.entity.suggest;

import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.home.entity.user.UserExpandDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class SugSuggestReply implements Serializable {
    private Long id;// 主键自增
    private Long cusId;// 回复者id
    private Long suggestId;// 建议id
    private String content;// 回复内容
    private int type;// 类型 1务实2意境
    private int isbest;// 是否最佳答案 0否1是
    private java.util.Date addtime;// 添加时间
    private String showname = "";// 会员名
    private String shortContent;// 缩短会员名
    private UserExpandDto userExpandDto;
    private String modelStr;//字符串时间

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
        //整理后的时间
        modelStr = StringUtils.getModelDate(addtime);
    }

    public String getShortContent() {
        // 去掉html代码
        shortContent = WebUtils.replaceTagHTML(content);
        // 截取长度超过截取30+...
        shortContent = StringUtils.getLength(shortContent, 40);
        return shortContent;
    }
}
