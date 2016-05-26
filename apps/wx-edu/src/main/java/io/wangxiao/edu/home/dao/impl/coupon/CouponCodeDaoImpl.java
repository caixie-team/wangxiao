package io.wangxiao.edu.home.dao.impl.coupon;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.coupon.CouponCodeDao;
import io.wangxiao.edu.home.entity.coupon.CouponCode;
import io.wangxiao.edu.home.entity.coupon.CouponCodeDTO;
import io.wangxiao.edu.home.entity.coupon.QueryCouponCode;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository("couponCodeDao")
public class CouponCodeDaoImpl extends GenericDaoImpl implements CouponCodeDao {

    /**
     * 批量添加添加CouponCode
     *
     * @param couponCode 要添加的CouponCode
     * @return id
     */
    public void addCouponCode(StringBuffer val) {
        this.insert("CouponCodeMapper.createCouponCode", val.toString());
    }

    public void deleteCouponCodeById(Long id) {
        this.delete("CouponCodeMapper.deleteCouponCodeById", id);
    }

    public void updateCouponCode(CouponCode couponCode) {
        this.update("CouponCodeMapper.updateCouponCode", couponCode);
    }

    /**
     * 修改CouponCode支付时间
     *
     * @param couponCode 要修改的CouponCode
     */
    public void updateCouponCodePayTime(CouponCode couponCode) {
        this.update("CouponCodeMapper.updateCouponCodePayTime", couponCode);
    }

    public CouponCode getCouponCodeById(Long id) {
        return this.selectOne("CouponCodeMapper.getCouponCodeById", id);
    }

    public List<CouponCode> getCouponCodeListByCouponId(Long id) {
        return this.selectList("CouponCodeMapper.getCouponCodeListByCouponId", id);
    }

    /**
     * 优惠券id查找优惠券编码
     */
    public List<String> getStringCodeByCouponId(Long id) {
        return this.selectList("CouponCodeMapper.getStringCodeListByCouponId", id);
    }

    /**
     * 优惠券编码列表
     *
     * @param queryCoupon
     * @param page
     * @return
     */
    public List<CouponCodeDTO> getCouponCodePage(QueryCouponCode queryCouponCode, PageEntity page) {
        return this.queryForListPage("CouponCodeMapper.getCouponCodePage", queryCouponCode, page);
    }

    /**
     * id查询优惠券编码
     *
     * @param queryCoupon
     * @param page
     * @return
     */
    public CouponCodeDTO getCouponCodeDTO(Long id) {
        return this.selectOne("CouponCodeMapper.getCouponCodeDTO", id);
    }

    /**
     * 根据优惠券编码获取单个CouponCode对象
     *
     * @param it 要查询的code
     * @return CouponCode
     */
    public CouponCodeDTO getCouponCodeByCode(String code) {
        return this.selectOne("CouponCodeMapper.getCouponCodeDTOByCode", code);
    }

    /**
     * 作废优惠券编码
     *
     * @param id
     */
    public void wasteCouponCode(String ids) {
        this.update("CouponCodeMapper.wasteCouponCode", ids);
    }

    /**
     * 作废优惠券下的未使用优惠编码
     *
     * @param id
     */
    public void wasteCodeByCouponId(Long id) {
        this.update("CouponCodeMapper.wasteCodeByCouponId", id);
    }

    /**
     * 过期的优惠编码改状态
     *
     * @param id
     */
    public void overdueCodeByTime() {
        this.update("CouponCodeMapper.overdueCodeByTime", 0);
    }

    @Override
    public void giveCouponBatch(Map map) {
        this.update("CouponCodeMapper.giveCouponBatch", map);
    }
}
