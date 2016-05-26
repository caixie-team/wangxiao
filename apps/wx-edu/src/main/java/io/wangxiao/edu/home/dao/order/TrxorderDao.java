package io.wangxiao.edu.home.dao.order;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.entity.order.QueryTrxorder;
import io.wangxiao.edu.home.entity.order.TrxOrderDTO;
import io.wangxiao.edu.home.entity.order.Trxorder;

import java.util.List;
import java.util.Map;

public interface TrxorderDao {

    /**
     * 添加Trxorder
     *
     * @param trxorder 要添加的Trxorder
     * @return id
     */
    java.lang.Long addTrxorder(Trxorder trxorder);

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
     * 根据requestId获取Trxorder
     *
     * @param 列表
     * @return Trxorder
     */
    Trxorder getTrxorderByRequestId(String requestId);

    /**
     * 更新订单状态为成功
     *
     * @param trxorder
     */
    Long updateTrxorderStatusSuccess(Trxorder trxorder);

    /**
     * 订单分页查询 ,根据条件
     *
     * @param QueryTrxorder
     * @return List
     */
    List<QueryTrxorder> queryOrderPageResult(QueryTrxorder queryTrxorder, PageEntity page);

    /**
     * 订单分页查询 ,根据条件
     *
     * @param QueryTrxorder
     * @return List
     */
    List<TrxOrderDTO> queryOrderForUc(QueryTrxorder queryTrxorder, PageEntity page);

    /**
     * 订单id查询流水的课程集合
     *
     * @param orderId
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