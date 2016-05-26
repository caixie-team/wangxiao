package com.atdld.os.sns.entity.dynamic;

import lombok.Data;

import com.atdld.os.core.util.StringUtils;

/**
 * @author :
 * @ClassName com.atdld.os.sns.util.DynamicDesc
 * @description
 * @Create Date : 2014-5-9 下午5:08:38
 */
@Data
public class DynamicDesc {

    private String desc;// 描述
    private String title;// 标题
    private String action;// 动作(发表了微博，回复了小组文章)
    private String id;// 超链接id

    /* 周玮 在 (描述 )"a不禁止的都会发生a"(标题+id)回复了群文章(动作)： */
    public String getText(String type) {
        StringBuffer buffer = new StringBuffer();
        if (StringUtils.isNotEmpty(desc)) {
            buffer.append(desc);
        }
        if (StringUtils.isNotEmpty(id)) {
            if (StringUtils.isNotEmpty(title)) {

                if (type.equals("aaa")) {
                    // url不同
                }
                buffer.append("<a href='+" + id + "+'>" + title + "</a>");
            }

        } else {
            if (StringUtils.isNotEmpty(desc)) {
                buffer.append(desc);
            }
        }
        if (StringUtils.isNotEmpty(action)) {
            buffer.append(action);
        }
        return buffer.toString();
    }

}
