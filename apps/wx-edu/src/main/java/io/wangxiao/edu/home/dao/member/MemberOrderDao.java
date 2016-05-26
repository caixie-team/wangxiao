package io.wangxiao.edu.home.dao.member;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.member.MemberOrder;
import io.wangxiao.edu.home.entity.member.MemberOrderDTO;
import io.wangxiao.edu.home.entity.member.QueryMemberOrder;

import java.util.List;

/**
 * MemberOrder管理接口
 */
public interface MemberOrderDao {

    /**
     * 添加MemberOrder
     *
     * @param memberOrder 要添加的MemberOrder
     * @return id
     */
    java.lang.Long addMemberOrder(MemberOrder memberOrder);

    /**
     * 根据id删除一个MemberOrder
     *
     * @param id 要删除的id
     */
    void deleteMemberOrderById(Long id);

    /**
     * 修改MemberOrder
     *
     * @param memberOrder 要修改的MemberOrder
     */
    void updateMemberOrder(MemberOrder memberOrder);

    /**
     * 根据id获取单个MemberOrder对象
     *
     * @param id 要查询的id
     * @return MemberOrder
     */
    MemberOrder getMemberOrderById(Long id);

    /**
     * 根据条件获取MemberOrder列表
     *
     * @param memberOrder 查询条件
     * @return List<MemberOrder>
     */
    List<MemberOrder> getMemberOrderList(MemberOrder memberOrder);

    /**
     * 会员订单列表
     *
     * @param queryMemberOrder
     * @param page
     * @return
     */
    List<MemberOrderDTO> getMemberOrderPage(QueryMemberOrder queryMemberOrder, PageEntity page);

    /**
     * 根据requestId获取单个MemberOrder对象
     *
     * @param id
     * @return
     */
    MemberOrder getMemberOrderByRequestId(String requestId);

    /**
     * 更改订单成功后的状态
     *
     * @param memberOrder
     * @return
     */
    Long updateMemberOrderStatusSuccess(MemberOrder memberOrder);

    /**
     * 根据id获取单个MemberOrderDTO对象
     *
     * @param id 要查询的id
     * @return MemberOrderDTO
     */
    MemberOrderDTO getMemberOrderDTOById(Long id);
}