package com.atdld.os.edu.service.impl.coupon;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.dao.coupon.CouponCodeDao;
import com.atdld.os.edu.entity.coupon.CouponCode;
import com.atdld.os.edu.entity.coupon.CouponCodeDTO;
import com.atdld.os.edu.entity.coupon.QueryCouponCode;
import com.atdld.os.edu.entity.course.Course;
import com.atdld.os.edu.service.coupon.CouponCodeService;
import com.atdld.os.edu.service.coupon.CouponService;
import com.atdld.os.edu.service.course.CourseService;
import com.atdld.os.edu.service.order.TrxorderService;
import com.atdld.os.edu.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * CouponCode管理接口
 * User:
 * Date: 2014-05-27
 */
@Service("couponCodeService")
public class CouponCodeServiceImpl implements CouponCodeService{

 	@Autowired
    private CouponCodeDao couponCodeDao;
 	@Autowired
    private CouponService couponService;
 	@Autowired
    private UserService userService;
    @Autowired
    private TrxorderService trxorderService;
    @Autowired
    private CourseService courseService;
 	/**
     * 添加CouponCode
     * @param val 要添加的CouponCode
     * @return id
     */
	public void addCouponCode(StringBuffer val) {
 		couponCodeDao.addCouponCode(val);//生成优惠券编码
	}

    /**
     * 根据id删除一个CouponCode
     * @param id 要删除的id
     */
    public void deleteCouponCodeById(Long id){
    	 couponCodeDao.deleteCouponCodeById(id);
    }

    /**
     * 修改CouponCode
     * @param couponCode 要修改的CouponCode
     */
    public void updateCouponCode(CouponCode couponCode){
     	couponCodeDao.updateCouponCode(couponCode);
    }
    /**
     * 修改CouponCode支付时间
     * @param couponCode 要修改的CouponCode
     */
    public void updateCouponCodePayTime(CouponCode couponCode){
     	couponCodeDao.updateCouponCodePayTime(couponCode);
    }
    /**
     * 根据id获取单个CouponCode对象
     * @param id 要查询的id
     * @return CouponCode
     */
    public CouponCode getCouponCodeById(Long id){
    	return couponCodeDao.getCouponCodeById(id);
    }
    /**
     * 根据优惠券编码获取单个CouponCode对象
     * @param code 要查询的code
     * @return CouponCode
     */
    public CouponCodeDTO getCouponCodeByCode(String code){
    	return couponCodeDao.getCouponCodeByCode(code);
    }
    /**
     * 根据条件获取CouponCode列表
     * @param couponCode 查询条件
     * @return List<CouponCode>
     */
    public List<CouponCode> getCouponCodeListByCouponId(Long id){
    	return couponCodeDao.getCouponCodeListByCouponId(id);
    }
    /**
     * 优惠券id查找优惠券编码
     */
	public List<String> getStringCodeByCouponId(Long id) {
		return couponCodeDao.getStringCodeByCouponId(id);
	}
	/**
	 * 优惠券编码列表
	 * @param queryCouponCode
	 * @param page
	 * @return
	 */
	public List<CouponCodeDTO> getCouponCodePage(QueryCouponCode queryCouponCode, PageEntity page){
		return couponCodeDao.getCouponCodePage(queryCouponCode,page);
	}
	/**
	 * id查询优惠券编码
	 * @param id
	 * @return
	 */
	public CouponCodeDTO getCouponCodeDTO(Long id){
		return couponCodeDao.getCouponCodeDTO(id);
	}
	/**
	 * 作废优惠券编码
	 * @param ids
	 */
	public void wasteCouponCode(String ids){
		couponCodeDao.wasteCouponCode(ids);
	}
	/**
	 * 作废优惠券下的未使用优惠编码
	 * @param id
	 */
	public void wasteCodeByCouponId(Long id){
		couponCodeDao.wasteCodeByCouponId(id);
	}
	/**
	 * 过期的优惠编码改状态
	 */
	public void overdueCodeByTime(){
		couponCodeDao.overdueCodeByTime();

	}
	/**
	 * 优惠编码使用限制
	 * @param couponCodeDTO
	 * @param memberCode 区分会员和课程
	 * @param courses
	 * @return
	 */
	public Map<String,Object> checkCode(CouponCodeDTO couponCodeDTO,String memberCode,List<Course> courses){
		String resultMsg="";
		Map<String,Object> map=new HashMap<String, Object>();
        if (couponCodeDTO == null) {
        	resultMsg="优惠券不存在";
        } else if (couponCodeDTO.getType()==3&&!memberCode.equals("memberCode")) {
        	resultMsg="会员优惠券只适用于会员订单";
        } else if (memberCode.equals("memberCode")&&couponCodeDTO.getType()!=3) {
        	resultMsg="请输入会员优惠券";
        } else if (couponCodeDTO.getStatus() == 2) {
        	resultMsg="优惠券已使用";
        } else if (couponCodeDTO.getStatus() == 3) {
        	resultMsg="优惠券已过期";
        } else if (couponCodeDTO.getStatus() == 4) {
        	resultMsg="优惠券已作废";
        } else{
        	Date startDate = couponCodeDTO.getStartTime();
            Date endDate = couponCodeDTO.getEndTime();
            Date date=new Date();
        	if (startDate.getTime() > date.getTime() || endDate.getTime() < date.getTime()) {
        		resultMsg="优惠券不在使用期限内";
        	}
        }
        if(resultMsg!=""){
        	map.put("msg", resultMsg);
        	return map;
        }
        if(memberCode.equals("memberCode")){//会员订单
        	map.put("msg", "true");//通过验证标志
    		return map;
        }else{//课程订单
        	map=courseOrderCode(couponCodeDTO,courses);
        }
        return map;
	}
	/**
 	 * 课程订单优惠券编码验证
 	 * @param couponCodeDTO
 	 * @return
 	 */
 	public Map<String,Object> courseOrderCode(CouponCodeDTO couponCodeDTO,List<Course> courses){
 		Map<String,Object> map=new HashMap<String, Object>();
 		String resultMsg="";
 		double tempPrice=0.00;//临时价格
 		String courseIds="";//购物车课程id字符串
    	for(Course course:courses){//购物车课程id字符串
    		courseIds+=course.getId()+",";
    	}
 		//课程限制集合
 		List<Course> limitCourses=couponService.getCouponLimitCourseById(couponCodeDTO.getCouponId());
 		
        //优惠券项目限制,含有该项目下的课程且含有课程总价格大于优惠券限制价格
        if(couponCodeDTO.getSubjectId()>0){
        	courseIds=courseIds.substring(0, courseIds.length()-1);
        	//购物车中满足项目限制的课程集合
        	List<Course> subjectCourses=courseService.getCouponSubjectCourse(couponCodeDTO.getSubjectId(),courseIds);
        	String limitCourseIds="";//记录优惠的课程id字符串，方便以后查看
        	for(Course course:subjectCourses){
        		limitCourseIds+=course.getId()+",";
        		tempPrice+=course.getCurrentprice().doubleValue();
        	}
        	if(tempPrice==0||tempPrice<couponCodeDTO.getLimitAmount().doubleValue()){
        		resultMsg="优惠券不在使用范围内";//无该项目限制的课程或课程价格不满足优惠券限制
        	}else{//返回能优惠的课程金额,具体的打折、减额在页面展示
        		map.put("msg", "true");//通过验证标志
        		map.put("tempPrice", tempPrice);
        		map.put("limitCourseIds", limitCourseIds);
        		return map;
        	}
        }
        //优惠券课程限制,必须含有优惠券限制的所有课程
        else if(limitCourses!=null&&limitCourses.size()>0){
        	String limitCourseIds="";//记录优惠的课程id字符串，方便以后查看
        	boolean flag=true;
        	for(Course course:limitCourses){//是否包含全部限制课程
        		limitCourseIds+=course.getId()+",";
        		if(courseIds.indexOf(course.getId()+",")==-1){
        			flag=false;
        		}
        		tempPrice+=course.getCurrentprice().doubleValue();//限制课程总价格
        	}
        	if(flag!=true||tempPrice==0||tempPrice<couponCodeDTO.getLimitAmount().doubleValue()){
        		resultMsg="优惠券不在使用范围内";//购物车没有优惠限制的全部课程或课程价格不满足优惠券限制
        	}else{//返回能优惠的课程金额,具体的打折、减额在页面展示
        		map.put("msg", "true");//通过验证标志
        		map.put("tempPrice", tempPrice);
        		map.put("limitCourseIds", limitCourseIds);
        		return map;
        	}
        }
        //所有课程都能用
        else{
        	for(Course course:courses){
        		tempPrice+=course.getCurrentprice().doubleValue();
        	}
        	if(tempPrice==0||tempPrice<couponCodeDTO.getLimitAmount().doubleValue()){
        		resultMsg="优惠券不在使用范围内";//购物车课程价格不满足优惠券限制
        	}else{//返回能优惠的课程金额,具体的打折、减额在页面展示
        		map.put("msg", "true");//通过验证标志
        		map.put("tempPrice", tempPrice);
        		return map;
        	}
        }
        map.put("msg",resultMsg);//返回错误信息
        return map;
 	}

}
