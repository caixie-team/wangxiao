package com.atdld.os.edu.dao.impl.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.dao.order.TrxorderDetailDao;
import com.atdld.os.edu.entity.order.QueryTrxorderDetail;
import com.atdld.os.edu.entity.order.TrxorderDetail;

/**
 *
 * TrxorderDetail
 * User:
 * Date: 2014-05-27
 */
 @Repository("trxorderDetailDao")
public class TrxorderDetailDaoImpl extends GenericDaoImpl implements TrxorderDetailDao{

    public java.lang.Long addTrxorderDetail(TrxorderDetail trxorderDetail) {
        return this.insert("TrxorderDetailMapper.createTrxorderDetail",trxorderDetail);
    }
    /**
     * 批量添加TrxorderDetail
     * @param List<trxorderDetail> 要添加的TrxorderDetail
     * @return id
     */
    public void addBatchTrxorderDetail(List<TrxorderDetail> trxorderDetailList){
        this.insert("TrxorderDetailMapper.addBatchTrxorderDetail",trxorderDetailList);
    }

    public void deleteTrxorderDetailById(Long id){
        this.delete("TrxorderDetailMapper.deleteTrxorderDetailById",id);
    }

    public void updateTrxorderDetail(TrxorderDetail trxorderDetail) {
        this.update("TrxorderDetailMapper.updateTrxorderDetail",trxorderDetail);
    }

    public TrxorderDetail getTrxorderDetailById(Long id) {
        return this.selectOne("TrxorderDetailMapper.getTrxorderDetailById",id);
    }

    public List<TrxorderDetail> getTrxorderDetailList(TrxorderDetail trxorderDetail) {
        return this.selectList("TrxorderDetailMapper.getTrxorderDetailList",trxorderDetail);
    }
    /**
     * 查询该用户购买过的课程
     * @param trxorderDetail 查询条件
     * @return List<TrxorderDetail>
     */
    public List<TrxorderDetail> getTrxorderDetailListBuy(Long userId){
        return this.selectList("TrxorderDetailMapper.getTrxorderDetailListBuy",userId);
    }
    
    /**
     * 更新流水状态为成功，网银回调用
     * @param trxorderDetail
     */
    public Long updateTrxorderDetailStatusSuccess(TrxorderDetail trxorderDetail){
        return this.update("TrxorderDetailMapper.updateTrxorderDetailStatusSuccess", trxorderDetail);
    }
    
    
    /**
     *根据 订单条件查询 流水分页
     *@param trxorderDetail,page
     *@return List<TrxorderDetail>
     *@Date 2014-09-28
     */
    public List<QueryTrxorderDetail> queryTrxorderDetailByOrder(QueryTrxorderDetail trxorderDetail,PageEntity page){
    	return this.queryForListPage("TrxorderDetailMapper.getTrxorderDetailListByCondition",trxorderDetail, page);
    	
    }
    
    
    /**
     * 根据流水id关联用户表查询流水详情
     * @param id
     * return QueryTrxorderDetail
     * @Date 2014-09-28
     */
    public QueryTrxorderDetail queryQueryTrxorderDetailById(Long id){
    	
    	return this.selectOne("TrxorderDetailMapper.getTrxorderDetailInfoById", id);
    }
    
    /**
     * 判断已购买直播
     * @param id
     * return QueryTrxorderDetail
     */
    public int queryOrderByLive(Long userId,Long courseId){
    	Map<String,Object> map=new HashMap<String, Object>();
    	map.put("userId", userId);
    	map.put("courseId", courseId);
    	return this.selectOne("TrxorderDetailMapper.queryOrderByLive",map );
    }
}
