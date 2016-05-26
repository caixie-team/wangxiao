package io.wangxiao.cash.service;

import io.wangxiao.bookstore.entity.BookDTO;
import io.wangxiao.bookstore.entity.QueryBookCondition;
import co.bluepx.edu.cash.BaseTest;
import io.wangxiao.cash.entity.CashOrderDTO;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bison on 12/30/15.
 */
public class CashSeviceTest extends BaseTest {

    @Autowired
    CashService cashService;

    private Gson gson = new Gson();


    /**
     * 测试按订单ID查询订单信息
     */
    @Test
    public void testFindCashOrderById() {

        System.out.println(gson.toJson(cashService.findById(11)));

    }
    @Test
    public void testFindCashOrderPage() {

        CashOrderDTO cashOrderDTO = new CashOrderDTO();
//        cashOrderDTO.setPayType("");
//        QueryBookCondition query = new QueryBookCondition();
//        query.setBookId(0);

        PageInfo<CashOrderDTO> cashOrderList = cashService.findUserCashOrderDTOByPage(cashOrderDTO, 1, 10);

        System.out.println(gson.toJson(cashOrderList));

    }
/*
  public PageInfo<QueryCourseAssess> findCourseAssessByPage(Long courseId, int pageNum, int pageSize) {
        Map map = new HashMap<>();
        map.put("courseId", courseId);
        return PageHelper
                .startPage(pageNum, pageSize)
                .doSelectPageInfo(
                        () -> baseDao.findCourseAssessList(map)
                );
    }
 */

}
