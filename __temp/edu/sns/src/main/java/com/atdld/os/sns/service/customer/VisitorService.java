package com.atdld.os.sns.service.customer;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.customer.Visitor;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.customer.VisitorService
 * @description 访问者service
 * @Create Date : 2014-1-24 上午9:02:11
 */
public interface VisitorService {
    /**
     * 查询该用户的最近访客
     *
     * @param cusId 用户id
     * @return
     */
    public List<Visitor> queryVisitorByCusId(Long cusId) throws Exception;

    /**
     * 查询该用户的最近访客分页
     *
     * @param cusId 用户id
     * @return
     */
    public List<Visitor> queryVisitorByCusId(Long cusId, PageEntity page) throws Exception;

    /**
     * 添加访客
     *
     * @param visitor 访客
     * @return
     */
    public Long addVisitor(Visitor visitor);

    /**
     * 查询该用户的最近访问量
     *
     * @param cusId 用户id
     * @return
     */
    public int queryVisitorNumByCusId(Long cusId);
}
