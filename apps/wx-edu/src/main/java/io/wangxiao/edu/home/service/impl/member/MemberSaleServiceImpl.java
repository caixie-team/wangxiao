package io.wangxiao.edu.home.service.impl.member;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.member.MemberSaleDao;
import io.wangxiao.edu.home.entity.member.MemberSale;
import io.wangxiao.edu.home.entity.member.MemberSaleDTO;
import io.wangxiao.edu.home.entity.member.QueryMemberSale;
import io.wangxiao.edu.home.service.member.MemberSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MemberSale管理接口
 */
@Service("memberSaleService")
public class MemberSaleServiceImpl implements MemberSaleService {

    @Autowired
    private MemberSaleDao memberSaleDao;

    /**
     * 修改MemberSale(价格，开通天数)
     *
     * @param memberSale 要修改的MemberSale
     */
    public void updateMemberSale(MemberSale memberSale) {
        memberSaleDao.updateMemberSale(memberSale);
    }

    /**
     * 根据id获取单个MemberSale对象
     *
     * @param id 要查询的id
     * @return MemberSale
     */
    public MemberSale getMemberSaleById(Long id) {
        return memberSaleDao.getMemberSaleById(id);
    }

    /**
     * 添加会员商品
     *
     * @param memberSale
     */
    public void addMemberSale(MemberSale memberSale) {
        memberSaleDao.addMemberSale(memberSale);
    }

    /**
     * 删除会员商品
     *
     * @param id
     */
    public void delMemberSale(Long id) {
        memberSaleDao.delMemberSale(id);
    }

    /**
     * 获取MemberSale分页
     *
     * @param queryMemberSale
     * @param page
     * @return
     */
    public List<MemberSaleDTO> getMemberSalePage(QueryMemberSale queryMemberSale, PageEntity page) {
        return memberSaleDao.getMemberSalePage(queryMemberSale, page);
    }

    /**
     * type获取MemberSale集合
     *
     * @param type
     * @return
     */
    public List<MemberSale> getMemberSaleListByType(Long type) {
        return memberSaleDao.getMemberSaleListByType(type);
    }

    /**
     * 会员服务级联删除会员商品
     *
     * @param id
     */
    public void delMemberSaleByType(Long id) {
        memberSaleDao.delMemberSaleByType(id);
    }
}