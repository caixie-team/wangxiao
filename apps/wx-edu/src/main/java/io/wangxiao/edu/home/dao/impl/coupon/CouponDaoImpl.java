package io.wangxiao.edu.home.dao.impl.coupon;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.coupon.CouponDao;
import io.wangxiao.edu.home.entity.coupon.Coupon;
import io.wangxiao.edu.home.entity.coupon.CouponDTO;
import io.wangxiao.edu.home.entity.coupon.CouponLimit;
import io.wangxiao.edu.home.entity.coupon.QueryCoupon;
import io.wangxiao.edu.home.entity.course.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("couponDao")
public class CouponDaoImpl extends GenericDaoImpl implements CouponDao {

    public Long addCoupon(Coupon coupon) {
        return this.insert("CouponMapper.createCoupon", coupon);
    }

    public void delCouponById(Long id) {
        this.delete("CouponMapper.deleteCouponById", id);
    }

    public void updateCoupon(Coupon coupon) {
        this.update("CouponMapper.updateCoupon", coupon);
    }

    /**
     * 修改Coupon使用数量
     *
     * @param coupon 要修改的Coupon
     */
    public void updateCouponUserNum(Long id) {
        this.update("CouponMapper.updateCouponUserNum", id);
    }

    /**
     * 修改Coupon支付数量
     *
     * @param coupon 要修改的Coupon
     */
    public void updateCouponPayNum(Long id) {
        this.update("CouponMapper.updateCouponPayNum", id);
    }

    public Coupon getCouponById(Long id) {
        return this.selectOne("CouponMapper.getCouponById", id);
    }

    public List<Coupon> getCouponList(Coupon coupon) {
        return this.selectList("CouponMapper.getCouponList", coupon);
    }

    public List<CouponDTO> getCouponPage(QueryCoupon queryCoupon, PageEntity page) {
        return this.queryForListPage("CouponMapper.getCouponPage", queryCoupon, page);
    }


    public void addCouponLimitCourse(List<CouponLimit> couponLimits) {
        this.insert("CouponMapper.addCouponLimitCourse", couponLimits);

    }

    /**
     * 查看优惠券
     *
     * @param id
     * @return
     */
    public CouponDTO getCouponDTOById(Long id) {
        return this.selectOne("CouponMapper.getCouponDTOById", id);
    }

    /**
     * 优惠券id查找限制课程
     *
     * @param id
     * @return
     */
    public List<Course> getCouponLimitCourseById(Long id) {
        return this.selectList("CouponMapper.getCouponCourse", id);
    }
}
