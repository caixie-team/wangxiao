package com.atdld.os.sns.entity.search;

import lombok.Data;

/**
 * @author :
 * @ClassName com.atdld.os.sns.entity.search.SearchDto
 * @description 搜索用字段
 * @Create Date : 2014-1-22 上午10:00:29
 */
@Data
public class SearchDto {

    private String tab;// 页面tab
    private String keyword;// 关键字
    private String falgshow;// 是否展示高级标识

    // 博客
    private String blogshowname;// 用户名
    private String blogdate;// 时间
    private String blogtitle;// 标题
    // 微博
    private String weiboshowname;// 用户名
    private String weibodate;// 时间
    // 建议
    private String sugshowname;// 用户名
    private String sugbodate;// 时间
    private String sugtitle;// 标题
    private String sugType;// 建议类型 类型 1务实2意境
    private String sugRecType;// 建议的推荐类型 1智慧 2热心
    private String sugRecUser;// 建议的给出建议者

    // 小组
    private String disshowname;// 小组主
    private String disclasstiy;// 小组分类
    private int disgroupFlag;// 小组排序标示
}
