package com.atdld.os.sns.constants;

/**
 * @author :
 * @ClassName com.atdld.os.sns.entity.constans.DisGroupConstans
 * @description
 * @Create Date : 2014-3-26 上午9:57:40
 */
public class DisGroupConstans {
    /**
     * disGroup 字典常量定义
     */
    public static final int GROUP_STATUS_PASS = 1;// 小组状态 审核通过
    public static final int GROUP_STATUS_UNPASS = 0;// 小组状态 审核中
    public static final int GROUP_MEMBER_TRANSFERID_ADMINISTRATOR = 0;// 小组成员身份标识
    // 0管理员
    public static final int GROUP_MEMBER_TRANSFERID_COMMON = 1;// 小组成员身份标识 1成员
    public static final int GROUP_ACTIVITY_ARTICLE = 5;// 发表小组文章活跃度加分
    public static final int GROUP_ACTIVITY_REPLY = 1;// 回复小组文章 活跃度加分
    public static final int GROUP_ACTIVITY_MEMBER = 30;// 添加小组成员 活跃度加分
}
