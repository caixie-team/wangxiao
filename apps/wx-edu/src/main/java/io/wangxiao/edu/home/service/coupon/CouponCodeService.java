package io.wangxiao.edu.home.service.coupon;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.coupon.CouponCode;
import io.wangxiao.edu.home.entity.coupon.CouponCodeDTO;
import io.wangxiao.edu.home.entity.coupon.QueryCouponCode;
import io.wangxiao.edu.home.entity.course.Course;

import java.util.List;
import java.util.Map;

/**
 * CouponCode管理接口
 */
public interface CouponCodeService {

    /**
     * 添加Coupon
     *
     * @param couponCode字符串
     * @return id
     */
    void addCouponCode(StringBuffer val);

    /**
     * 根据id删除一个CouponCode
     *
     * @param it 要删除的id
     */
    void deleteCouponCodeById(Long id);

    /**
     * 修改CouponCode
     *
     * @param couponCode 要修改的CouponCode
     */
    void updateCouponCode(CouponCode couponCode);

    /**
     * 修改CouponCode支付时间
     *
     * @param couponCode 要修改的CouponCode
     */
    void updateCouponCodePayTime(CouponCode couponCode);

    /**
     * 根据id获取单个CouponCode对象
     *
     * @param it 要查询的id
     * @return CouponCode
     */
    CouponCode getCouponCodeById(Long id);

    /**
     * 根据优惠券编码获取单个CouponCode对象
     *
     * @param it 要查询的code
     * @return CouponCode
     */
    CouponCodeDTO getCouponCodeByCode(String code);

    /**
     * 根据条件获取CouponCode列表
     *
     * @param couponCode 查询条件
     * @return List<CouponCode>
     */
    List<CouponCode> getCouponCodeListByCouponId(Long id);

    /**
     * 优惠券id查找优惠券编码
     *
     * @param id
     * @return
     */
    List<String> getStringCodeByCouponId(Long id);

    /**
     * 优惠券编码列表
     *
     * @param queryCoupon
     * @param page
     * @return
     */
    List<CouponCodeDTO> getCouponCodePage(QueryCouponCode queryCouponCode, PageEntity page);

    /**
     * id查询优惠券编码
     *
     * @param queryCoupon
     * @param page
     * @return
     */
    CouponCodeDTO getCouponCodeDTO(Long id);

    /**
     * 作废优惠券编码
     *
     * @param id
     */
    void wasteCouponCode(String ids);

    /**
     * 作废优惠券下的未使用优惠编码
     *
     * @param id
     */
    void wasteCodeByCouponId(Long id);

    /**
     * 过期的优惠编码改状态
     *
     * @param id
     */
    void overdueCodeByTime();

    /**
     * 优惠编码使用限制
     *
     * @param couponCodeDTO
     * @param memberCode
     * @param userId
     * @param flag          区分购物车课程和订单课程
     * @return
     */
    Map<String, Object> checkCode(CouponCodeDTO couponCodeDTO, String memberCode, List<Course> courses);

    /**
     * 批量赠送优惠券给用户
     *
     * @param ids
     * @param userId
     */
    void giveCouponBatch(String ids, Long userId);
}