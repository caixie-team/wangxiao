package com.atdld.os.edu.controller.coupon;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atdld.os.edu.service.coupon.CouponCodeService;
import com.atdld.os.edu.service.coupon.CouponService;
import com.atdld.os.edu.service.order.ShopcartService;
import com.atdld.os.edu.service.order.TrxorderService;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.coupon.Coupon;
import com.atdld.os.edu.entity.coupon.CouponCodeDTO;
import com.atdld.os.edu.entity.course.Course;
/**
 * Coupon管理接口
 * User:
 * Date: 2014-05-27
 */
@Controller
public class CouponController extends EduBaseController{
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
     * 
     * 验证优惠券的限制范围
     * @return
     */
 	@RequestMapping("/coupon/check")
 	@ResponseBody
    public Map<String,Object> checkCouponByCode(HttpServletRequest request) {
        try {
        	String couponCode=request.getParameter("couponCode");//优惠券编码
        	String memberCode=request.getParameter("memberCode");//会员优惠券
        	String requestId=request.getParameter("requestId");//订单编号
        	if(memberCode==null){
        		memberCode="";
        	}
        	Long userId=getLoginUserId(request);//用户id
        	//查询优惠券编码
            CouponCodeDTO couponCodeDTO = couponCodeService.getCouponCodeByCode(couponCode);
            List<Course> courses=null;
            if(!memberCode.equals("memberCode")){//非会员订单
	            if(requestId!=null&&requestId!=""){//订单课程集合
	            	courses=trxorderService.getTrxCourseByRequestId(requestId);//订单课程集合
	            }else{//查询购物车的课程集合
	            	courses=shopcartService.getShopcartCourseList(userId);
	                if (courses== null||courses.size()==0) {// 如果购物车为空
	                	this.setJson(false, "购物车不能为空", null);
	                	return json;
	                }
	            }
            }
     		
            //验证优惠券编码
            Map<String,Object> map = couponCodeService.checkCode(couponCodeDTO, memberCode,courses);
            if(map.get("msg")=="true"){
            	map.put("couponCodeDTO", couponCodeDTO);
            	this.setJson(true, "true", map);
            }else{
            	this.setJson(false, map.get("msg").toString(), null);
            }
        } catch (Exception e) {
        	logger.error("CouponController.checkCouponByCode--验证优惠券出错", e);
			this.setJson(false, "error", null);
        }
        return json;
    }
 	
    
    /**
     * 修改Coupon
     * @param coupon 要修改的Coupon
     */
    public void updateCoupon(Coupon coupon){
     	couponService.updateCoupon(coupon);
    }

   
}