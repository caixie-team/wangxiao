package io.wangxiao.cash.dao;

import io.wangxiao.cash.entity.CashOrder;
import io.wangxiao.cash.entity.CashOrderDTO;
import io.wangxiao.core.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by bison on 1/18/16.
 */
public interface CashDao extends BaseDao<CashOrder>{

    /**
     * 根据查询条件查询用户订单信息
     *
     * @param cashOrderDTO
     * @return
     */
    List<CashOrderDTO> findUserCashOrderDTOList(@Param("e") CashOrderDTO cashOrderDTO);
}
