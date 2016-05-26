package com.atdld.os.sns.entity.discuss;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :
 * @ClassName com.atdld.os.core.entity.discuss.DisGroup
 * @description 小组组
 * @Create Date : 2013-12-11 下午2:05:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DisGroup implements Serializable {

    private static final long serialVersionUID = 1244946624106941174L;

    private Long id;// 小组ID
    private String name;// 小组名称
    private String introduction;// 小组介绍
    private String imageUrl;// 小组头像 url 150*150
    private String imageOriginal;// 原图地址url
    private String imageLittle;// 缩略图url 60*60
    private Date createTime;// 创建时间
    private Long cusId;// 创建人Id
    private int memberNum;// 小组成员数
    private String showName;// 会员用户名字
    private Long disclassifyId;// 小组分类id
    private int status = -1;// 小组的状态0审核1审核通过
    private int top;// 置顶小组
    private int activity;// 活跃度
    private int articleCounts;// 文章数
    private int sort;// 排序值
    private String disclasstiy;// 分类名称
    private String disNumber;// 小组号码
    private int type;// 被转让类型
    private int transferId;// 权限
    private int flag;// 排序标示

}
