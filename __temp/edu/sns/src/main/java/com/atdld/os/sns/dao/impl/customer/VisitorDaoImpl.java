package com.atdld.os.sns.dao.impl.customer;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.dao.customer.VisitorDao;
import com.atdld.os.sns.entity.customer.Visitor;

/**
 * @author :
 * @ClassName com.atdld.os.sns.dao.impl.customer.VisitorDaoImpl
 * @description 访问者Dao
 * @Create Date : 2014-1-24 上午9:10:56
 */
@Repository("visitorDao")
public class VisitorDaoImpl extends GenericDaoImpl implements VisitorDao {

    /**
     * 查询该用户的最近访客
     *
     * @param cusId 用户id
     * @return
     */
    public List<Visitor> queryVisitorByCusId(Long cusId) {
        return this.selectList("VisitorMapper.queryVisitorByCusId", cusId);// 查询该用户的最近访客
    }

    /**
     * 查询该用户的最近访客分页
     *
     * @param cusId 用户id
     * @return
     */
    public List<Visitor> queryVisitorByCusId(Long cusId, PageEntity page) {
        return this.queryForListPage("VisitorMapper.queryVisitorByCusIdpage", cusId, page);// 查询该用户的最近访客
    }

    /**
     * 添加访客
     *
     * @param visitor 访客
     * @return
     */
    public Long addVisitor(Visitor visitor) {
        return this.insert("VisitorMapper.addVisitor", visitor);// 添加访客
    }

    /**
     * 查询访客是否访问过我的空间
     *
     * @param visitor 访客 传入cusId 和visitorCusId
     * @return
     */
    public int clickVisitorByCusIdAndVisitorCusId(Visitor visitor) {
        return this.selectOne("VisitorMapper.clickVisitorByCusIdAndVisitorCusId", visitor);// 查询访客是否访问过我的空间
    }

    /**
     * 更新访客添加时间
     *
     * @param visitor 访客 传入cusId 和visitorCusId
     * @return
     */
    public Long updateVisitorByCusIdAndVisitorCusId(Visitor visitor) {
        return this.update("VisitorMapper.updateVisitorByCusIdAndVisitorCusId", visitor);// 更新访客添加时间
    }

    /**
     * 查询该用户的最近访问量
     *
     * @param cusId 用户id
     * @return
     */
    public int queryVisitorNumByCusId(Long cusId) {
        return this.selectOne("VisitorMapper.queryVisitorNumByCusId", cusId);// 查询该用户的最近访问量
    }

}
