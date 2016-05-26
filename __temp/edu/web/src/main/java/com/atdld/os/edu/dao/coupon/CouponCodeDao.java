package com.atdld.os.edu.dao.coupon;
import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.coupon.CouponCode;
import com.atdld.os.edu.entity.coupon.CouponCodeDTO;
import com.atdld.os.edu.entity.coupon.QueryCouponCode;

/**
 * CouponCode管理接口
 * User:
 * Date: 2014-05-27
 */
public interface CouponCodeDao {

    /**
     * 批量添加添加CouponCode
     * @param couponCode 要添加的CouponCode
     * @return id
     */
    public void addCouponCode(StringBuffer val);

    /**
     * 根据id删除一个CouponCode
     * @param it 要删除的id
     */
    public void deleteCouponCodeById(Long it);

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
    public CouponCode getCouponCodeById(Long it);

    /**
     * 根据条件获取CouponCode列表
     * @param couponCode 查询条件
     * @return List<CouponCode>
     */
    public List<CouponCode> getCouponCodeListByCouponId(Long id);
    /**
     * 优惠券id查找优惠券编码
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
     * 根据优惠券编码获取单个CouponCode对象
     * @param it 要查询的code
     * @return CouponCode
     */
    public CouponCodeDTO getCouponCodeByCode(String code);
}