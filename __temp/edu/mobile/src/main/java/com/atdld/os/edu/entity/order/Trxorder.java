package com.atdld.os.edu.entity.order;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Trxorder implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 5113068126680550371L;
    private Long id;
    private Long userId;//用户id
    private java.util.Date createTime;//下单时间
    private java.util.Date payTime;//支付成功
    private java.math.BigDecimal orderAmount;//订单原始金额
    private java.math.BigDecimal couponAmount;//优惠券金额
    private java.math.BigDecimal amount;//实际支付金额
    private java.math.BigDecimal cashAmount;//实际支付的cash金额
    private java.math.BigDecimal vmAmount;//实际支付的vm金额
    private Long couponCodeId=0L;//优惠券编码id
    private String requestId;//交易请求号
    private String trxStatus;//交易装态 INIT SUCCESS REFUND CACULE
    private String payType;//支付类型（ALIPY,KUAIQIAN,CARD,FREE,INTEGRAL）
    private String reqChannel;//请求渠道(WEB,APP)
    private String description;//备用描述
    private Long version=0L;//乐观锁版本号
    private String reqIp;//客户端IP
}
