package io.wangxiao.edu.home.dao.impl.member;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.member.MemberSaleDao;
import io.wangxiao.edu.home.entity.member.MemberSale;
import io.wangxiao.edu.home.entity.member.MemberSaleDTO;
import io.wangxiao.edu.home.entity.member.QueryMemberSale;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("memberSaleDao")
public class MemberSaleDaoImpl extends GenericDaoImpl implements MemberSaleDao {


    public void updateMemberSale(MemberSale memberSale) {
        this.update("MemberSaleMapper.updateMemberSale", memberSale);
    }

    public MemberSale getMemberSaleById(Long id) {
        return this.selectOne("MemberSaleMapper.getMemberSaleById", id);
    }

    /**
     * 添加会员商品
     *
     * @param memberSale
     */
    public void addMemberSale(MemberSale memberSale) {
        this.insert("MemberSaleMapper.addMemberSale", memberSale);
    }

    /**
     * 删除会员商品
     *
     * @param id
     */
    public void delMemberSale(Long id) {
        this.update("MemberSaleMapper.delMemberSale", id);
    }

    /**
     * 获取MemberSale分页
     *
     * @param queryMemberSale
     * @param page
     * @return
     */
    public List<MemberSaleDTO> getMemberSalePage(QueryMemberSale queryMemberSale, PageEntity page) {
        return this.queryForListPage("MemberSaleMapper.getMemberSalePage", queryMemberSale, page);
    }

    /**
     * type获取MemberSale集合
     *
     * @param type
     * @return
     */
    public List<MemberSale> getMemberSaleListByType(Long type) {
        return this.selectList("MemberSaleMapper.getMemberSaleByType", type);
    }

    /**
     * 会员服务级联删除会员商品
     *
     * @param id
     */
    public void delMemberSaleByType(Long id) {
        this.update("MemberSaleMapper.delMemberSaleByType", id);
    }
}
