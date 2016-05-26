package com.atdld.os.edu.dao.order;

import java.util.List;
import java.util.Map;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.course.Course;
import com.atdld.os.edu.entity.order.QueryTrxorder;
import com.atdld.os.edu.entity.order.TrxOrderDTO;
import com.atdld.os.edu.entity.order.Trxorder;

/**
 * Trxorder管理接口 User:  Date: 2014-05-27
 */
public interface TrxorderDao {

	/**
	 * 添加Trxorder
	 * 
	 * @param trxorder
	 *            要添加的Trxorder
	 * @return id
	 */
	public java.lang.Long addTrxorder(Trxorder trxorder);

	/**
	 * 根据id删除一个Trxorder
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteTrxorderById(Long id);

	/**
	 * 修改Trxorder
	 * 
	 * @param trxorder
	 *            要修改的Trxorder
	 */
	public void updateTrxorder(Trxorder trxorder);

	/**
	 * 根据id获取单个Trxorder对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return Trxorder
	 */
	public Trxorder getTrxorderById(Long id);

	/**
	 * 根据条件获取Trxorder列表
	 * 
	 * @param trxorder
	 *            查询条件
	 * @return List<Trxorder>
	 */
	public List<Trxorder> getTrxorderList(Trxorder trxorder);

	/**
	 * 根据requestId获取Trxorder
	 * 
	 * @param 列表
	 * @return Trxorder
	 */
	public Trxorder getTrxorderByRequestId(String requestId);

	/**
	 * 更新订单状态为成功
	 * 
	 * @param trxorder
	 */
	public Long updateTrxorderStatusSuccess(Trxorder trxorder);

	/**
	 * 订单分页查询 ,根据条件
	 * 
	 * @param QueryTrxorder
	 * @return List
	 */
	public List<QueryTrxorder> queryOrderPageResult(QueryTrxorder queryTrxorder, PageEntity page);

	/**
	 * 订单分页查询 ,根据条件
	 * 
	 * @param QueryTrxorder
	 * @return List
	 */
	public List<TrxOrderDTO> queryOrderForUc(QueryTrxorder queryTrxorder, PageEntity page);

	/**
	 * 订单id查询流水的课程集合
	 * 
	 * @param orderId
	 * @return
	 */
	public List<Course> getTrxCourseByRequestId(String requestId);

	/**
	 * 订单详情
	 * 
	 * @param id
	 * @return
	 */
	public QueryTrxorder getOrderInfoById(Long id);

	/**
	 * 网站支付成功的订单数量和销售金额
	 * 
	 * @return orderNum(key) 订单数
	 *         salesNum(key) 销售金额
	 */
	public Map<String,Object> getOrderTotalAndSales();
}