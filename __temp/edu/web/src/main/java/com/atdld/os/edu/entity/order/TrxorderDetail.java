package com.atdld.os.edu.entity.order;

import java.io.Serializable;
import java.util.List;

import com.atdld.os.edu.entity.course.Teacher;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TrxorderDetail implements Serializable{
    /**
     * 流水
     */
    private static final long serialVersionUID = 7081379317366445288L;
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
    private List<Teacher> teacherList;//最后更新时间
}
