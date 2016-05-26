package io.wangxiao.edu.home.service.order;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.common.exception.AccountException;
import io.wangxiao.edu.common.exception.StaleObjectStateException;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.entity.order.QueryTrxorder;
import io.wangxiao.edu.home.entity.order.TrxOrderDTO;
import io.wangxiao.edu.home.entity.order.TrxReqData;
import io.wangxiao.edu.home.entity.order.Trxorder;

import java.util.List;
import java.util.Map;

/**
 * @description 订单操作
 */
public interface TrxorderService {
    /**
     * 添加Trxorder(用于APP)
     */
    Map<String, Object> appAddTrxorder(Map<String, String> sourceMap) throws Exception;

    /**
     * 下微站订单操作
     *
     * @param sourceMap 需要的参数
     * @return id
     */
    Map<String, Object> addMobileTrxorder(Map<String, String> sourceMap) throws Exception;

    /**
     * 下订单操作
     *
     * @param sourceMap 需要的参数
     * @return id
     */
    Map<String, Object> addTrxorder(Map<String, String> sourceMap) throws Exception;


    /**
     * 生成订单号
     *
     * @return
     */
    String getOrderNum(Long userId);

    /**
     * 根据id删除一个Trxorder
     *
     * @param id 要删除的id
     */
    void deleteTrxorderById(Long id);

    /**
     * 修改Trxorder
     *
     * @param trxorder 要修改的Trxorder
     */
    void updateTrxorder(Trxorder trxorder);

    /**
     * 根据id获取单个Trxorder对象
     *
     * @param id 要查询的id
     * @return Trxorder
     */
    Trxorder getTrxorderById(Long id);

    /**
     * 根据条件获取Trxorder列表
     *
     * @param trxorder 查询条件
     * @return List<Trxorder>
     */
    List<Trxorder> getTrxorderList(Trxorder trxorder);

    /**
     * 检查该用户是否可以观看该课程
     *
     * @param courseId
     * @param userId
     * @return
     */
    boolean checkCourseLook(Long courseId, Long userId);


    /**
     * 免费赠送下订单操作
     *
     * @param sourceMap 需要的参数
     * @return id
     */
    Map<String, Object> addFreeTrxorder(Map<String, String> sourceMap) throws Exception;


    /**
     * 根据requestId获取Trxorder
     *
     * @param 列表
     * @return Trxorder
     */
    Trxorder getTrxorderByRequestId(String requestId);

    /**
     * 订单回调支付成功操作
     *
     * @param
     * @return
     */
    Map<String, String> updateCompleteOrder(TrxReqData trxReqData) throws AccountException, StaleObjectStateException;


    /**
     * 订单回调支付成功,预处理查询
     *
     * @param
     * @return
     */
    TrxReqData preQueryCompleteOrder(TrxReqData trxReqData) throws AccountException;

    /**
     * 更新订单状态为成功,网银的回调
     *
     * @param trxorder
     */
    void updateTrxorderStatusSuccess(Trxorder trxorder) throws StaleObjectStateException;


    /**
     * 添加课程卡订单信息
     *
     * @param sourceMap
     * @return
     * @throws Exception
     */
    String addCourseCardOrder(Map<String, String> sourceMap) throws Exception;


    /**
     * 订单分页查询 ,根据条件
     *
     * @param QueryTrxorder
     * @return List
     */
    List<QueryTrxorder> queryOrderPageResult(QueryTrxorder queryTrxorder, PageEntity page);


    /**
     * 个人中心订单查询
     *
     * @param queryTrxorder
     * @param page
     * @return
     */
    List<TrxOrderDTO> queryOrderForWebUc(QueryTrxorder queryTrxorder, PageEntity page);


    /**
     * 订单的课程集合
     *
     * @param id
     * @return
     */
    List<Course> getTrxCourseByRequestId(String requestId);

    /**
     * 订单详情
     *
     * @param id
     * @return
     */
    QueryTrxorder getOrderInfoById(Long id);

    /**
     * 网站支付成功的订单数量和销售金额
     *
     * @return orderNum(key) 订单数
     * salesNum(key) 销售金额
     */
    Map<String, Object> getOrderTotalAndSales();

}