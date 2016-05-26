package io.wangxiao.edu.home.dao.coupon;

import io.wangxiao.edu.home.entity.coupon.CouponLimit;

import java.util.List;

/**
 * CouponLimit管理接口
 */
public interface CouponLimitDao {

    /**
     * 添加CouponLimit
     *
     * @param couponLimit 要添加的CouponLimit
     * @return id
     */
    java.lang.Long addCouponLimit(CouponLimit couponLimit);

    /**
     * 根据id删除一个CouponLimit
     *
     * @param id 要删除的id
     */
    void deleteCouponLimitById(Long id);

    /**
     * 根据courseId删除一个CouponLimit
     *
     * @param courseId 要删除的id
     */
    void deleteCouponLimitByCourseId(Long courseId);

    void deleteCouponLimitByCourseIds(String courseId);

    /**
     * 修改CouponLimit
     *
     * @param couponLimit 要修改的CouponLimit
     */
    void updateCouponLimit(CouponLimit couponLimit);

    /**
     * 根据id获取单个CouponLimit对象
     *
     * @param id 要查询的id
     * @return CouponLimit
     */
    CouponLimit getCouponLimitById(Long id);

    /**
     * 根据条件获取CouponLimit列表
     *
     * @param couponLimit 查询条件
     * @return List<CouponLimit>
     */
    List<CouponLimit> getCouponLimitList(CouponLimit couponLimit);
}