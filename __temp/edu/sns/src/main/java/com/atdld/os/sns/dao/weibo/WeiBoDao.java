package com.atdld.os.sns.dao.weibo;

import java.util.List;
import java.util.Map;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.weibo.QueryWeiBo;
import com.atdld.os.sns.entity.weibo.WeiBo;

/**
 * @author
 * @ClassName WeiBoDao
 * @package com.atdld.open.sns.dao.weibo
 * @description 微博Dao的接口
 * @Create Date: 2013-12-10 下午4:20:02
 */
public interface WeiBoDao {
    /**
     * 添加微博
     *
     * @param weiBo 微博实体
     */
    public void addWeiBo(WeiBo weiBo);

    /**
     * 验证某用户是否转发过该微博
     *
     * @param weiBo 微博实体
     */
    public WeiBo queryIsForwardWeiBo(WeiBo weiBo);

    /**
     * 删除微博
     *
     * @param weiBo 微博实体
     */
    public Long delWeiBo(WeiBo weiBo);

    /**
     * 查询我的微博
     *
     * @param weiBo 微博实体
     * @param page  分页参数
     * @return List<QueryWeiBo>我的微博list
     */
    public List<QueryWeiBo> queryMyWeiBo(WeiBo weiBo, PageEntity page);

    /**
     * 检查是否是我的微博
     *
     * @param cusId   当前用户id
     * @param weiboId 微博id
     * @return
     */
    public boolean clickMyWeiBo(Long cusId, Long weiboId);

    /**
     * 查询我关注的人的微博
     *
     * @param weiBo 微博实体
     * @param page  分页参数
     * @return List<QueryWeiBo> 我关注的微博list
     * @throws Exception
     */
    public List<QueryWeiBo> queryAttentionWeiBo(WeiBo weiBo, PageEntity page) throws Exception;

    /**
     * 根据微博id让该微博被赞数加一
     *
     * @param weiBo 微博实体
     */
    public void updateWeiBoPraiseNumAddOne(WeiBo weiBo);

    /**
     * 根据微博id让该微博被赞数减一
     *
     * @param weiBo 微博实体
     */
    public void updateWeiBoPraiseNumSubtractionOne(WeiBo weiBo);

    /**
     * 查询热门微博
     *
     * @param weiBo 微博实体
     * @param page  分页参数
     * @return List<QueryWeiBo> 热门微博list
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
     * 根据微博id让该微博评论数更新
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
     * 查询所有微博
     *
     * @param weiBo 微博实体
     * @param page  分页参数
     * @return
     */
    public List<QueryWeiBo> queryAllWeiBo(WeiBo weiBo, PageEntity page);

    /**
     * 设置该微博置顶
     *
     * @param weiBo 微博实体
     * @throws Exception
     */
    public Long updateWeiBoForTop(WeiBo weiBo) throws Exception;

    /**
     * 设置该微博置顶
     *
     * @param weiBo 微博实体
     * @throws Exception
     */
    public void updateQuXiaoWeiBoForTop(WeiBo weiBo) throws Exception;

    /**
     * 评论最多的微博
     *
     * @param weiBo 传入cusId 用户id
     * @param page  分页参数
     * @return List<QueryWeiBo> 查询的微博list
     * @throws Exception
     */
    public List<QueryWeiBo> queryCommentMostWeiBo(WeiBo weiBo, PageEntity page) throws Exception;

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
     * 查询微博根据用户id
     *
     * @param userId
     * @return
     */
    public List<WeiBo> queryPersonWeiBoById(Long userId, int status, int num);

    /**
     * lucene方法 查询传入微博id 查询微博
     *
     * @param wbIds 微博id的list
     * @return List<WeiBo>
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
     * 查出从beginRow（起始行数）开始的微博 一共 pageSize（需要查出多少条）条
     * 要在minWeiBoId（查询的where条件的最小微博id）和maxWeiBoId（查询的where条件的最大微博id）之间查
     *
     * @param beginRow   起始行数
     * @param pageSize   需要查出多少条
     * @param minWeiBoId 查询的where条件的最小微博id
     * @param maxWeiBoId 查询的where条件的最大微博id
     * @return List<WeiBo>
     * @throws Exception
     */
    public List<WeiBo> getQuestionByPageQuery(Long beginRow, Long pageSize, Long minWeiBoId, Long maxWeiBoId) throws Exception;


    /**
     * 根据微博id让该微博的微博转发数加一
     *
     * @param weiBo 微博实体
     */
    public void updateWeiBoForwardNumAddOne(Long weiBoId);

    /**
     * 根据微博id查询微博
     *
     * @param ids 微博id字符串
     * @return Map<String,Object>
     * @throws Exception
     */
    public Map<Long, QueryWeiBo> getWeiBoByIds(String ids) throws Exception;
}
