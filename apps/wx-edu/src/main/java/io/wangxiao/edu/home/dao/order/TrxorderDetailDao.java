package io.wangxiao.edu.home.dao.order;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.order.QueryTrxorderDetail;
import io.wangxiao.edu.home.entity.order.TrxorderDetail;

import java.util.List;

/**
 * TrxorderDetail管理接口
 */
public interface TrxorderDetailDao {

    /**
     * 添加TrxorderDetail
     *
     * @param trxorderDetail 要添加的TrxorderDetail
     * @return id
     */
    public java.lang.Long addTrxorderDetail(TrxorderDetail trxorderDetail);

    /**
     * 批量添加TrxorderDetail
     *
     * @param List<trxorderDetail> 要添加的TrxorderDetail
     * @return id
     */
    public void addBatchTrxorderDetail(List<TrxorderDetail> trxorderDetailList);

    /**
     * 根据id删除一个TrxorderDetail
     *
     * @param id 要删除的id
     */
    public void deleteTrxorderDetailById(Long id);

    /**
     * 修改TrxorderDetail
     *
     * @param trxorderDetail 要修改的TrxorderDetail
     */
    public void updateTrxorderDetail(TrxorderDetail trxorderDetail);

    /**
     * 根据id获取单个TrxorderDetail对象
     *
     * @param id 要查询的id
     * @return TrxorderDetail
     */
    public TrxorderDetail getTrxorderDetailById(Long id);

    /**
     * 根据条件获取TrxorderDetail列表
     *
     * @param trxorderDetail 查询条件
     * @return List<TrxorderDetail>
     */
    public List<TrxorderDetail> getTrxorderDetailList(TrxorderDetail trxorderDetail);

    /**
     * 查询该用户购买过的课程
     *
     * @param trxorderDetail 查询条件
     * @return List<TrxorderDetail>
     */
    public List<TrxorderDetail> getTrxorderDetailListBuy(Long userId);

    /**
     * 更新流水状态为成功，网银回调用
     *
     * @param trxorderDetail
     */
    public Long updateTrxorderDetailStatusSuccess(TrxorderDetail trxorderDetail);

    /**
     * 根据 订单条件查询分页
     *
     * @param trxorderDetail
     * @return List<QueryTrxorderDetail>
     */
    public List<QueryTrxorderDetail> queryTrxorderDetailByOrder(QueryTrxorderDetail trxorderDetail, PageEntity page);

    /**
     * 根据流水id关联用户表查询流水详情
     *
     * @param id return QueryTrxorderDetail
     */
    public QueryTrxorderDetail queryQueryTrxorderDetailById(Long id);


    /**
     * 判断已购买直播
     *
     * @param userId
     * @param courseId
     * @return
     */
    public int queryOrderByLive(Long userId, Long courseId);
}