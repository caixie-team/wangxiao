package io.wangxiao.order.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 订单查询条件类
 * 
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryTrxorder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1996291355323321464L;
	private Long id;
	private Long userId;// 用户id
	private java.util.Date createTime;//
	private java.util.Date payTime;
	private java.math.BigDecimal orderAmount;// 订单原始金额
	private java.math.BigDecimal couponAmount;// 优惠券金额
	private java.math.BigDecimal amount;// 实际支付金额
	private java.math.BigDecimal cashAmount;// 实际支付的cash金额
	private java.math.BigDecimal vmAmount;// 实际支付的vm金额
	private Long couponCodeId = 0L;// 优惠券id
	private String requestId;// 交易请求号
	private String trxStatus;// 交易装态 INIT SUCCESS REFUND CACULE
	private String payType;// 支付类型（ALIPY,KUAIQIAN,CARD,FREE,INTEGRAL）
	private String reqChannel;// 请求渠道(WEB,APP)
	private Long version = 0L;// 乐观锁版本号
	private String description;
	private String reqIp;// 客户端IP
	private String userName;// 用户名
	private String email;// 用户邮箱
	private java.util.Date startCreateTime;// 下单时间
	private java.util.Date endCreateTime;// 下单时间
	private java.util.Date startPayTime;// 支付时间
	private java.util.Date endPayTime;//
	private String couponCode;//优惠券编码
	private String mobile;//手机号码

}
