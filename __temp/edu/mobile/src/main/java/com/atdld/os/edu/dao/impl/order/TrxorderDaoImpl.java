package com.atdld.os.edu.dao.impl.order;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.dao.order.TrxorderDao;
import com.atdld.os.edu.entity.course.Course;
import com.atdld.os.edu.entity.order.QueryTrxorder;
import com.atdld.os.edu.entity.order.TrxOrderDTO;
import com.atdld.os.edu.entity.order.Trxorder;

/**
 *
 * Trxorder
 * User:
 * Date: 2014-05-27
 */
 @Repository("trxorderDao")
public class TrxorderDaoImpl extends GenericDaoImpl implements TrxorderDao{

    public java.lang.Long addTrxorder(Trxorder trxorder) {
        return this.insert("TrxorderMapper.createTrxorder",trxorder);
    }

    public void deleteTrxorderById(Long id){
        this.delete("TrxorderMapper.deleteTrxorderById",id);
    }

    public void updateTrxorder(Trxorder trxorder) {
        this.update("TrxorderMapper.updateTrxorder",trxorder);
    }

    public Trxorder getTrxorderById(Long id) {
        List<Trxorder> list= this.selectList("TrxorderMapper.getTrxorderById",id);
        if(ObjectUtils.isNotNull(list)){
            return list.get(0);
        }
        return null;
    }
    
    
    

    public List<Trxorder> getTrxorderList(Trxorder trxorder) {
        return this.selectList("TrxorderMapper.getTrxorderList",trxorder);
    }
    /**
     * 根据requestId获取Trxorder
     * @param 列表
     * @return Trxorder
     */
    public Trxorder  getTrxorderByRequestId(String requestId ){
        List<Trxorder> list= this.selectList("TrxorderMapper.getTrxorderByRequestId",requestId);
        if(ObjectUtils.isNotNull(list)){
            return list.get(0);
        }
        return null;
    }
    /**
     * 更新订单状态为成功
     * @param trxorder
     */
    public Long updateTrxorderStatusSuccess(Trxorder trxorder){
        return this.update("TrxorderMapper.updateTrxorderStatusSuccess", trxorder);
    }
    
    /**
     * 订单分页查询 ,根据条件
     * @param QueryTrxorder
     * @return List
     */
    public List<QueryTrxorder> queryOrderPageResult(QueryTrxorder queryTrxorder,
			PageEntity page) {
		return this.queryForListPage("TrxorderMapper.queryOrderPageResult", queryTrxorder, page);
	}
    /**
     * 个人中心订单查询
     * @param QueryTrxorder
     * @return List
     */
    public List<TrxOrderDTO> queryOrderForUc(QueryTrxorder queryTrxorder, PageEntity page) {
    	return this.queryForListPage("TrxorderMapper.queryOrderForUc", queryTrxorder, page);
    }

	
	public List<Course> getTrxCourseByRequestId(String requestId) {
		
		return this.selectList("TrxorderMapper.getTrxCourseByRequestId", requestId);
	}

	public QueryTrxorder getOrderInfoById(Long id) {
		return this.selectOne("TrxorderMapper.getOrderInfoById", id);
	}
	/**
	 * 网站支付成功的订单数量和销售金额
	 * 
	 * @return orderNum(key) 订单数
	 *         salesNum(key) 销售金额
	 */
	public Map<String,Object> getOrderTotalAndSales() {
		List<Map<String,Object>> map=this.selectList("TrxorderMapper.getOrderTotalAndSales", null);
		if(ObjectUtils.isNotNull(map)&&map.size()>0){
			return map.get(0);
		}
		return null;
	}
}
