package com.atdld.os.sns.dao.impl.dynamic;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.dao.dynamic.DynamicWebDao;
import com.atdld.os.sns.entity.dynamic.DynamicWeb;

/**
 * @author :
 * @ClassName com.atdld.os.sns.dao.impl.dynamic.DynamicWebDaoImpl
 * @description
 * @Create Date : 2014-1-11 下午2:22:47
 */
@Repository("dynamicWebDao")
public class DynamicWebDaoImpl extends GenericDaoImpl implements DynamicWebDao {
    /**
     * 添加动态 动态类型 1.加好友 2.微博动态 3.小组文章 4.加入小组 5.回复微博 6.回复文章 7.加关注 8.推荐建议 9.发表博文
     * 10.回复博文
     *
     * @param dynamicWeb
     */
    public void addDynamicWeb(DynamicWeb dynamicWeb) {
        this.insert("DynamicWebMapper.addDynamicWeb", dynamicWeb);
    }

    /**
     * 查看全站动态
     *
     * @return List<DynamicWeb> 动态列表list
     */
    public List<DynamicWeb> queryDynamicWebList(Long cusId, PageEntity page) {
        return this.queryForListPage("DynamicWebMapper.queryDynamicWebList", cusId, page);
    }

    /**
     * 显示小组组动态，只有小组成员才能看到
     *
     * @param cusId 用户Id
     * @return List<DynamicWeb> 1发表小组文章 2回复小组文章 3加入小组 list
     */
    public List<DynamicWeb> queryDynamicWebDisGroup(List<Long> ids, PageEntity page) {
        return this.queryForListPage("DynamicWebMapper.queryDynamicWebDisGroup", ids, page);
    }

    /**
     * 查看加好友的动态
     *
     * @param cus_id 用户Id
     * @return List<DynamicWeb> 加好友Friend list
     */
    public List<DynamicWeb> queryDynamicWebFriend(int cusId, PageEntity page) {
        return this.queryForListPage("DynamicWebMapper.queryDynamicWebFriend", cusId, page);
    }

    /**
     * 查看好友动态 1.发微博 2.回微博 3.加关注
     *
     * @param cusIds 用户id的List
     * @param page   分页参数
     * @return
     */
    public List<DynamicWeb> queryDynamicWebJunZiHui(List<Long> cusIds, PageEntity page) {
        return this.queryForListPage("DynamicWebMapper.queryDynamicWebJunZiHui", cusIds, page);
    }

    /**
     * 通过type查询动态
     *
     * @param page 分页参数
     * @return
     */
    public List<DynamicWeb> queryDynamicWebByTP(String type, PageEntity page)
            throws Exception {
        return this.queryForListPage("DynamicWebMapper.queryDynamicWebByTP", type, page);
    }

    /*
     * @see
     * com.atdld.os.sns.dao.dynamic.DynamicWebDao#queryDynamicWebFriend(java
     * .lang.Long, com.atdld.os.core.entity.PageEntity)
     */
    @Override
    public List<DynamicWeb> queryDynamicWebFriend(Long cusId, PageEntity page) {
        return this.queryForListPage("DynamicWebMapper.queryDynamicWebJunZiHui", cusId, page);
    }

    /**
     * 公共删除动态方法 1微博 2博文 3小组文章 4建议
     *
     * @param dynamicWeb
     * @return
     */
    public Long deleteDynamicWebByCondition(DynamicWeb dynamicWeb) {
        return this.delete("DynamicWebMapper.deleteDynamicWebByCondition", dynamicWeb);
    }

    /**
     * 公共删除 回复动态 1 回复微博 2回复博文 3回复小组文章 4回复建议
     *
     * @param dynamicWeb
     * @return
     */
    public Long deleteReplyDynamicByCondition(DynamicWeb dynamicWeb) {
        return this.delete("DynamicWebMapper.deleteReplyDynamicByCondition", dynamicWeb);
    }
}
