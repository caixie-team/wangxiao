package com.atdld.os.edu.dao.impl.coupon;

import java.util.List;
import com.atdld.os.edu.entity.coupon.CouponLimit;
import com.atdld.os.edu.dao.coupon.CouponLimitDao;
import org.springframework.stereotype.Repository;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;

/**
 *
 * CouponLimit
 * User:
 * Date: 2014-05-27
 */
 @Repository("couponLimitDao")
public class CouponLimitDaoImpl extends GenericDaoImpl implements CouponLimitDao{

    public java.lang.Long addCouponLimit(CouponLimit couponLimit) {
        return this.insert("CouponLimitMapper.createCouponLimit",couponLimit);
    }

    public void deleteCouponLimitById(Long id){
        this.delete("CouponLimitMapper.deleteCouponLimitById",id);
    }

    public void updateCouponLimit(CouponLimit couponLimit) {
        this.update("CouponLimitMapper.updateCouponLimit",couponLimit);
    }

    public CouponLimit getCouponLimitById(Long id) {
        return this.selectOne("CouponLimitMapper.getCouponLimitById",id);
    }

    public List<CouponLimit> getCouponLimitList(CouponLimit couponLimit) {
        return this.selectList("CouponLimitMapper.getCouponLimitList",couponLimit);
    }
}
