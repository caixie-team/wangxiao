package com.atdld.os.edu.dao.impl.member;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.edu.entity.member.MemberSale;
import com.atdld.os.edu.entity.member.MemberSaleDTO;
import com.atdld.os.edu.entity.member.QueryMemberSale;
import com.atdld.os.edu.dao.member.MemberSaleDao;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;

/**
 *
 * MemberSale
 * User:
 * Date: 2014-09-26
 */
 @Repository("memberSaleDao")
public class MemberSaleDaoImpl extends GenericDaoImpl implements MemberSaleDao{


    public void updateMemberSale(MemberSale memberSale) {
        this.update("MemberSaleMapper.updateMemberSale",memberSale);
    }

    public MemberSale getMemberSaleById(Long id) {
        return this.selectOne("MemberSaleMapper.getMemberSaleById",id);
    }

    /**
     * 添加会员商品
     * @param memberSale
     */
    public void addMemberSale(MemberSale memberSale){
    	this.insert("MemberSaleMapper.addMemberSale",memberSale);
    }
    /**
     * 删除会员商品
     * @param id
     */
    public void delMemberSale(Long id){
    	this.update("MemberSaleMapper.delMemberSale", id);
    }
    /**
     * 获取MemberSale分页
     * @param queryMemberSale
     * @param page
     * @return
     */
    public List<MemberSaleDTO> getMemberSalePage(QueryMemberSale queryMemberSale,PageEntity page){
    	return this.queryForListPage("MemberSaleMapper.getMemberSalePage", queryMemberSale, page);
    }
    /**
     * type获取MemberSale集合
     * @param type
     * @return
     */
    public List<MemberSale> getMemberSaleListByType(Long type){
    	return this.selectList("MemberSaleMapper.getMemberSaleByType", type);
    }
    /**
     * 会员服务级联删除会员商品
     * @param id
     */
	public void delMemberSaleByType(Long id){
		this.update("MemberSaleMapper.delMemberSaleByType", id);
	}
}
