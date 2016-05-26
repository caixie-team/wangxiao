package io.wangxiao.user.member.entity;

import io.wangxiao.core.model.BaseIncrementIdModel;

/**
 * 会员服务
 */
public class MemberType extends BaseIncrementIdModel {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 服务名称
     */
    private String title;
    /**
     * 状态0正常1删除
     */
    private int status;

}

