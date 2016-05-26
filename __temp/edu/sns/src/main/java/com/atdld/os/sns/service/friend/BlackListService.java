package com.atdld.os.sns.service.friend;


import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.friend.BlackList;

/**
 * @author
 * @ClassName BlackListService
 * @package com.atdld.open.sns.service.friend
 * @description 黑名单接口
 * @Create Date: 2013-12-10 下午4:18:58
 */

public interface BlackListService {
    /**
     * 添加黑名单
     *
     * @param blackList 黑名单实体
     * @throws Exception
     */
    public String addBlackList(BlackList blackList) throws Exception;

    /**
     * 检查黑名单
     *
     * @param blackList 黑名单实体
     * @return int 返回是否存在该黑名单如果等于1则存在等于0 则不存在
     * @throws Exception
     */
    public int queryBlackListByCusIdAndCusBlacklistId(BlackList blackList) throws Exception;

    /**
     * 我的黑名单列表
     *
     * @param blackList 黑名单实体
     * @param page      分页参数
     * @return List<QueryCustomer> 黑名当列表
     * @throws Exception
     */
    public List<SnsUserExpandDto> queryBlackListByCusId(BlackList blackList, PageEntity page) throws Exception;

    /**
     * 删除黑名单
     *
     * @param blackList 黑名单实体
     * @throws Exception
     */
    public String delBlackList(BlackList blackList) throws Exception;
}
