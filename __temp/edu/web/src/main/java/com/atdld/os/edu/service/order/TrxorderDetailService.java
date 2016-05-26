package com.atdld.os.edu.service.order;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.order.QueryTrxorderDetail;
import com.atdld.os.edu.entity.order.TrxorderDetail;

/**
 * TrxorderDetail管理接口
 * User:
 * Date: 2014-05-27
 */
public interface TrxorderDetailService {

    /**
     * 添加TrxorderDetail
     * @param trxorderDetail 要添加的TrxorderDetail
     * @return id
     */
    public java.lang.Long addTrxorderDetail(TrxorderDetail trxorderDetail);

    /**
     * 批量添加TrxorderDetail
     * @param List<trxorderDetail> 要添加的TrxorderDetail
     * @return id
     */
    public void addBatchTrxorderDetail(List<TrxorderDetail> trxorderDetailList);

    /**
     * 根据id删除一个TrxorderDetail
     * @param id 要删除的id
     */
    public void deleteTrxorderDetailById(Long id);

    /**
     * 修改TrxorderDetail
     * @param trxorderDetail 要修改的TrxorderDetail
     */
    public void updateTrxorderDetail(TrxorderDetail trxorderDetail);

    /**
     * 根据id获取单个TrxorderDetail对象
     * @param id 要查询的id
     * @return TrxorderDetail
     */
    public TrxorderDetail getTrxorderDetailById(Long id);

    /**
     * 查询该用户购买过的课程
     * @param trxorderDetail 查询条件
     * @return List<TrxorderDetail>
     */
    public List<TrxorderDetail> getTrxorderDetailListBuy(Long userId);
    
    
    /**
     * 根据条件获取TrxorderDetail列表
     * @param trxorderDetail 查询条件
     * @return List<TrxorderDetail>
     */
    public List<TrxorderDetail> getTrxorderDetailList(TrxorderDetail trxorderDetail);
    	
    
    
    /**
     * 根据条件获取TrxorderDetail 流水列表分页
     * @param trxorderDetail 查询条件
     * @return List<QueryTrxorderDetail>
     */
    public List<QueryTrxorderDetail> queryTrxorderDetailByOrder(QueryTrxorderDetail trxorderDetail,PageEntity page);
    
    
    /**
     * 根据流水id关联用户表查询流水详情
     * @param id
     * return QueryTrxorderDetail
     * @Date  2014-09-28
     */
    public QueryTrxorderDetail queryQueryTrxorderDetailById(Long id);
    
}