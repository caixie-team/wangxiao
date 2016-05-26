package io.wangxiao.cash.service;

import io.wangxiao.cash.dao.CashDao;
import io.wangxiao.cash.entity.CashOrder;
import io.wangxiao.cash.entity.CashOrderDTO;
import io.wangxiao.core.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * Created by bison on 1/18/16.
 */
@Service
public class CashService extends BaseService<CashOrder, CashDao>{

    /**
     * 根据条件查询订单 DTO 分页列表
     *
     * @param cashOrderDTO
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<CashOrderDTO> findUserCashOrderDTOByPage(CashOrderDTO cashOrderDTO, int pageNum, int pageSize){


        return PageHelper
                .startPage(pageNum, pageSize)
                .doSelectPageInfo(
                        () -> baseDao.findUserCashOrderDTOList(cashOrderDTO)
                );
    }
}
