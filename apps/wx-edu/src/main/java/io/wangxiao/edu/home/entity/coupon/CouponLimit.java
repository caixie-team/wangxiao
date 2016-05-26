package io.wangxiao.edu.home.entity.coupon;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CouponLimit implements Serializable {
    private Long id;
    /**
     * 优惠券ID
     */
    private Long couponId;
    /**
     * 课程id
     */
    private Long courseId;
}
