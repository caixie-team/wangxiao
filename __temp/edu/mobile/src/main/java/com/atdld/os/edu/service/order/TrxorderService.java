package com.atdld.os.edu.service.order;

import java.util.List;
import java.util.Map;

import com.atdld.os.common.exception.AccountException;
import com.atdld.os.common.exception.StaleObjectStateException;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.course.Course;
import com.atdld.os.edu.entity.order.QueryTrxorder;
import com.atdld.os.edu.entity.order.TrxOrderDTO;
import com.atdld.os.edu.entity.order.TrxReqData;
import com.atdld.os.edu.entity.order.Trxorder;

/**
 * 
 * @ClassName  com.atdld.os.edu.service.order.TrxorderService
 * @description 订单操作
 * @author :
 * @Create Date : 2014-7-2 上午9:31:29
 */
public interface TrxorderService {

    /**
     * 下微站订单操作
     * @param sourceMap 需要的参数
     * @return id
     */
    public Map<String, Object> addMobileTrxorder(Map<String, String> sourceMap) throws Exception;
    /**生成订单号
     * @return
     */
    public String getOrderNum(Long userId);
    /**
     * 根据id删除一个Trxorder
     * @param id 要删除的id
     */
    public void deleteTrxorderById(Long id);

    /**
     * 修改Trxorder
     * @param trxorder 要修改的Trxorder
     */
    public void updateTrxorder(Trxorder trxorder);

    /**
     * 根据id获取单个Trxorder对象
     * @param id 要查询的id
     * @return Trxorder
     */
    public Trxorder getTrxorderById(Long id);

    /**
     * 根据条件获取Trxorder列表
     * @param trxorder 查询条件
     * @return List<Trxorder>
     */
    public List<Trxorder> getTrxorderList(Trxorder trxorder);
    
    /**  检查该用户是否可以观看该课程
     * @param courseId
     * @param userId
     * @return
     */
    public boolean checkCourseLook(Long courseId, Long userId);
    
    
   
    /**
     * 根据requestId获取Trxorder
     * @param 列表
     * @return Trxorder
     */
    public Trxorder  getTrxorderByRequestId(String requestId );
    
    /**
     * 订单回调支付成功操作
     * @param
     * @return
     */
    public Map<String, String> updateCompleteOrder(TrxReqData trxReqData)throws AccountException, StaleObjectStateException;
    
    
    /**
     * 订单回调支付成功,预处理查询
     * @param
     * @return
     */
    public TrxReqData preQueryCompleteOrder(TrxReqData trxReqData)throws AccountException;
    
    /**
     * 更新订单状态为成功,网银的回调
     * @param trxorder
     *//*
    public void updateTrxorderStatusSuccess(Trxorder trxorder) throws StaleObjectStateException;*/
    
    
    
    /**
     * 添加课程卡订单信息
     * @param sourceMap
     * @return
     * @throws Exception
     */
    public String addCourseCardOrder(Map<String,String> sourceMap) throws Exception ;
    
    
    /**
     * 订单分页查询 ,根据条件
     * @param QueryTrxorder
     * @return List
     */
    public List<QueryTrxorder> queryOrderPageResult(QueryTrxorder queryTrxorder,PageEntity page);
    
    
    /**
     * 个人中心订单查询
     * @param queryTrxorder
     * @param page
     * @return
     */
    public List<TrxOrderDTO> queryOrderForWebUc(QueryTrxorder queryTrxorder,PageEntity page);
    
    
    
    /**
     * 订单的课程集合
     * @param id
     * @return
     */
	public List<Course> getTrxCourseByRequestId(String requestId);
	/**
	 * 订单详情
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