package com.atdld.os.member.entity;

import java.io.Serializable;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberOrderDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 会员类型id
     */
    private Long memberId;
    /**
     * 下单时间
     */
    private java.util.Date createTime;
    /**
     * 支付成功/确认时间
     */
    private java.util.Date payTime;
    /**
     * 订单原始金额
     */
    private java.math.BigDecimal orderAmount;
    /**
     * 优惠券金额
     */
    private java.math.BigDecimal couponAmount;
    /**
     * 实际支付金额
     */
    private java.math.BigDecimal amount;
    /**
     * 现金支付
     */
    private java.math.BigDecimal cashAmount;
    /**
     * 虚拟币
     */
    private java.math.BigDecimal vmAmount;
    /**
     * 优惠券编码id
     */
    private Long couponCodeId;
    /**
     * 交易请求号
     */
    private String requestId;
    /**
     * 交易装态
     */
    private String trxStatus;
    /**
     * 支付类型（ALIPY,KUAIQIAN,VM,FREE,INTEGRAL)
     */
    private String payType;
    /**
     * 开通天数
     */
    private int memberDays;
    /**
     * 开通会员类型
     */
    private Long memberType;
    /**
     * 开通会员类型名称
     */
    private String memberTitle;
    /**
     * 请求渠道(WEB,APP)
     */
    private String reqChannel;
    /**
     * 备用描述
     */
    private String description;
    /**
     * 乐观锁版本号
     */
    private Long version;
    /**
     * 客户端IP
     */
    private String reqIp;
    /**
	 * 用户邮箱
	 */
    private String email;
    /**
     * 优惠券编码
     */
    private String couponCode;
   
}
