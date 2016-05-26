package io.wangxiao.edu.home.dao.impl.coupon;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.coupon.CouponLimitDao;
import io.wangxiao.edu.home.entity.coupon.CouponLimit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("couponLimitDao")
public class CouponLimitDaoImpl extends GenericDaoImpl implements CouponLimitDao {

    public java.lang.Long addCouponLimit(CouponLimit couponLimit) {
        return this.insert("CouponLimitMapper.createCouponLimit", couponLimit);
    }

    public void deleteCouponLimitById(Long id) {
        this.delete("CouponLimitMapper.deleteCouponLimitById", id);
    }

    public void deleteCouponLimitByCourseId(Long courseId) {
        this.delete("CouponLimitMapper.deleteCouponLimitByCourseId", courseId);
    }

    public void updateCouponLimit(CouponLimit couponLimit) {
        this.update("CouponLimitMapper.updateCouponLimit", couponLimit);
    }

    public CouponLimit getCouponLimitById(Long id) {
        return this.selectOne("CouponLimitMapper.getCouponLimitById", id);
    }

    public List<CouponLimit> getCouponLimitList(CouponLimit couponLimit) {
        return this.selectList("CouponLimitMapper.getCouponLimitList", couponLimit);
    }

    @Override
    public void deleteCouponLimitByCourseIds(String courseId) {
        // TODO Auto-generated method stub
        this.delete("CouponLimitMapper.deleteCouponLimitByCourseId", courseId);
    }
}
