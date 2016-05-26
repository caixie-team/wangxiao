package com.atdld.os.edu.dao.member;
import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.member.MemberSale;
import com.atdld.os.edu.entity.member.MemberSaleDTO;
import com.atdld.os.edu.entity.member.QueryMemberSale;

/**
 * MemberSale管理接口
 * User:
 * Date: 2014-09-26
 */
public interface MemberSaleDao {

	/**
     * 修改MemberSale(价格，开通天数)
     * @param memberSale 要修改的MemberSale
     */
    public void updateMemberSale(MemberSale memberSale);

    /**
     * 根据id获取单个MemberSale对象
     * @param id 要查询的id
     * @return MemberSale
     */
    public MemberSale getMemberSaleById(Long id);

    /**
     * 添加会员商品
     * @param memberSale
     */
    public void addMemberSale(MemberSale memberSale);
    /**
     * 删除会员商品
     * @param id
     */
    public void delMemberSale(Long id);
    /**
     * 获取MemberSale分页
     * @param queryMemberSale
     * @param page
     * @return
     */
    public List<MemberSaleDTO> getMemberSalePage(QueryMemberSale queryMemberSale,PageEntity page);
    /**
     * type获取MemberSale集合
     * @param type
     * @return
     */
    public List<MemberSale> getMemberSaleListByType(Long type);
    /**
     * 会员服务级联删除会员商品
     * @param id
     */
	public void delMemberSaleByType(Long id);
}