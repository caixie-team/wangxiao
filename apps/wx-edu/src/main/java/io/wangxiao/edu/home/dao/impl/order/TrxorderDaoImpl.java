package io.wangxiao.edu.home.dao.impl.order;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.common.util.StatisticsUtil;
import io.wangxiao.edu.home.constants.enums.StatisticsQueryType;
import io.wangxiao.edu.home.dao.order.TrxorderDao;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.entity.order.QueryTrxorder;
import io.wangxiao.edu.home.entity.order.TrxOrderDTO;
import io.wangxiao.edu.home.entity.order.Trxorder;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository("trxorderDao")
public class TrxorderDaoImpl extends GenericDaoImpl implements TrxorderDao {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public java.lang.Long addTrxorder(Trxorder trxorder) {
        //存入redis  今日订单数
        StatisticsUtil.incrActionOneDayOrActionCount(MemConstans.MEMFIX + StatisticsQueryType.ORDER_NUM.toString(), sdf.format(new Date()));
        return this.insert("TrxorderMapper.createTrxorder", trxorder);
    }

    public void deleteTrxorderById(Long id) {
        this.delete("TrxorderMapper.deleteTrxorderById", id);
    }

    public void updateTrxorder(Trxorder trxorder) {
        this.update("TrxorderMapper.updateTrxorder", trxorder);
    }

    public Trxorder getTrxorderById(Long id) {
        List<Trxorder> list = this.selectList("TrxorderMapper.getTrxorderById", id);
        if (ObjectUtils.isNotNull(list)) {
            return list.get(0);
        }
        return null;
    }


    public List<Trxorder> getTrxorderList(Trxorder trxorder) {
        return this.selectList("TrxorderMapper.getTrxorderList", trxorder);
    }

    /**
     * 根据requestId获取Trxorder
     *
     * @param 列表
     * @return Trxorder
     */
    public Trxorder getTrxorderByRequestId(String requestId) {
        List<Trxorder> list = this.selectList("TrxorderMapper.getTrxorderByRequestId", requestId);
        if (ObjectUtils.isNotNull(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 更新订单状态为成功
     *
     * @param trxorder
     */
    public Long updateTrxorderStatusSuccess(Trxorder trxorder) {
        return this.update("TrxorderMapper.updateTrxorderStatusSuccess", trxorder);
    }

    /**
     * 订单分页查询 ,根据条件
     *
     * @param QueryTrxorder
     * @return List
     */
    public List<QueryTrxorder> queryOrderPageResult(QueryTrxorder queryTrxorder,
                                                    PageEntity page) {
        return this.queryForListPage("TrxorderMapper.queryOrderPageResult", queryTrxorder, page);
    }

    /**
     * 个人中心订单查询
     *
     * @param QueryTrxorder
     * @return List
     */
    public List<TrxOrderDTO> queryOrderForUc(QueryTrxorder queryTrxorder, PageEntity page) {
        queryTrxorder.setPayType(queryTrxorder.getTrxStatus());
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
     * salesNum(key) 销售金额
     */
    public Map<String, Object> getOrderTotalAndSales() {
        List<Map<String, Object>> map = this.selectList("TrxorderMapper.getOrderTotalAndSales", null);
        if (ObjectUtils.isNotNull(map) && map.size() > 0) {
            return map.get(0);
        }
        return null;
    }
}
