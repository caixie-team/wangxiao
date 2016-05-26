package com.atdld.os.sns.service.weibo;

import java.util.List;
import java.util.Map;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.friend.Friend;
import com.atdld.os.sns.entity.weibo.QueryWeiBo;
import com.atdld.os.sns.entity.weibo.WeiBo;

/**
 * @author
 * @ClassName WeiBoService
 * @package com.atdld.open.sns.service.weibo
 * @description 微博servise
 * @Create Date: 2013-12-10 下午3:01:54
 */
public interface WeiBoService {
    /**
     * 添加微博
     *
     * @param weiBo 微博
     * @throws Exception
     */
    public String addWeiBo(WeiBo weiBo) throws Exception;

    /**
     * 转发微博
     */
    public String addForward(Long weiboId, Long cusId, String content) throws Exception;

    /**
     * 删除微博
     *
     * @param weiBo 根据微博id
     * @throws Exception
     */
    public String delWeiBo(WeiBo weiBo) throws Exception;

    /**
     * 查询我的微博
     *
     * @param cusId  用户id
     * @param page   分页参数
     * @param status 是否公开的状态
     * @return List<QueryWeiBo> 我的微博list
     * @throws Exception
     */
    public List<QueryWeiBo> queryMyWeiBo(Long cusId, int status, PageEntity page) throws Exception;

    /**
     * 查询我关注的人的微博
     *
     * @param cusId    用具id
     * @param page分页参数
     * @return List<QueryWeiBo> 微博list
     * @throws Exception
     */
    public List<QueryWeiBo> queryAttentionWeiBo(Long cusId, PageEntity page) throws Exception;

    /**
     * 查询热门微博
     *
     * @param weiBo 微博
     * @param page  分页参数
     * @return List<QueryWeiBo> 微博list
     * @throws Exception
     */
    public List<QueryWeiBo> queryHotWeiBo(WeiBo weiBo, PageEntity page) throws Exception;

    /**
     * 查询一个星期发表微博最多的用户数
     *
     * @return List<QueryWeiBo>
     * @throws Exception
     */
    public List<QueryWeiBo> queryCustomerForWeiBoNumByWeek() throws Exception;

    /**
     * 评论最多的微博 根据评论数排序
     *
     * @param weiBo 传入cusId 用户id
     * @param page  分页参数
     * @return List<QueryWeiBo> 查询的微博list
     * @throws Exception
     */
    public List<QueryWeiBo> queryCommentMostWeiBo(WeiBo weiBo, PageEntity page) throws Exception;

    /**
     * 查询所有微博
     *
     * @param weiBo 微博
     * @param page  分页参数
     * @return List<QueryWeiBo>
     * @throws Exception
     */
    public List<QueryWeiBo> queryAllWeiBo(WeiBo weiBo, PageEntity page) throws Exception;


    /**
     * 查询我关注的用户的列表
     *
     * @param cusAttention 用户关注的实体
     * @param page         分页参数
     * @return List<Customer>用户list
     * @throws Exception
     */
    public List<SnsUserExpandDto> queryMyAttentionCustomer(Friend friend, PageEntity page) throws Exception;

    /**
     * 取消关注用户
     *
     * @param cusAttention 需要 当前用户id.取消者用户id
     * @throws Exception
     */
    public String delCusAttention(Friend friend) throws Exception;

    /**
     * 查询我的粉丝用户的列表
     *
     * @param cusAttention
     * @param page         分页参数
     * @return List<Customer> 用户关注的list
     * @throws Exception
     */
    public List<SnsUserExpandDto> queryMyFans(Friend friend, PageEntity page) throws Exception;

    /**
     * 设置该微博置顶
     *
     * @param weiBo 微博
     * @throws Exception
     */
    public String updateWeiBoForTop(WeiBo weiBo) throws Exception;

    /**
     * 设置该微博置顶
     *
     * @param weiBo 微博
     * @throws Exception
     */
    public void updateQuXiaoWeiBoForTop(WeiBo weiBo) throws Exception;

    /**
     * 后台查询微博
     *
     * @param weiBo 微博
     * @param page  分页参数
     * @return List<QueryWeiBo>
     * @throws Exception
     */
    public List<QueryWeiBo> queryAdminAllWeiBo(QueryWeiBo queryWeiBo, PageEntity page) throws Exception;

    /**
     * 通过微博id 查询微博
     *
     * @param weiBo 传入微博id
     * @return QueryWeiBo
     * @throws Exception
     */
    public QueryWeiBo queryWeiBoById(WeiBo weiBo) throws Exception;

    /**
     * 根据微博id让该微博评论数
     *
     * @param weiboId 微博id
     * @param count   要更新的数量
     */
    public void updateWeiBoCommentNumAddCount(Long weiboId, int count);

    /**
     * 根据微博id让该微博评论数减一
     *
     * @param weiboId 微博id
     */
    public void updateWeiBoCommentNumSubtractCount(Long weiboId);

    /**
     * 查询微博根据用户id
     *
     * @param userId num
     * @param status 是否公开的状态 1不显示公开内容 0全部显示
     * @return List<WeiBo>
     * @throws Exception
     */
    public List<WeiBo> queryPersonWeiBoById(Long userId, int status, int num) throws Exception;

    /**
     * lucene方法 查询传入微博id 查询微博
     *
     * @param wbIds 微博id的list
     * @return List<QueryWeiBo>
     * @throws Exception
     */
    public List<QueryWeiBo> getLuceneWeiBoByIds(List<Long> wbIds, Long cusId) throws Exception;

    /**
     * 查出这个微博id之后的微博行数和最大微博id
     *
     * @param weiBoId 起始微博id
     * @return Map<String,Object> 返回两个参数一个是微博行数和最大微博id
     * @throws Exception
     */
    public Map<String, Object> getWeiBoCountAfterId(Long weiBoId) throws Exception;

    /**
     * 查出从starWeiBoId（起始行数）开始的微博 一共 pageSize（需要查出多少条）条
     * 要在minWeiBoId（查询的where条件的最小微博id）和maxWeiBoId（查询的where条件的最大微博id）之间查
     *
     * @param starWeiBoId 起始行数
     * @param pageSize    需要查出多少条
     * @param minWeiBoId  查询的where条件的最小微博id
     * @param maxWeiBoId  查询的where条件的最大微博id
     * @return List<QueryWeiBo>
     * @throws Exception
     */
    public List<WeiBo> getQuestionByPageQuery(Long starWeiBoId, Long pageSize, Long minWeiBoId, Long maxWeiBoId) throws Exception;

}
