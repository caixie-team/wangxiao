package io.wangxiao.edu.home.service.cash;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.cash.CashOrder;
import io.wangxiao.edu.home.entity.cash.CashOrderDTO;

import java.util.List;
import java.util.Map;

/**
 * CashOrder管理接口
 */
public interface CashOrderService {

    /**
     * 添加CashOrder
     *
     * @param cashOrder 要添加的CashOrder
     * @return id
     */
    Map<String, Object> addCashOrder(Map<String, String> sourceMap) throws Exception;

    /**
     * 根据id删除一个CashOrder
     *
     * @param id 要删除的id
     */
    void delCashOrderById(Long id);

    /**
     * 修改CashOrder
     *
     * @param CashOrder 要修改的CashOrder
     */
    void updateCashOrder(CashOrder cashOrder);

    /**
     * 根据id获取单个CashOrder对象
     *
     * @param id 要查询的id
     * @return CashOrder
     */
    CashOrder getCashOrderById(Long id);

    /**
     * 会员订单列表
     *
     * @param queryCashOrder
     * @param page
     * @return
     */
    List<CashOrderDTO> getCashOrderPage(CashOrderDTO cashOrderDTO, PageEntity page);

    /**
     * 根据requestId获取单个CashOrder对象
     *
     * @param id
     * @return
     */
    CashOrder getCashOrderByRequestId(String requestId);

    /**
     * 更改订单成功后的状态
     *
     * @param cashOrder
     * @return
     */
    Long updateCashOrderStatusSuccess(CashOrder cashOrder);

    /**
     * 根据id获取单个CashOrderDTO对象
     *
     * @param id 要查询的id
     * @return CashOrderDTO
     */
    CashOrderDTO getCashOrderDTOById(Long id);
}