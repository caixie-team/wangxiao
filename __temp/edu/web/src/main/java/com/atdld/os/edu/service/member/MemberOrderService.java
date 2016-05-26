package com.atdld.os.edu.service.member;


import java.util.List;
import java.util.Map;

import com.atdld.os.common.exception.AccountException;
import com.atdld.os.common.exception.StaleObjectStateException;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.member.MemberOrder;
import com.atdld.os.edu.entity.member.MemberOrderDTO;
import com.atdld.os.edu.entity.member.MemberOrderReqData;
import com.atdld.os.edu.entity.member.QueryMemberOrder;

/**
 * MemberOrder管理接口
 * User:
 * Date: 2014-09-26
 */
public interface MemberOrderService {
	/**
	 * 添加MemberOrder
	 * 
	 * @param MemberOrder
	 *            要添加的MemberOrder
	 * @return id
	 */
	public Map<String, Object> addMemberOrder(Map<String, String> sourceMap) throws Exception ;
	
    /**
     * 添加MemberOrder
     * @param memberOrder 要添加的MemberOrder
     * @return id
     */
    public java.lang.Long addMemberOrder(MemberOrder memberOrder);

    /**
     * 根据id删除一个MemberOrder
     * @param id 要删除的id
     */
    public void deleteMemberOrderById(Long id);

    /**
     * 修改MemberOrder
     * @param memberOrder 要修改的MemberOrder
     */
    public void updateMemberOrder(MemberOrder memberOrder);

    /**
     * 根据id获取单个MemberOrder对象
     * @param id 要查询的id
     * @return MemberOrder
     */
    public MemberOrder getMemberOrderById(Long id);
    /**
     * 根据id获取单个MemberOrderDTO对象
     * @param id 要查询的id
     * @return MemberOrderDTO
     */
    public MemberOrderDTO getMemberOrderDTOById(Long id);
    /**
     * 根据requestId获取单个MemberOrder对象
     * @param id
     * @return
     */
    public MemberOrder getMemberOrderByRequestId(String requestId);
    /**
     * 根据条件获取MemberOrder列表
     * @param memberOrder 查询条件
     * @return List<MemberOrder>
     */
    public List<MemberOrder> getMemberOrderList(MemberOrder memberOrder);
    /**
     * 会员订单列表
     * @param queryMemberOrder
     * @param page
     * @return
     */
	public List<MemberOrderDTO> getMemberOrderPage(QueryMemberOrder queryMemberOrder, PageEntity page);
	/**
	 * 会员订单
	 * @param memberReqData
	 * @return
	 */
	public Map<String, String> updateCompleteMemberOrder( MemberOrderReqData memberReqData) throws AccountException, StaleObjectStateException ;
}