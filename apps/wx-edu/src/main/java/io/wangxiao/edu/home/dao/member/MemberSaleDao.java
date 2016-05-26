package io.wangxiao.edu.home.dao.member;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.member.MemberSale;
import io.wangxiao.edu.home.entity.member.MemberSaleDTO;
import io.wangxiao.edu.home.entity.member.QueryMemberSale;

import java.util.List;

/**
 * MemberSale管理接口
 */
public interface MemberSaleDao {

    /**
     * 修改MemberSale(价格，开通天数)
     *
     * @param memberSale 要修改的MemberSale
     */
    void updateMemberSale(MemberSale memberSale);

    /**
     * 根据id获取单个MemberSale对象
     *
     * @param id 要查询的id
     * @return MemberSale
     */
    MemberSale getMemberSaleById(Long id);

    /**
     * 添加会员商品
     *
     * @param memberSale
     */
    void addMemberSale(MemberSale memberSale);

    /**
     * 删除会员商品
     *
     * @param id
     */
    void delMemberSale(Long id);

    /**
     * 获取MemberSale分页
     *
     * @param queryMemberSale
     * @param page
     * @return
     */
    List<MemberSaleDTO> getMemberSalePage(QueryMemberSale queryMemberSale, PageEntity page);

    /**
     * type获取MemberSale集合
     *
     * @param type
     * @return
     */
    List<MemberSale> getMemberSaleListByType(Long type);

    /**
     * 会员服务级联删除会员商品
     *
     * @param id
     */
    void delMemberSaleByType(Long id);
}