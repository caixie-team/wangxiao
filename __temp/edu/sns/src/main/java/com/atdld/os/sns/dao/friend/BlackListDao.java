package com.atdld.os.sns.dao.friend;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.friend.BlackList;

/**
 * @author
 * @ClassName BlackListDao
 * @package com.atdld.open.sns.dao.friend
 * @description 黑名单Dao接口
 * @Create Date: 2013-12-10 下午4:19:27
 */

public interface BlackListDao {
    /**
     * 添加黑名单
     *
     * @param blackList 黑名单实体
     * @throws Exception
     */
    public void addBlackList(BlackList blackList) throws Exception;

    /**
     * 检查黑名单
     *
     * @param blackList 黑名单实体
     * @return 0不存在1存在 检查是否存在黑名单里
     * @throws Exception
     */
    public int queryBlackListByCusIdAndCusBlacklistId(BlackList blackList) throws Exception;

    /**
     * 我的黑名单列表
     *
     * @param blackList 黑名单实体
     * @param page      分页参数
     * @return List<QueryCustomer> 我的黑名单list
     * @throws Exception
     */
    public List<SnsUserExpandDto> queryBlackListByCusId(BlackList blackList, PageEntity page) throws Exception;

    /**
     * 删除黑名单
     *
     * @param blackList 黑名单实体
     * @throws Exception
     */
    public Long delBlackList(BlackList blackList) throws Exception;
}
