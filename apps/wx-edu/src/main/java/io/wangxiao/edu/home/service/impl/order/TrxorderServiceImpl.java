package io.wangxiao.edu.home.service.impl.order;

import com.google.gson.Gson;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.exception.BaseException;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.DateUtils;
import io.wangxiao.commons.util.EnumUtil;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.common.exception.AccountException;
import io.wangxiao.edu.common.exception.StaleObjectStateException;
import io.wangxiao.edu.common.service.SnsHessianService;
import io.wangxiao.edu.home.constants.enums.*;
import io.wangxiao.edu.home.constants.web.OrderConstans;
import io.wangxiao.edu.home.dao.course.CourseProfileDao;
import io.wangxiao.edu.home.dao.order.ShopcartDao;
import io.wangxiao.edu.home.dao.order.TrxorderDao;
import io.wangxiao.edu.home.dao.order.TrxorderDetailDao;
import io.wangxiao.edu.home.entity.coupon.CouponCode;
import io.wangxiao.edu.home.entity.coupon.CouponCodeDTO;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.course.CourseReserveRecord;
import io.wangxiao.edu.home.entity.order.*;
import io.wangxiao.edu.home.entity.user.UserAccount;
import io.wangxiao.edu.home.service.coupon.CouponCodeService;
import io.wangxiao.edu.home.service.coupon.CouponService;
import io.wangxiao.edu.home.service.course.CourseReserveRecordService;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.order.ShopcartService;
import io.wangxiao.edu.home.service.order.TrxorderDetailService;
import io.wangxiao.edu.home.service.order.TrxorderService;
import io.wangxiao.edu.home.service.user.UserAccountService;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service("trxorderService")
public class TrxorderServiceImpl implements TrxorderService {

    private static Logger logger = LoggerFactory.getLogger(TrxorderServiceImpl.class);
    @Autowired
    private TrxorderDao trxorderDao;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TrxorderDetailService trxorderDetailService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private ShopcartDao shopcartDao;
    @Autowired
    private ShopcartService shopcartService;
    @Autowired
    private SnsHessianService snsHessianService;
    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private TrxorderDetailDao trxorderDetailDao;
    @Autowired
    private CouponCodeService couponCodeService;
    @Autowired
    private CouponService couponService;

    @Autowired
    private CourseProfileDao courseProfileDao;
    @Autowired
    private CourseReserveRecordService courseReserveRecordService;

    CacheKit cacheKit = CacheKit.getInstance();


    @Getter
    @Setter
    private Map<String, Object> userMesg = new HashMap<String, Object>();


    /**
     * 添加Trxorder(用于APP)
     *
     * @param sourceMap
     * @return
     * @throws Exception
     */
    public Map<String, Object> appAddTrxorder(Map<String, String> sourceMap) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();

        // 检查参数
        TrxReqData trxReqData = checkorderparam(sourceMap);
        if (trxReqData == null || sourceMap.get("courseId") == null) {// 参数验证失败
            result.put("msg", "param");
            result.put("message", "请传入正确的数据！");
            return result;
        }
        // 拼装订单数据
        Trxorder trxorder = new Trxorder();
        // 下单时间
        Date date = new Date();
        trxorder.setCreateTime(date);// 下单时间
        trxorder.setUserId(trxReqData.getUserId());
        trxorder.setRequestId(this.getOrderNum(trxReqData.getUserId()));// 交易请求号
        trxorder.setTrxStatus(TrxOrderStatus.INIT.toString());// 交易装态
        trxorder.setPayType(trxReqData.getPayType().toString());// 支付类型
        trxorder.setReqChannel(trxReqData.getReqChannel());// 请求渠道
        trxorder.setReqIp(trxReqData.getReqIp());
        trxorder.setDescription("无");
        String courseIds = sourceMap.get("courseId");
        if (courseIds.endsWith(",")) {
            courseIds = courseIds.substring(0, courseIds.length() - 1);
        }
        //得到课程
        List<Course> courseList = courseService.queryCourseListByIds(courseIds);
        if (courseList == null || courseList.size() == 0) {
            result.put("msg", "notCoures");
            result.put("message", "购买的课程不存在 ！");
            return result;
        }
        // 原始金额
        BigDecimal orderAmount = new BigDecimal(0);
        // 循环该用户购物车中的课程全部的价格
        int index = 0;
        for (Course cou : courseList) {
            // 查询出的课程不为空则添加
            if (ObjectUtils.isNotNull(cou)) {
                orderAmount = orderAmount.add(cou.getCurrentprice());
                Date authDate = null;
                // 到期时间
                if (cou.getLosetype() == 0) {
                    authDate = cou.getLoseAbsTime();
                }
                // 按天数计算
                if (cou.getLosetype() == 1) {//
                    // 按所写时间推移过期时间
                    Calendar now = Calendar.getInstance();
                    now.setTime(date);
                    now.set(Calendar.DATE, now.get(Calendar.DATE) + Integer.valueOf(cou.getLoseTime()));
                    authDate = now.getTime();
                }

                if (date.getTime() > authDate.getTime()) {
                    result.put("msg", "expire");
                    result.put("message", "课程 " + cou.getName() + "已经过期");
                    index++;
                    break;
                }
            }
        }
        if (index > 0) {
            return result;
        }
        trxorder.setOrderAmount(orderAmount); // 原始金额
        // 是否使用优惠券，判断优惠券是否在规则内
        // 优惠金额
        BigDecimal couponAmount = new BigDecimal(0);
        // 查询优惠券编码
        if (sourceMap.get("couponcode") != null && sourceMap.get("couponcode") != "") {// 使用优惠券
            // 查询优惠券编码
            CouponCodeDTO couponCodeDTO = couponCodeService.getCouponCodeByCode(sourceMap.get("couponcode"));
            List<Course> courses = shopcartService.getShopcartCourseList(trxorder.getUserId());//课程集合用于优惠券编码验证
            // 验证优惠券编码
            Map<String, Object> map = couponCodeService.checkCode(couponCodeDTO, "", courses);
            if (map.get("msg") == "true") {// 验证通过
                if (map.get("limitCourseIds") != null) {// 订单描述，限制的课程id字符串
                    trxorder.setDescription("课程限制：" + map.get("limitCourseIds").toString());
                } else {
                    trxorder.setDescription("课程限制：所有课程");
                }
                trxorder.setCouponCodeId(couponCodeDTO.getId());// 优惠券编码id
                if (couponCodeDTO.getType() == 1) {// 折扣券
                    BigDecimal tempPrice = new BigDecimal(Double.parseDouble(map.get("tempPrice").toString()) * 0.1);
                    couponAmount = new BigDecimal(Double.parseDouble(map.get("tempPrice").toString())).subtract(tempPrice.multiply(couponCodeDTO.getAmount()));// 折扣优惠金额
                } else {// 定额券
                    couponAmount = couponCodeDTO.getAmount();// 定额优惠金额
                }
            }
        }

        trxorder.setCouponAmount(couponAmount);
        // 实际需要支付的金额,四舍五去取2位
        BigDecimal amount = orderAmount.subtract(couponAmount).setScale(2, RoundingMode.HALF_UP);
        if (amount.doubleValue() <= 0) {
            result.put("msg", "amount");
            return result;
        }
        trxorder.setAmount(amount);// 实际支付金额
        // 添加订单
        trxorderDao.addTrxorder(trxorder);
        // 添加流水表
        List<TrxorderDetail> trxorderDetailList = new ArrayList<TrxorderDetail>();
        for (Course cou : courseList) {
            if (ObjectUtils.isNull(cou)) {
                continue;
            }
            // 创建流水
            TrxorderDetail trxorderDetail = new TrxorderDetail();
            // 用户id
            trxorderDetail.setUserId(trxReqData.getUserId());
            // 课程id
            trxorderDetail.setCourseId(cou.getId());
            // 订单id
            trxorderDetail.setTrxorderId(trxorder.getId());
            // 订单类型
            trxorderDetail.setTrxStatus(TrxOrderStatus.INIT.toString());// 流水支付状态

            // 有效期类型
            trxorderDetail.setLosetype(cou.getLosetype());
            // 订单过期时间段
            trxorderDetail.setLoseAbsTime(cou.getLoseAbsTime());
            // 订单过期时间点
            trxorderDetail.setLoseTime(cou.getLoseTime());
            // 时间段
            if (cou.getLosetype() == 0) {
                trxorderDetail.setAuthTime(cou.getLoseAbsTime());
            }
            // 时间点
            if (cou.getLosetype() == 1) {
                // 按所写时间推移过期时间
                Calendar now = Calendar.getInstance();
                now.setTime(date);
                now.set(Calendar.DATE, now.get(Calendar.DATE) + Integer.valueOf(cou.getLoseTime()));
                trxorderDetail.setAuthTime(now.getTime());
            }


            // 下单时间
            trxorderDetail.setCreateTime(new Date());
            // 课程原始价格
            trxorderDetail.setSourcePrice(cou.getSourceprice());
            // 课程实际支付价格
            trxorderDetail.setCurrentPrice(cou.getCurrentprice());
            // 课程名
            trxorderDetail.setCourseName(cou.getName());
            // 课程状态
            trxorderDetail.setAuthStatus(AuthStatus.INIT.toString());
            // 订单请求号
            trxorderDetail.setRequestId(trxorder.getRequestId());
            trxorderDetail.setLastUpdateTime(date);
            trxorderDetail.setDescription("");

            trxorderDetailList.add(trxorderDetail);
        }
        // 批量添加流水
        trxorderDetailService.addBatchTrxorderDetail(trxorderDetailList);
        result.put("order", trxorder);
        return result;
    }

    /**
     * 添加微站Trxorder
     *
     * @param sourceMap 需要的参数
     * @return
     * @throws Exception
     */
    public Map<String, Object> addMobileTrxorder(Map<String, String> sourceMap) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();

        // 检查参数
        TrxReqData trxReqData = checkMobileOrderparam(sourceMap);
        if (trxReqData == null || trxReqData.getCourseId() == null) {// 参数验证失败
            result.put("msg", "param");
            return result;
        }
        //购买课程
        Course course = courseService.getCourseById(Long.parseLong(sourceMap.get("courseId")));
        if (trxReqData.getType() == 2) {//购买直播课程
            if (course.getLiveEndTime().getTime() < new Date().getTime()) {//判断结束时间
                result.put("msg", "ending");
                return result;
            }
            int num = trxorderDetailService.queryOrderByLive(trxReqData.getUserId(), course.getId());
            if (num > 0) {//判断已购买
                result.put("msg", "havebuy");
                return result;
            }
        }
        // 拼装订单数据
        Trxorder trxorder = new Trxorder();
        // 下单时间
        Date date = new Date();
        trxorder.setCreateTime(date);// 下单时间
        trxorder.setUserId(trxReqData.getUserId());
        trxorder.setRequestId(this.getOrderNum(trxReqData.getUserId()));// 交易请求号
        trxorder.setTrxStatus(TrxOrderStatus.INIT.toString());// 交易装态
        trxorder.setPayType(trxReqData.getPayType().toString());// 支付类型
        trxorder.setReqChannel(trxReqData.getReqChannel());// 请求渠道
        trxorder.setReqIp(trxReqData.getReqIp());
        trxorder.setDescription("无");


        if (course.getCurrentprice().doubleValue() == 0) {
            result.put("msg", "amount");
            return result;
        }
        Date authDate = null;
        // 到期时间
        if (course.getLosetype() == 0) {
            authDate = course.getLoseAbsTime();
        }
        // 按天数计算
        if (course.getLosetype() == 1) {//
            // 按所写时间推移过期时间
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            now.set(Calendar.DATE, now.get(Calendar.DATE) + Integer.valueOf(course.getLoseTime()));
            authDate = now.getTime();
        }
        // 如果课程已经过期，无法购买
        if (date.getTime() > authDate.getTime()) {
            result.put("msg", "courselosedate");
            return result;
        }
        // 原始金额
        BigDecimal orderAmount = course.getCurrentprice();

        trxorder.setOrderAmount(orderAmount); // 原始金额
        trxorder.setCouponAmount(new BigDecimal(0));//youhuijine
        trxorder.setAmount(orderAmount);// 实际支付金额
        // 添加订单
        trxorderDao.addTrxorder(trxorder);


        // 创建流水
        TrxorderDetail trxorderDetail = new TrxorderDetail();
        // 用户id
        trxorderDetail.setUserId(trxReqData.getUserId());
        // 课程id
        trxorderDetail.setCourseId(course.getId());
        // 订单id
        trxorderDetail.setTrxorderId(trxorder.getId());
        // 订单类型
        trxorderDetail.setTrxStatus(TrxOrderStatus.INIT.toString());// 流水支付状态

        // 有效期类型
        trxorderDetail.setLosetype(course.getLosetype());
        // 订单过期时间段
        if (course.getLoseAbsTime() != null) {
            trxorderDetail.setLoseAbsTime(course.getLoseAbsTime());
        }
        // 订单过期时间点
        trxorderDetail.setLoseTime(course.getLoseTime());
        // 时间段
        if (course.getLosetype() == 0) {
            trxorderDetail.setAuthTime(course.getLoseAbsTime());
        }
        // 时间点
        if (course.getLosetype() == 1) {
            // 按所写时间推移过期时间
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            now.set(Calendar.DATE, now.get(Calendar.DATE) + Integer.valueOf(course.getLoseTime()));
            trxorderDetail.setAuthTime(now.getTime());
        }
        // 下单时间
        trxorderDetail.setCreateTime(new Date());
        // 课程原始价格
        trxorderDetail.setSourcePrice(course.getSourceprice());
        // 课程实际支付价格
        trxorderDetail.setCurrentPrice(course.getCurrentprice());
        // 课程名
        trxorderDetail.setCourseName(course.getName());
        // 课程状态
        trxorderDetail.setAuthStatus(AuthStatus.INIT.toString());
        // 订单请求号
        trxorderDetail.setRequestId(trxorder.getRequestId());
        trxorderDetail.setLastUpdateTime(date);
        trxorderDetail.setDescription("");

        List<TrxorderDetail> trxorderDetailList = new ArrayList<>();
        trxorderDetailList.add(trxorderDetail);
        // 批量添加流水
        trxorderDetailService.addBatchTrxorderDetail(trxorderDetailList);
        result.put("order", trxorder);
        return result;
    }

    /**
     * 添加Trxorder
     *
     * @param sourceMap 需要的参数
     * @return
     * @throws Exception
     */
    public Map<String, Object> addTrxorder(Map<String, String> sourceMap) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();

        // 检查参数
        TrxReqData trxReqData = checkorderparam(sourceMap);
        if (trxReqData == null) {// 参数验证失败
            result.put("msg", "param");
            return result;
        }
        // 拼装订单数据
        Trxorder trxorder = new Trxorder();
        // 下单时间
        Date date = new Date();
        trxorder.setCreateTime(date);// 下单时间
        trxorder.setUserId(trxReqData.getUserId());
        trxorder.setRequestId(this.getOrderNum(trxReqData.getUserId()));// 交易请求号
        trxorder.setTrxStatus(TrxOrderStatus.INIT.toString());// 交易装态
        trxorder.setPayType(trxReqData.getPayType().toString());// 支付类型
        trxorder.setReqChannel(trxReqData.getReqChannel());// 请求渠道
        trxorder.setReqIp(trxReqData.getReqIp());
        trxorder.setDescription("无");

        // 获得该用户购物车
        List<Shopcart> shopcartlist = shopcartDao.getShopcartListByUserId(trxReqData.getUserId(), trxReqData.getType());
        if (ObjectUtils.isNull(shopcartlist)) {
            return result;
        }
        // 原始金额
        BigDecimal orderAmount = new BigDecimal(0);
        // 循环该用户购物车中的课程全部的价格
        for (Shopcart shopcart : shopcartlist) {
            Course cou = shopcart.getCourse();
            // 查询出的课程不为空则添加
            if (ObjectUtils.isNotNull(cou)) {
                orderAmount = orderAmount.add(cou.getCurrentprice());
                Date authDate = null;
                // 到期时间
                if (cou.getLosetype() == 0) {
                    authDate = cou.getLoseAbsTime();
                }
                // 按天数计算
                if (cou.getLosetype() == 1) {//
                    // 按所写时间推移过期时间
                    Calendar now = Calendar.getInstance();
                    now.setTime(date);
                    now.set(Calendar.DATE, now.get(Calendar.DATE) + Integer.valueOf(cou.getLoseTime()));
                    authDate = now.getTime();
                }
                // 如果课程已经过期，将课程从购物车中删除，并重新加载购物车
                if (date.getTime() > authDate.getTime()) {
                    shopcartService.deleteShopcartById(shopcart.getId(), trxReqData.getUserId());
                    result.put("msg", "courselosedate");
                    return result;
                }
            }
        }
        trxorder.setOrderAmount(orderAmount); // 原始金额
        // 是否使用优惠券，判断优惠券是否在规则内
        // 优惠金额
        BigDecimal couponAmount = new BigDecimal(0);
        // 查询优惠券编码
        if (sourceMap.get("couponcode") != null && sourceMap.get("couponcode") != "") {// 使用优惠券
            // 查询优惠券编码
            CouponCodeDTO couponCodeDTO = couponCodeService.getCouponCodeByCode(sourceMap.get("couponcode"));
            List<Course> courses = shopcartService.getShopcartCourseList(trxorder.getUserId());//课程集合用于优惠券编码验证
            // 验证优惠券编码
            Map<String, Object> map = couponCodeService.checkCode(couponCodeDTO, "", courses);
            if (map.get("msg") == "true") {// 验证通过
                if (map.get("limitCourseIds") != null) {// 订单描述，限制的课程id字符串
                    trxorder.setDescription("课程限制：" + map.get("limitCourseIds").toString());
                } else {
                    trxorder.setDescription("课程限制：所有课程");
                }
                trxorder.setCouponCodeId(couponCodeDTO.getId());// 优惠券编码id
                if (couponCodeDTO.getType() == 1) {// 折扣券
                    BigDecimal tempPrice = new BigDecimal(Double.parseDouble(map.get("tempPrice").toString()) * 0.1);
                    couponAmount = new BigDecimal(Double.parseDouble(map.get("tempPrice").toString())).subtract(tempPrice.multiply(couponCodeDTO.getAmount()));// 折扣优惠金额
                } else {// 定额券
                    couponAmount = couponCodeDTO.getAmount();// 定额优惠金额
                }
            }
        }

        trxorder.setCouponAmount(couponAmount);
        // 实际需要支付的金额,四舍五去取2位
        BigDecimal amount = orderAmount.subtract(couponAmount).setScale(2, RoundingMode.HALF_UP);
        if (amount.doubleValue() <= 0) {
            result.put("msg", "amount");
            return result;
        }
        trxorder.setAmount(amount);// 实际支付金额
        // 添加订单
        trxorderDao.addTrxorder(trxorder);
        // 添加流水表
        List<TrxorderDetail> trxorderDetailList = new ArrayList<TrxorderDetail>();
        for (Shopcart shopcart : shopcartlist) {
            Course cou = shopcart.getCourse();
            if (ObjectUtils.isNull(cou)) {
                continue;
            }
            // 创建流水
            TrxorderDetail trxorderDetail = new TrxorderDetail();
            // 用户id
            trxorderDetail.setUserId(trxReqData.getUserId());
            // 课程id
            trxorderDetail.setCourseId(shopcart.getGoodsid());
            // 订单id
            trxorderDetail.setTrxorderId(trxorder.getId());
            // 订单类型
            trxorderDetail.setTrxStatus(TrxOrderStatus.INIT.toString());// 流水支付状态

            // 有效期类型
            trxorderDetail.setLosetype(cou.getLosetype());
            // 订单过期时间段
            trxorderDetail.setLoseAbsTime(cou.getLoseAbsTime());
            // 订单过期时间点
            trxorderDetail.setLoseTime(cou.getLoseTime());
            // 时间段
            if (cou.getLosetype() == 0) {
                trxorderDetail.setAuthTime(cou.getLoseAbsTime());
            }
            // 时间点
            if (cou.getLosetype() == 1) {
                // 按所写时间推移过期时间
                Calendar now = Calendar.getInstance();
                now.setTime(date);
                now.set(Calendar.DATE, now.get(Calendar.DATE) + Integer.valueOf(cou.getLoseTime()));
                trxorderDetail.setAuthTime(now.getTime());
            }


            // 下单时间
            trxorderDetail.setCreateTime(new Date());
            // 课程原始价格
            trxorderDetail.setSourcePrice(cou.getSourceprice());
            // 课程实际支付价格
            trxorderDetail.setCurrentPrice(cou.getCurrentprice());
            // 课程名
            trxorderDetail.setCourseName(cou.getName());
            // 课程状态
            trxorderDetail.setAuthStatus(AuthStatus.INIT.toString());
            // 订单请求号
            trxorderDetail.setRequestId(trxorder.getRequestId());
            trxorderDetail.setLastUpdateTime(date);
            trxorderDetail.setDescription("");

            trxorderDetailList.add(trxorderDetail);
            CourseReserveRecord courseReserveRecord = new CourseReserveRecord();
            courseReserveRecord.setUserId(trxReqData.getUserId());
            courseReserveRecord.setCourseId(shopcart.getGoodsid());
            courseReserveRecord.setCourseName(cou.getName());
            courseReserveRecord.setCoursePrice(cou.getCurrentprice());
            courseReserveRecord.setCreateTime(new Date());
            courseReserveRecord.setCheck(0);
            courseReserveRecordService.addCourseReserveRecord(courseReserveRecord);
        }
        // 批量添加流水
        trxorderDetailService.addBatchTrxorderDetail(trxorderDetailList);
        result.put("order", trxorder);
        return result;
    }

    /**
     * 生成订单号 当前用户id+毫秒数
     *
     * @return
     */
    public String getOrderNum(Long userId) {
        return userId + DateUtils.toString(new Date(), "yyyyMMddHHmmssSSS");
    }

    /**
     * 根据id删除一个Trxorder
     *
     * @param id 要删除的id
     */
    public void deleteTrxorderById(Long id) {
        trxorderDao.deleteTrxorderById(id);
    }

    /**
     * 修改Trxorder
     *
     * @param trxorder 要修改的Trxorder
     */
    public void updateTrxorder(Trxorder trxorder) {
        trxorderDao.updateTrxorder(trxorder);
    }

    /**
     * 根据id获取单个Trxorder对象
     *
     * @param id 要查询的id
     * @return Trxorder
     */
    public Trxorder getTrxorderById(Long id) {
        return trxorderDao.getTrxorderById(id);
    }

    /**
     * 根据条件获取Trxorder列表
     *
     * @param trxorder 查询条件
     * @return List<Trxorder>
     */
    public List<Trxorder> getTrxorderList(Trxorder trxorder) {
        return trxorderDao.getTrxorderList(trxorder);
    }

    /**
     * 检查该用户是否可以观看该课程
     *
     * @param courseId
     * @param userId
     * @return
     */
    public boolean checkCourseLook(Long courseId, Long userId) {
        //查找售卖方式开关配置
        Map<String, Object> saleMap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) saleMap.get(WebSiteProfileType.sale.toString());
        if (map.get("verifyCourse").equals("OFF")) {//单课购买功能关闭
            return false;
        }
        Course course = courseService.getCourseById(courseId);
        if (course != null) {
            // 如果课程价格为0则可以观看
            if (course.getIsPay() <= 0) {// 免费的课程可以直接试听
                if (course.getIsReserve() <= 0) {
                    return true;
                } else {
                    if (courseReserveRecordService.checkCourseReserved(userId, courseId)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        if (userId == null || userId == 0) {//不再往下判断
            return false;
        }
        // 如果传入的参数为空则不可观看该课程
        if (ObjectUtils.isNull(courseId) || ObjectUtils.isNull(userId)) {
            return false;
        }
        Boolean isOk = (Boolean) cacheKit.get(MemConstans.USER_CANLOOK + "_" + courseId + "_" + userId);
        if (ObjectUtils.isNotNull(isOk)) {
            return true;
        }

        // 查询购买过的课程
        List<TrxorderDetail> trxorderDetailList = trxorderDetailService.getTrxorderDetailListBuy(userId);

        if (ObjectUtils.isNotNull(trxorderDetailList)) {
            List<Long> ids = new ArrayList<Long>();
            for (TrxorderDetail detail : trxorderDetailList) {
                ids.add(detail.getCourseId());
                if (detail.getCourseId().longValue() == courseId.longValue()) {
                    cacheKit.set(MemConstans.USER_CANLOOK + "_" + courseId + "_" + userId, true, MemConstans.USER_CANLOOK_TIME);
                    return true;
                }
            }
            // 再查询购买的课程中是否是套餐。查询套餐下是否包含该课程
            List<CourseDto> courseDtos = courseService.getCourseListPackage(ids);
            if (ObjectUtils.isNotNull(courseDtos)) {
                for (CourseDto courseDto : courseDtos) {
                    if (courseDto.getId().longValue() == courseId.longValue()) {
                        cacheKit.set(MemConstans.USER_CANLOOK + "_" + courseId + "_" + userId, true, MemConstans.USER_CANLOOK_TIME);
                        return true;
                    }
                }
            }

        }
        return false;
    }

    /**
     * 订单检查请求参数
     *
     * @param sourceMap
     * @return
     */
    public TrxReqData checkorderparam(Map<String, String> sourceMap) {
        TrxReqData reqData = new TrxReqData();
        String typestr = sourceMap.get("type").toString();// 购物类型 1 是课程
        // 下单类型
        if (StringUtils.isNotEmpty(typestr) && Long.valueOf(typestr).longValue() > 0) {
            reqData.setType(Long.valueOf(typestr));
        } else {
            return null;
        }
        String couponcode = sourceMap.get("couponcode"); // 优惠卷编码
        if (StringUtils.isNotEmpty(couponcode)) {
            reqData.setCouponCode(couponcode);
        }

        String userid = sourceMap.get("userid");// 用户id
        if (StringUtils.isNotEmpty(userid) && Long.valueOf(userid).longValue() > 0) {
            reqData.setUserId(Long.valueOf(userid));
        } else {
            return null;
        }

        String reqchanle = sourceMap.get("reqchanle");// 用户id
        if (StringUtils.isNotEmpty(reqchanle)) {
            reqData.setReqChannel(reqchanle);
        } else {
            return null;
        }

        String payType = sourceMap.get("payType");// 支付类型
        if (StringUtils.isNotEmpty(payType)) {
            reqData.setPayType(EnumUtil.transStringToEnum(PayType.class, payType));
        } else {
            return null;
        }

        String reqIp = sourceMap.get("reqIp");// 用户reqIp
        if (StringUtils.isNotEmpty(reqIp)) {
            reqData.setReqIp(reqIp);
        } else {
            reqData.setReqIp("");
        }
        return reqData;
    }

    /**
     * 免费赠送订单操作
     *
     * @param sourceMap 需要的参数
     * @throws BaseException
     */
    @Override
    public Map<String, Object> addFreeTrxorder(Map<String, String> sourceMap) throws BaseException {

        Map<String, Object> result = new HashMap<String, Object>();

        // 检查参数
        TrxReqData trxReqData = checkorderparam(sourceMap);
        if (trxReqData == null) {// 参数验证失败
            result.put("msg", "parame-rror");
            return result;
        }

        Course course = courseService.getCourseById(Long.valueOf(sourceMap.get("courseId")));
        if (course == null) {
            result.put("msg", "couser-is-null");
            return result;
        }
        /*
         * // 非0元直接返回 if (course.getCurrentprice().doubleValue() > 0) {
		 * result.put("msg", "course-Currentprice-not-0"); return result; }
		 */
        // 已经添加过无需重复添加
        List<TrxorderDetail> buyDetailList = trxorderDetailService.getTrxorderDetailListBuy(trxReqData.getUserId());
        if (buyDetailList != null && buyDetailList.size() > 0) {
            for (TrxorderDetail detail : buyDetailList) {
                if (detail.getCourseId().equals(course.getId())) {
                    result.put("msg", "course-already-success");
                    return result;
                }
            }
        }

        // 拼装订单数据
        Trxorder trxorder = new Trxorder();
        // 下单时间
        Date date = new Date();
        trxorder.setCreateTime(date);// 下单时间
        trxorder.setUserId(trxReqData.getUserId());
        trxorder.setRequestId(this.getOrderNum(trxReqData.getUserId()));// 交易请求号
        trxorder.setTrxStatus(TrxOrderStatus.SUCCESS.toString());// 交易装态,成功状态的
        trxorder.setPayType(trxReqData.getPayType().toString());// 支付类型 免费
        trxorder.setReqChannel(trxReqData.getReqChannel());
        trxorder.setReqIp(trxReqData.getReqIp());
        trxorder.setOrderAmount(course.getCurrentprice()); // 原始金额
        trxorder.setCouponAmount(course.getCurrentprice());// 优惠金额
        trxorder.setAmount(new BigDecimal(0));// 实际支付金额
        trxorder.setDescription(new Gson().toJson(sourceMap));
        trxorder.setPayTime(date);// 支付时间
        // 添加订单
        trxorderDao.addTrxorder(trxorder);

        // 添加流水表

        List<TrxorderDetail> trxorderDetailList = new ArrayList<TrxorderDetail>();
        // 创建流水
        TrxorderDetail trxorderDetail = new TrxorderDetail();
        // 用户id
        trxorderDetail.setUserId(trxReqData.getUserId());
        // 课程id
        trxorderDetail.setCourseId(course.getId());
        // 订单id
        trxorderDetail.setTrxorderId(trxorder.getId());
        // 订单类型
        trxorderDetail.setTrxStatus(TrxOrderStatus.SUCCESS.toString());// 流水支付状态
        // 有效期类型
        trxorderDetail.setLosetype(course.getLosetype());
        // 订单过期时间段
        trxorderDetail.setLoseAbsTime(course.getLoseAbsTime());
        // 订单过期时间点
        trxorderDetail.setLoseTime(course.getLoseTime());


        // 赠送的课程，到期时间
        if (course.getLosetype() == 0) {
            trxorderDetail.setAuthTime(course.getLoseAbsTime());
        } else if (course.getLosetype() == 1) { // 按天数计算
            // 按所写时间推移过期时间
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            now.set(Calendar.DATE, now.get(Calendar.DATE) + Integer.valueOf(course.getLoseTime()));
            trxorderDetail.setAuthTime(now.getTime());
        }
        // 如果课程已经过期，将课程从购物车中删除，并重新加载购物车
        if (date.getTime() > trxorderDetail.getAuthTime().getTime()) {
            result.put("msg", "courselosedate");
            throw new BaseException("课程已经过期");
        }

        // 下单时间
        trxorderDetail.setCreateTime(new Date());
        // 课程原始价格
        trxorderDetail.setSourcePrice(course.getSourceprice());
        // 课程实际支付价格
        trxorderDetail.setCurrentPrice(new BigDecimal(0));
        // 课程名
        trxorderDetail.setCourseName(course.getName());
        // 课程状态（未开始）
        trxorderDetail.setAuthStatus(AuthStatus.SUCCESS.toString());
        // 订单请求号
        trxorderDetail.setRequestId(trxorder.getRequestId());
        trxorderDetail.setLastUpdateTime(date);
        // 支付时间
        trxorderDetail.setPayTime(date);
        trxorderDetail.setDescription("");
        // 批量添加流水
        trxorderDetailList.add(trxorderDetail);
        trxorderDetailService.addBatchTrxorderDetail(trxorderDetailList);

        result.put("msg", "success");
        return result;

    }

    /**
     * 根据requestId获取Trxorder
     *
     * @param requestId
     * @return
     */
    public Trxorder getTrxorderByRequestId(String requestId) {
        return trxorderDao.getTrxorderByRequestId(requestId);
    }

    /**
     * 订单回调,余额支付订单操作 事物开启
     *
     * @param
     * @return
     * @throws StaleObjectStateException
     */
    public Map<String, String> updateCompleteOrder(TrxReqData trxReqData) throws AccountException, StaleObjectStateException {
        logger.info("updateCompleteOrder trxReqData:" + trxReqData);
        Map<String, String> res = new HashMap<String, String>();
        // userAccount要重新查询一次，否则乐观锁版本号异常
        UserAccount userAccount = userAccountService.getUserAccountByUserId(trxReqData.getUserId());
        if (!AccountStatus.ACTIVE.toString().equals(userAccount.getAccountStatus().toString())) {
            res.put(OrderConstans.RESCODE, "您的账户已冻结");
            res.put("requestId", trxReqData.getRequestId());
            logger.info("updateCompleteOrder no balance,RequestId:" + trxReqData.getRequestId() + ",BankAmount:" + trxReqData.getBankAmount() + ",Balance:" + userAccount.getBalance());
            return res; // 账户被冻结
        }
        // 计算此次订单使用的cash金额和vm金额
        BigDecimal amount = trxReqData.getAmount();// 订单所需支付的金额
        BigDecimal balance = userAccount.getBalance();// 账户余额
        BigDecimal useCashAmoun = new BigDecimal(0);
        BigDecimal useVmAmount = new BigDecimal(0);
        if (balance.compareTo(amount) < 0) {// 账户余额不足
            res.put(OrderConstans.RESCODE, "balance");
            res.put("amount", amount.toString());
            res.put("balance", balance.toString());
            res.put("bankAmount", amount.subtract(balance).toString());
            res.put("requestId", trxReqData.getRequestId());
            logger.info("updateCompleteOrder no balance,RequestId:" + trxReqData.getRequestId() + ",BankAmount:" + trxReqData.getBankAmount() + ",Balance:" + userAccount.getBalance());
            return res;
        }
        if (userAccount.getVmAmount().compareTo(amount) >= 0) {// vm余额大于等于扣款的金额,vm足够支付
            useVmAmount = amount;
        } else {// vm不够的时候 再扣除cash的余额
            useVmAmount = userAccount.getVmAmount();//
            useCashAmoun = amount.subtract(useVmAmount);// 需要扣除的cash的金额
        }
        Trxorder trxorder = getTrxorderByRequestId(trxReqData.getRequestId());
        trxorder.setCashAmount(useCashAmoun);
        trxorder.setVmAmount(useVmAmount);
        trxorder.setTrxStatus(TrxOrderStatus.SUCCESS.toString());
        trxorder.setPayTime(trxReqData.getCreateTime());
        trxorder.setPayType(trxReqData.getPayType().toString());
        List<Course> courses = getTrxCourseByRequestId(trxorder.getRequestId());//订单课程集合
        //验证优惠券信息
        if (trxorder.getCouponCodeId() > 0) {//订单使用了优惠券
            CouponCodeDTO couponCodeDTO = couponCodeService.getCouponCodeDTO(trxorder.getCouponCodeId());

            //验证优惠券编码
            Map<String, Object> map = couponCodeService.checkCode(couponCodeDTO, "", courses);
            if (map.get("msg") != "true") {//验证不通过，返回优惠券编码错误信息
                res.put(OrderConstans.RESCODE, map.get("msg").toString());
                res.put("amount", amount.toString());
                res.put("balance", balance.toString());
                res.put("bankAmount", amount.subtract(balance).toString());
                res.put("requestId", trxReqData.getRequestId());
                logger.info("updateCompleteOrder no balance,RequestId:" + trxReqData.getRequestId() + ",BankAmount:" + trxReqData.getBankAmount() + ",Balance:" + userAccount.getBalance());
                return res;
            }
        }
        // 扣款
        userAccountService.debit(userAccount, trxReqData.getAmount(), AccountHistoryType.SALES, trxReqData.getUserId(), trxReqData.getTrxorderId(), trxReqData.getRequestId(), trxReqData.getCreateTime(), trxReqData.getDescription(), true, AccountBizType.COURSE);
        // 更新订单的状态
        updateTrxorderStatusSuccess(trxorder);

        // 遍历订单课程集合，获得课程编号集合字符串
        String ids = "";
        if (courses != null) {
            for (Course course : courses) {
                if (ids.equals("")) {
                    ids = ids + course.getId();
                } else {
                    ids = "," + course.getId();
                }
            }
            // 根据ids修改所买课程的购买数量
            courseProfileDao.updateCourseBuyCount(ids);
        }

        //更改优惠券信息
        if (trxorder.getCouponCodeId() > 0) {//订单使用了优惠券
            CouponCodeDTO couponCodeDTO = couponCodeService.getCouponCodeDTO(trxorder.getCouponCodeId());
            couponService.updateCouponUserNum(couponCodeDTO.getCouponId());//更新优惠券使用数
            if (couponCodeDTO.getUseType() == 2) {//非无限次使用权的优惠券编码才更新状态
                CouponCode couponCode = new CouponCode();
                couponCode.setPayTime(new Date());
                couponCode.setStatus(2);//已使用
                couponCode.setTrxorderId(trxorder.getId());//订单id
                couponCode.setRequestId(trxorder.getRequestId());//订单请求号
                couponCode.setUserId(trxorder.getUserId());//用户id
                couponCode.setUseTime(new Date());//使用时间
                couponCode.setId(trxorder.getCouponCodeId());
                couponCodeService.updateCouponCode(couponCode);//更新优惠券编码使用后的信息
            }
        }
        try {
            //购买成功添加动态
            addDynamicForBuyCourse(trxReqData.getUserId(), trxorder.getRequestId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        res.put(OrderConstans.RESCODE, OrderConstans.SUCCESS);
        logger.info("updateCompleteOrder trxReqData success");
        return res;
    }

    /**
     * 订单回调支付成功,预处理查询
     *
     * @param
     * @return
     */
    public TrxReqData preQueryCompleteOrder(TrxReqData trxReqData) throws AccountException {
        // 查询用户账户
        UserAccount userAccount = userAccountService.getUserAccountByUserId(trxReqData.getUserId());
        // 账户状态为正常状态
        if (AccountStatus.ACTIVE.toString().equals(userAccount.getAccountStatus().toString())) {
            BigDecimal orderAmount = trxReqData.getAmount();// 订单的金额
            BigDecimal bankAmount = trxReqData.getBankAmount();// 本次支付回调的金额
            BigDecimal balance = userAccount.getBalance().subtract(userAccount.getForzenAmount());// 账户的可用余额
            // 充值的加余额不足支付本次订单的，改为充值操作
            if (bankAmount.add(balance).compareTo(orderAmount) < 0) {
                trxReqData.setRecharge(true);
            } else {
                trxReqData.setRecharge(false);
            }
        } else {
            // 此处冻结状态不管。可以充值进去，但是不允许消费
            // 只充值操作,不够支付改订单的。
            trxReqData.setRecharge(true);
        }
        trxReqData.setUserAccount(userAccount);
        return trxReqData;
    }

    /**
     * 更新订单状态为成功,网银的回调
     *
     * @param trxorder
     */
    public void updateTrxorderStatusSuccess(Trxorder trxorder) throws StaleObjectStateException {
        // 更新订单表状态
        Long cnt = trxorderDao.updateTrxorderStatusSuccess(trxorder);
        // 更新流水的状态
        TrxorderDetail trxorderDetail = new TrxorderDetail();
        trxorderDetail.setPayTime(trxorder.getPayTime());
        trxorderDetail.setAuthStatus(AuthStatus.SUCCESS.toString());
        trxorderDetail.setTrxStatus(trxorder.getTrxStatus());
        trxorderDetail.setRequestId(trxorder.getRequestId());
        Long cnt2 = trxorderDetailDao.updateTrxorderDetailStatusSuccess(trxorderDetail);
        System.out.println("wwwwwwwwwwwwwwwwwwwwwww          cnt:" + cnt);
        System.out.println("wwwwwwwwwwwwwwwwwwwwwww           cnt2:" + cnt2);
        if (cnt.longValue() == 0 || cnt2.longValue() == 0) {
            throw new StaleObjectStateException(StaleObjectStateException.OPTIMISTIC_UPDATE_NONE);
        }
    }

    /**
     * 添加 课程卡订单信息 流水信息
     */
    @Override
    public String addCourseCardOrder(Map<String, String> sourceMap) throws Exception {
        String returnMsg;

        TrxReqData trxReqData = checkorderparam(sourceMap);
        // 拼装订单数据
        Trxorder trxorder = new Trxorder();
        // 下单时间
        Date date = new Date();
        trxorder.setCreateTime(date);// 下单时间
        trxorder.setUserId(trxReqData.getUserId());
        trxorder.setRequestId(this.getOrderNum(trxReqData.getUserId()));// 交易请求号
        trxorder.setTrxStatus(TrxOrderStatus.SUCCESS.toString());// 交易装态,成功状态的
        trxorder.setPayType(PayType.CARD.toString());// 支付类型 免费
        trxorder.setReqChannel(trxReqData.getReqChannel());
        trxorder.setReqIp(trxReqData.getReqIp());
        trxorder.setOrderAmount(new BigDecimal(0)); // 原始金额
        trxorder.setCouponAmount(new BigDecimal(0));// 优惠金额
        trxorder.setAmount(new BigDecimal(0));// 实际支付金额
        trxorder.setDescription(new Gson().toJson(sourceMap));
        trxorder.setPayTime(date);// 支付时间
        trxorderDao.addTrxorder(trxorder);
        // 添加订单
        returnMsg = trxorder.getId() + "," + trxorder.getRequestId();
        String[] courseArray = sourceMap.get("courseIds").split(",");
        // 添加流水表
        List<TrxorderDetail> trxorderDetailList = new ArrayList<TrxorderDetail>();
        for (int i = 0; i < courseArray.length; i++) {
            Course cou = courseService.getCourseById(new Long(courseArray[i]));
            if (ObjectUtils.isNull(cou)) {
                continue;
            }
            // 创建流水
            TrxorderDetail trxorderDetail = new TrxorderDetail();
            // 用户id
            trxorderDetail.setUserId(trxReqData.getUserId());
            // 课程id
            trxorderDetail.setCourseId(new Long(courseArray[i]));
            // 订单id
            trxorderDetail.setTrxorderId(trxorder.getId());
            // 订单类型
            trxorderDetail.setTrxStatus(AuthStatus.SUCCESS.toString());// 流水支付状态
            // 有效期类型
            trxorderDetail.setLosetype(cou.getLosetype());
            // 订单过期时间段
            trxorderDetail.setLoseAbsTime(cou.getLoseAbsTime());
            // 订单过期时间点
            trxorderDetail.setLoseTime(cou.getLoseTime());
            // 到期时间
            if (cou.getLosetype() == 0) {
                trxorderDetail.setAuthTime(cou.getLoseAbsTime());
            }
            // 按天数计算
            if (cou.getLosetype() == 1) {
                // 按所写时间推移过期时间
                Calendar now = Calendar.getInstance();
                now.setTime(date);
                now.set(Calendar.DATE, now.get(Calendar.DATE) + Integer.valueOf(cou.getLoseTime()));
                trxorderDetail.setAuthTime(now.getTime());
            }

            // 下单时间
            trxorderDetail.setCreateTime(new Date());
            // 课程原始价格
            trxorderDetail.setSourcePrice(cou.getSourceprice());
            // 课程实际支付价格
            trxorderDetail.setCurrentPrice(cou.getCurrentprice());
            // 课程名
            trxorderDetail.setCourseName(cou.getName());
            // 课程状态
            trxorderDetail.setAuthStatus(AuthStatus.SUCCESS.toString());
            trxorderDetail.setPayTime(new Date());
            // 订单请求号
            trxorderDetail.setRequestId(trxorder.getRequestId());
            trxorderDetail.setLastUpdateTime(date);
            trxorderDetail.setDescription("");
            trxorderDetailList.add(trxorderDetail);
        }
        // 批量添加流水
        trxorderDetailService.addBatchTrxorderDetail(trxorderDetailList);
        return returnMsg;
    }

    /**
     * 订单分页查询 ,根据条件
     *
     * @param queryTrxorder
     * @param page
     * @return
     */
    public List<QueryTrxorder> queryOrderPageResult(QueryTrxorder queryTrxorder, PageEntity page) {

        return trxorderDao.queryOrderPageResult(queryTrxorder, page);
    }

    /**
     * 订单id查询流水的课程集合
     *
     * @return
     */
    public List<Course> getTrxCourseByRequestId(String requestId) {
        return trxorderDao.getTrxCourseByRequestId(requestId);
    }

    /**
     * 个人中心订单查询
     */
    public List<TrxOrderDTO> queryOrderForWebUc(QueryTrxorder queryTrxorder, PageEntity page) {
        return trxorderDao.queryOrderForUc(queryTrxorder, page);
    }

    public void addDynamicForBuyCourse(Long userId, String requestId) throws Exception {
        //订单课程集合
        List<Course> courses = getTrxCourseByRequestId(requestId);
        if (ObjectUtils.isNotNull(courses) && courses.size() > 0) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("userId", userId + "");//用户id
            map.put("bizId", courses.get(0).getId() + "");// 学员活动（事件）id 商品id 试卷id
            map.put("type", 12 + "");//11观看课程 12购买了商品 13 考试
            map.put("desc", "购买课程");// 动态描述
            map.put("title", courses.get(0).getName());// 辅助title
            map.put("assistId", 0L + "");// 辅助id
            String content = WebUtils.replaceTagHTML(courses.get(0).getTitle());
            if (StringUtils.isNotEmpty(content)) {// 回复的内容
                content = StringUtils.getLength(content, 300);
                map.put("content", content);
            } else {
                map.put("content", "");
            }
            map.put("url", CommonConstants.contextPath + "/front/couinfo/" + courses.get(0).getId());//操作url
            // 获得社区的开关是否打开
            Map<String, Object> websiteProfile = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.keyword.toString());
            if (ObjectUtils.isNotNull(websiteProfile)) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map1 = (Map<String, Object>) websiteProfile.get(WebSiteProfileType.keyword.toString());
                if (map1.get("verifySns").toString().equalsIgnoreCase("no")) { // 如果开关打开
                    snsHessianService.addDynamic(map);
                }
            }
        }
    }

    /**
     * 订单详情
     *
     * @param id
     * @return
     */
    public QueryTrxorder getOrderInfoById(Long id) {
        return trxorderDao.getOrderInfoById(id);
    }

    /**
     * 网站支付成功的订单数量和销售金额
     *
     * @return orderNum(key) 订单数
     * salesNum(key) 销售金额
     */
    public Map<String, Object> getOrderTotalAndSales() {
        return trxorderDao.getOrderTotalAndSales();
    }


    /**
     * 订单检查请求参数
     *
     * @param sourceMap
     * @return
     */
    public TrxReqData checkMobileOrderparam(Map<String, String> sourceMap) {
        TrxReqData reqData = new TrxReqData();
        String typestr = sourceMap.get("type").toString();// 购物类型 1 是课程
        // 下单类型
        if (StringUtils.isNotEmpty(typestr) && Long.valueOf(typestr).longValue() > 0) {
            reqData.setType(Long.valueOf(typestr));
        } else {
            return null;
        }

        String courseId = sourceMap.get("courseId"); //微站课程订单用
        if (StringUtils.isNotEmpty(courseId) && Long.valueOf(courseId).longValue() > 0) {
            reqData.setCourseId(Long.parseLong(courseId));
        }
        String couponcode = sourceMap.get("couponcode"); // 优惠卷编码
        if (StringUtils.isNotEmpty(couponcode)) {
            reqData.setCouponCode(couponcode);
        }

        String userid = sourceMap.get("userid");// 用户id
        if (StringUtils.isNotEmpty(userid) && Long.valueOf(userid).longValue() > 0) {
            reqData.setUserId(Long.valueOf(userid));
        } else {
            return null;
        }

        String reqchanle = sourceMap.get("reqchanle");// 用户id
        if (StringUtils.isNotEmpty(reqchanle)) {
            reqData.setReqChannel(reqchanle);
        } else {
            return null;
        }

        String payType = sourceMap.get("payType");// 支付类型
        if (StringUtils.isNotEmpty(payType)) {
            reqData.setPayType(EnumUtil.transStringToEnum(PayType.class, payType));
        } else {
            return null;
        }

        String reqIp = sourceMap.get("reqIp");// 用户reqIp
        if (StringUtils.isNotEmpty(reqIp)) {
            reqData.setReqIp(reqIp);
        } else {
            reqData.setReqIp("");
        }
        return reqData;
    }
}