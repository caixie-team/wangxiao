package com.atdld.os.edu.entity.member;

import java.io.Serializable;

import com.atdld.os.edu.constants.enums.PayType;
import com.atdld.os.edu.entity.user.UserAccount;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberOrderReqData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//订单id
	 private Long memberOrderId;
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
    private java.math.BigDecimal bankAmount;//本次还需提交到银行的金额，账户中有余额的情况bankAmount<=amount
    /**
     * 虚拟币
     */
    private java.math.BigDecimal vmAmount;
    private String couponCode;//优惠券编码
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
    private PayType payType;
    /**
     * 开通天数
     */
    private int memberDays;
    /**
     * 开通会员类型
     */
    private Long memberType;
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
    private boolean isRecharge=false;//是否充值操作
    private UserAccount userAccount;
    private String out_trade_no;//提交到支付宝的请求号
}
