package com.atdld.os.sns.dao.impl.friend;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.sns.dao.friend.BlackListDao;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.friend.BlackList;

/**
 * @author
 * @ClassName BlackListImpl
 * @package com.atdld.open.sns.dao.impl.friend
 * @description 黑名单Dao实现
 * @Create Date: 2013-12-10 下午4:20:43
 */
@Repository("blackListDao")
public class BlackListDaoImpl extends GenericDaoImpl implements BlackListDao {
    /**
     * 添加黑名单
     *
     * @param blackList 黑名单实体
     * @throws Exception
     */
    public void addBlackList(BlackList blackList) throws Exception {
        this.insert("BlackListMapper.addBlackList", blackList);// 添加黑名单
    }

    /**
     * 检查黑名单
     *
     * @param blackList 黑名单实体 通过cusId和cusblackListId
     * @return 0不存在1存在 检查是否存在黑名单里
     * @throws Exception
     */
    public int queryBlackListByCusIdAndCusBlacklistId(BlackList blackList) throws Exception {
        List<Integer> integerList = this.selectList("BlackListMapper.queryBlackListByCusIdAndCusBlacklistId", blackList);// 检查黑名单
        if (integerList != null && integerList.size() > 0) {
            return integerList.get(0);
        }
        return 0;
    }

    /**
     * 我的黑名单列表
     *
     * @param blackList 黑名单实体
     * @param page      分页参数
     * @return List<QueryCustomer> 我的黑名单list
     * @throws Exception
     */
    public List<SnsUserExpandDto> queryBlackListByCusId(BlackList blackList, PageEntity page) throws Exception {
        return this.queryForListPage("BlackListMapper.queryBlackListByCusId", blackList, page);// 我的黑名单列表
    }

    /**
     * 删除黑名单
     *
     * @param blackList 黑名单实体
     * @throws Exception
     */
    public Long delBlackList(BlackList blackList) throws Exception {
        return this.delete("BlackListMapper.delBlackList", blackList);// 删除黑名单
    }

}
