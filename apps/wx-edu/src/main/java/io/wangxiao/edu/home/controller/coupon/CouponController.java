package io.wangxiao.edu.home.controller.coupon;

import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.coupon.Coupon;
import io.wangxiao.edu.home.entity.coupon.CouponCodeDTO;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.service.coupon.CouponCodeService;
import io.wangxiao.edu.home.service.coupon.CouponService;
import io.wangxiao.edu.home.service.order.ShopcartService;
import io.wangxiao.edu.home.service.order.TrxorderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class CouponController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(CouponController.class);
    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponCodeService couponCodeService;
    @Autowired
    private ShopcartService shopcartService;
    @Autowired
    private TrxorderService trxorderService;

    /**
     * 验证优惠券的限制范围
     *
     * @return
     */
    @RequestMapping("/coupon/check")
    @ResponseBody
    public Map<String, Object> checkCouponByCode(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String couponCode = request.getParameter("couponCode");// 优惠券编码
            String memberCode = request.getParameter("memberCode");// 会员优惠券
            String requestId = request.getParameter("requestId");// 订单编号
            if (memberCode == null) {
                memberCode = "";
            }
            Long userId = getLoginUserId(request);// 用户id
            // 查询优惠券编码
            CouponCodeDTO couponCodeDTO = couponCodeService.getCouponCodeByCode(couponCode);
            List<Course> courses = null;
            if (!memberCode.equals("memberCode")) {// 非会员订单
                if (requestId != null && requestId != "") {// 订单课程集合
                    courses = trxorderService.getTrxCourseByRequestId(requestId);// 订单课程集合
                } else {// 查询购物车的课程集合
                    courses = shopcartService.getShopcartCourseList(userId);
                    if (courses == null || courses.size() == 0) {// 如果购物车为空
                        json = this.getJsonMap(false, "购物车不能为空", null);
                        return json;
                    }
                }
            }

            // 验证优惠券编码
            Map<String, Object> map = couponCodeService.checkCode(couponCodeDTO, memberCode, courses);
            if (map.get("msg") == "true") {
                map.put("couponCodeDTO", couponCodeDTO);
                json = this.getJsonMap(true, "true", map);
            } else {
                json = this.getJsonMap(false, map.get("msg").toString(), null);
            }
        } catch (Exception e) {
            logger.error("CouponController.checkCouponByCode--验证优惠券出错", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 修改Coupon
     *
     * @param coupon 要修改的Coupon
     */
    public void updateCoupon(Coupon coupon) {
        couponService.updateCoupon(coupon);
    }

}