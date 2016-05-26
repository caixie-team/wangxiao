package io.wangxiao.user.member.entity;

import io.wangxiao.core.model.BaseIncrementIdModel;

import java.math.BigDecimal;

/**
 * 会员商品
 */
public class MemberSale extends BaseIncrementIdModel {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 商品名称
     */
    private String name;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 描述
     */
    private String description;
    /**
     * 权重排序
     */
    private int sort;
    /**
     * 会员类型
     */
    private Long type;
    /**
     * 按年时成功支付后开通的天数
     */
    private int days;
    /**
     * 状态0正常1删除
     */
    private int status;
}

