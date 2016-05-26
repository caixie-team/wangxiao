package io.wangxiao.edu.home.entity.member;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 会员商品
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberSale implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 价格
     */
    private java.math.BigDecimal price;
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

