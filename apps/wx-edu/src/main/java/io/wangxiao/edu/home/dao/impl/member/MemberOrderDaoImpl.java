package io.wangxiao.edu.home.dao.impl.member;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.member.MemberOrderDao;
import io.wangxiao.edu.home.entity.member.MemberOrder;
import io.wangxiao.edu.home.entity.member.MemberOrderDTO;
import io.wangxiao.edu.home.entity.member.QueryMemberOrder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("memberOrderDao")
public class MemberOrderDaoImpl extends GenericDaoImpl implements MemberOrderDao {

    public java.lang.Long addMemberOrder(MemberOrder memberOrder) {
        return this.insert("MemberOrderMapper.createMemberOrder", memberOrder);
    }

    public void deleteMemberOrderById(Long id) {
        this.delete("MemberOrderMapper.deleteMemberOrderById", id);
    }

    public void updateMemberOrder(MemberOrder memberOrder) {
        this.update("MemberOrderMapper.updateMemberOrder", memberOrder);
    }

    public MemberOrder getMemberOrderById(Long id) {
        return this.selectOne("MemberOrderMapper.getMemberOrderById", id);
    }

    /**
     * 根据id获取单个MemberOrderDTO对象
     *
     * @param id 要查询的id
     * @return MemberOrderDTO
     */
    public MemberOrderDTO getMemberOrderDTOById(Long id) {
        return this.selectOne("MemberOrderMapper.getMemberOrderDTOById", id);
    }

    public List<MemberOrder> getMemberOrderList(MemberOrder memberOrder) {
        return this.selectList("MemberOrderMapper.getMemberOrderList", memberOrder);
    }

    /**
     * 会员订单列表
     *
     * @param queryMemberOrder
     * @param page
     * @return
     */
    public List<MemberOrderDTO> getMemberOrderPage(QueryMemberOrder queryMemberOrder, PageEntity page) {
        return this.queryForListPage("MemberOrderMapper.getMemberOrderPage", queryMemberOrder, page);
    }

    /**
     * 根据requestId获取单个MemberOrder对象
     *
     * @param id
     * @return
     */
    public MemberOrder getMemberOrderByRequestId(String requestId) {
        return this.selectOne("MemberOrderMapper.getMemberOrderByRequestId", requestId);
    }

    /**
     * 更新订单状态为成功
     *
     * @param trxorder
     */
    public Long updateMemberOrderStatusSuccess(MemberOrder memberOrder) {
        return this.update("MemberOrderMapper.updateMemberOrderStatusSuccess", memberOrder);
    }
}
