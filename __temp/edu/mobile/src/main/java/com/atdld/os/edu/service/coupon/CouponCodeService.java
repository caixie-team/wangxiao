package com.atdld.os.edu.service.coupon;

import java.util.List;
import java.util.Map;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.coupon.CouponCode;
import com.atdld.os.edu.entity.coupon.CouponCodeDTO;
import com.atdld.os.edu.entity.coupon.QueryCouponCode;
import com.atdld.os.edu.entity.course.Course;

/**
 * CouponCode管理接口
 * User:
 * Date: 2014-05-27
 */
public interface CouponCodeService {

    /**
     * 添加Coupon
     * @param couponCode字符串
     * @return id
     */
    public void addCouponCode(StringBuffer val);

    /**
     * 根据id删除一个CouponCode
     * @param it 要删除的id
     */
    public void deleteCouponCodeById(Long id);

    /**
     * 修改CouponCode
     * @param couponCode 要修改的CouponCode
     */
    public void updateCouponCode(CouponCode couponCode);
    /**
     * 修改CouponCode支付时间
     * @param couponCode 要修改的CouponCode
     */
    public void updateCouponCodePayTime(CouponCode couponCode);
    /**
     * 根据id获取单个CouponCode对象
     * @param it 要查询的id
     * @return CouponCode
     */
    public CouponCode getCouponCodeById(Long id);
    /**
     * 根据优惠券编码获取单个CouponCode对象
     * @param it 要查询的code
     * @return CouponCode
     */
    public CouponCodeDTO getCouponCodeByCode(String code);

    /**
     * 根据条件获取CouponCode列表
     * @param couponCode 查询条件
     * @return List<CouponCode>
     */
    public List<CouponCode> getCouponCodeListByCouponId(Long id);
    /**
     * 优惠券id查找优惠券编码
     * @param id
     * @return
     */
	public List<String> getStringCodeByCouponId(Long id);
	/**
	 * 优惠券编码列表
	 * @param queryCoupon
	 * @param page
	 * @return
	 */
	public List<CouponCodeDTO> getCouponCodePage(QueryCouponCode queryCouponCode, PageEntity page);
	/**
	 * id查询优惠券编码
	 * @param queryCoupon
	 * @param page
	 * @return
	 */
	public CouponCodeDTO getCouponCodeDTO(Long id);
	/**
	 * 作废优惠券编码
	 * @param id
	 */
	public void wasteCouponCode(String ids);
	/**
	 * 作废优惠券下的未使用优惠编码
	 * @param id
	 */
	public void wasteCodeByCouponId(Long id);
	/**
	 * 过期的优惠编码改状态
	 * @param id
	 */
	public void overdueCodeByTime();
	/**
	 * 优惠编码使用限制
	 * @param couponCodeDTO
	 * @param memberCode
	 * @param userId
	 * @param flag 区分购物车课程和订单课程
	 * @return
	 */
	public Map<String,Object> checkCode(CouponCodeDTO couponCodeDTO,String memberCode,List<Course> courses);
}