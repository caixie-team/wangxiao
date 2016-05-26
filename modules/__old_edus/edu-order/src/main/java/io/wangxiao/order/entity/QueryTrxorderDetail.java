package io.wangxiao.order.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 流水查询条件类
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class QueryTrxorderDetail implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 8823831006166279064L;

	  private Long id;
	    private Long userId;//用户id
	    private Long courseId;//相关联的课程id（前台快照）
	    private Long trxorderId;//交易订单ID
	    private Long membertype;//会员观看类型（前台快照）
	    private int losetype;//有效期类型（前台快照）
	    private java.util.Date loseAbsTime;//订单过期时间段（前台快照）
	    private String loseTime;//订单过期时间点（前台快照）
	    private java.util.Date authTime;//课程过期时间
	    private java.util.Date createTime;//下单时间
	    private java.util.Date payTime;//支付成功时间
	    private java.math.BigDecimal sourcePrice;//原价格（前台快照）
	    private java.math.BigDecimal currentPrice;//销售价格（前台快照）
	    private String courseName;//课程名称（前台goods快照）
	    private String trxStatus;//订单状态（前台goods快照）
	    private String authStatus;//课程状态（INIT，SUCCESS，REFUND，CLOSED，LOSED）
	    private String requestId;//订单请求号
	    private String description;//描述
	    private Long version=1L;//乐观锁版本号
	    private java.util.Date lastUpdateTime;//最后更新时间
	    private String userName;
	    private String email;
	    
	    private java.util.Date startAuthTime;
	    private java.util.Date endAuthTime;
	    private java.util.Date startCreateTime;
	    private java.util.Date endCreateTime;
	    private java.util.Date startPayTime;
	    private java.util.Date endPayTime;
	    
	
}
