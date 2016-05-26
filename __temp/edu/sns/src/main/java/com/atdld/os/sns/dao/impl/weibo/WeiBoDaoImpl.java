package com.atdld.os.sns.dao.impl.weibo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.sns.constants.LuceneConstans;
import com.atdld.os.sns.dao.weibo.WeiBoDao;
import com.atdld.os.sns.entity.weibo.QueryWeiBo;
import com.atdld.os.sns.entity.weibo.WeiBo;

/**
 * @author
 * @ClassName WeiBoDaoImpl
 * @package com.atdld.open.sns.dao.impl.weibo
 * @description 微博Dao的实现
 * @Create Date: 2013-12-10 下午4:21:52
 */
@Repository("weiBoDao")
public class WeiBoDaoImpl extends GenericDaoImpl implements WeiBoDao {
    /**
     * 添加微博
     *
     * @param weiBo 微博实体
     */
    public void addWeiBo(WeiBo weiBo) {
        this.insert("WeiBoMapper.addWeiBo", weiBo);// 添加微博
    }

    /**
     * 验证某用户是否转发过该微博
     *
     * @param weiBo 微博实体
     */
    public WeiBo queryIsForwardWeiBo(WeiBo weiBo) {
        List<WeiBo> list = this.selectList("WeiBoMapper.queryIsForwardWeiBo", weiBo);
        if (ObjectUtils.isNotNull(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 删除微博
     *
     * @param weiBo 微博实体
     */
    public Long delWeiBo(WeiBo weiBo) {
        Long num = this.delete("WeiBoMapper.delWeiBo", weiBo);// 删除微博
        return num;
    }

    /**
     * 查询我的微博
     *
     * @param weiBo 微博实体
     * @param page  分页参数
     * @return List<QueryWeiBo>我的微博list
     */
    public List<QueryWeiBo> queryMyWeiBo(WeiBo weiBo, PageEntity page) {
        return this.queryForListPage("WeiBoMapper.queryMyWeiBo", weiBo, page);// 查询我的微博
    }

    /**
     * 检查是否是我的微博
     *
     * @param cusId   当前用户id
     * @param weiboId 微博id
     * @return
     */
    public boolean clickMyWeiBo(Long cusId, Long weiboId) {
        WeiBo weiBo = new WeiBo();
        weiBo.setCusId(cusId);
        weiBo.setId(weiboId);
        int num = this.selectOne("WeiBoMapper.clickMyWeiBo", weiBo);// 通过当前用户id
        // 和微博id检查有几条
        if (num == 0) {// 如果返回条数等于0 则返回false
            return false;
        } else {// 如果返回条数大于0 则返回true
            return true;
        }

    }

    /**
     * 查询我关注的人的微博
     *
     * @param weiBo 微博实体
     * @param page  分页参数
     * @return List<QueryWeiBo> 我关注的微博list
     * @throws Exception
     */
    public List<QueryWeiBo> queryAttentionWeiBo(WeiBo weiBo, PageEntity page) throws Exception {
        return this.queryForListPage("WeiBoMapper.queryAttentionWeiBo", weiBo, page);// 查询我关注的人的微博
    }

    /**
     * 查询我关注的人的微博
     *
     * @param weiBo 微博实体
     * @param page  分页参数
     * @return List<QueryWeiBo> 我关注的微博list
     * @throws Exception
     */
    public void updateWeiBoPraiseNumAddOne(WeiBo weiBo) {
        this.update("WeiBoMapper.updateWeiBoPraiseNumAddOne", weiBo);// 查询我关注的人的微博
    }

    /**
     * 根据微博id让该微博被赞数减一
     *
     * @param weiBo 微博实体
     */
    public void updateWeiBoPraiseNumSubtractionOne(WeiBo weiBo) {
        this.update("WeiBoMapper.updateWeiBoPraiseNumSubtractionOne", weiBo);// 根据微博id让该微博被赞数减一
    }

    /**
     * 查询热门微博
     *
     * @param weiBo 微博实体
     * @param page  分页参数
     * @return List<QueryWeiBo> 热门微博list
     * @throws Exception
     */
    public List<QueryWeiBo> queryHotWeiBo(WeiBo weiBo, PageEntity page) throws Exception {
        return this.queryForListPage("WeiBoMapper.queryHotWeiBo", weiBo, page);// 查询热门微博
    }

    /**
     * 查询一个星期发表微博最多的用户数
     *
     * @return List<QueryWeiBo>
     * @throws Exception
     */
    public List<QueryWeiBo> queryCustomerForWeiBoNumByWeek() throws Exception {
        return this.selectList("WeiBoMapper.queryCustomerForWeiBoNumByWeek", null);
    }

    /**
     * 根据微博id让该微博评论数
     *
     * @param weiboId 微博id
     * @param count   要更新的数量
     */
    public void updateWeiBoCommentNumAddCount(Long weiboId, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", weiboId);// 微博id
        map.put("count", count);// 要更新的数量
        this.update("WeiBoMapper.updateWeiBoCommentNumAddCount", map);// 根据微博id让该微博评论数加一
    }

    /**
     * 根据微博id让该微博评论数减一
     *
     * @param weiboId 微博id
     */
    public void updateWeiBoCommentNumSubtractCount(Long weiboId) {
        this.update("WeiBoMapper.updateWeiBoCommentNumSubtractCount", weiboId);// 根据微博id让该微博评论数加一
    }

    /**
     * 查询所有微博
     *
     * @param weiBo 微博实体
     * @param page  分页参数
     * @return
     */
    public List<QueryWeiBo> queryAllWeiBo(WeiBo weiBo, PageEntity page) {
        return this.queryForListPage("WeiBoMapper.queryAllWeiBo", weiBo, page);// 查询所有微博
    }

    /**
     * 设置该微博置顶
     *
     * @param weiBo 微博实体
     * @throws Exception
     */
    public Long updateWeiBoForTop(WeiBo weiBo) throws Exception {
        return this.update("WeiBoMapper.updateWeiBoForTop", weiBo);// 设置该微博置顶
    }

    /**
     * 设置该微博置顶
     *
     * @param weiBo 微博实体
     * @throws Exception
     */
    public void updateQuXiaoWeiBoForTop(WeiBo weiBo) throws Exception {
        this.update("WeiBoMapper.updateQuXiaoWeiBoForTop", weiBo);// 设置该微博置顶
    }

    /**
     * 评论最多的微博 根据评论数排序
     *
     * @param weiBo 传入cusId 用户id
     * @param page  分页参数
     * @return List<QueryWeiBo> 查询的微博list
     * @throws Exception
     */
    public List<QueryWeiBo> queryCommentMostWeiBo(WeiBo weiBo, PageEntity page) throws Exception {
        return this.queryForListPage("WeiBoMapper.queryCommentMostWeiBo", weiBo, page);// 评论最多的微博
        // 根据评论数排序
    }

    /**
     * 后台查询微博
     *
     * @param weiBo 微博
     * @param page  分页参数
     * @return List<QueryWeiBo>
     * @throws Exception
     */
    public List<QueryWeiBo> queryAdminAllWeiBo(QueryWeiBo queryWeiBo, PageEntity page) throws Exception {
        return this.queryForListPage("WeiBoMapper.queryAdminAllWeiBo", queryWeiBo, page);// 后台查询微博
    }

    /**
     * 通过微博id 查询微博
     *
     * @param weiBo 传入微博id
     * @return QueryWeiBo
     * @throws Exception
     */
    public QueryWeiBo queryWeiBoById(WeiBo weiBo) throws Exception {
        List<QueryWeiBo> QueryWeiBoList = this.selectList("WeiBoMapper.queryWeiBoById", weiBo);
        if (QueryWeiBoList != null && QueryWeiBoList.size() > 0) {
            return QueryWeiBoList.get(0);
        } else {
            return null;
        }
    }

    /**
     * lucene方法 查询传入微博id 查询微博
     *
     * @param wbIds 微博id的list
     * @return List<WeiBo>
     * @throws Exception
     */
    public List<QueryWeiBo> getLuceneWeiBoByIds(List<Long> weiboIds, Long cusId) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("weiboIds", weiboIds);// 微博id的list
        map.put("cusId", cusId);// 当前登陆用户
        return this.selectList("WeiBoMapper.getLuceneWeiBoByIds", map);// lucene方法
        // 查询传入微博id
        // 查询微博
    }

    /**
     * 查出这个微博id之后的微博行数和最大微博id
     *
     * @param weiBoId 起始微博id
     * @return Map<String,Object> 返回两个参数一个是微博行数和最大微博id
     * @throws Exception
     */
    public Map<String, Object> getWeiBoCountAfterId(Long weiBoId) throws Exception {
        List<QueryWeiBo> queryWeiBoList = this.selectList("WeiBoMapper.getWeiBoCountAfterId", weiBoId);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(LuceneConstans.LUCENE_LINE_NUM, queryWeiBoList.get(0).getWeiBoNum());// 把得到的数据放入map中
        map.put(LuceneConstans.LUCENE_MAX_ID, queryWeiBoList.get(0).getMaxWeiBoId());// 把得到的数据放入map中
        return map;
    }

    /**
     * 查出从beginRow（起始行数）开始的微博行数 一共 pageSize（需要查出多少条）条
     * 要在minWeiBoId（查询的where条件的最小微博id）和maxWeiBoId（查询的where条件的最大微博id）之间查
     *
     * @param beginRow   起始行数
     * @param pageSize   需要查出多少条
     * @param minWeiBoId 查询的where条件的最小微博id
     * @param maxWeiBoId 查询的where条件的最大微博id
     * @return List<WeiBo>
     * @throws Exception
     */
    public List<WeiBo> getQuestionByPageQuery(Long beginRow, Long pageSize, Long minWeiBoId, Long maxWeiBoId) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("beginRow", beginRow);// 把起始行数 放入map中
        map.put("pageSize", pageSize);// 把需要查出多少条 放入map中
        map.put("minWeiBoId", minWeiBoId);// 把 查询的where条件的最小微博id 放入map中
        map.put("maxWeiBoId", maxWeiBoId);// 把 查询的where条件的最大微博id 放入map中
        return this.selectList("WeiBoMapper.getQuestionByPageQuery", map);
    }

    /**
     * 查询微博根据用户id
     *
     * @param userId
     * @return
     */
    public List<WeiBo> queryPersonWeiBoById(Long userId, int status, int num) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cusId", userId);
        map.put("num", num);
        map.put("status", status);
        return this.selectList("WeiBoMapper.queryPersonWeiBoById", map);
    }

    /**
     * 根据微博id让该微博的微博转发数加一
     *
     * @param weiBo 微博实体
     */
    public void updateWeiBoForwardNumAddOne(Long weiBoId) {
        this.update("WeiBoMapper.updateWeiBoForwardNumAddOne", weiBoId);// 根据微博id让该微博的微博转发数加一
    }

    /**
     * 根据微博id查询微博
     *
     * @param ids 微博id字符串
     * @return Map<String,Object>
     * @throws Exception
     */
    public Map<Long, QueryWeiBo> getWeiBoByIds(String ids) throws Exception {
        Map<Long, QueryWeiBo> map = new HashMap<Long, QueryWeiBo>();
        //去除最后一个逗号
        ids = ids.substring(0, ids.length() - 1);
        //查询出的微博list
        List<QueryWeiBo> list = this.selectList("WeiBoMapper.getWeiBoByIds", ids);
        //放入map中
        if (ObjectUtils.isNotNull(list)) {
            for (QueryWeiBo queryWeiBo : list) {
                map.put(queryWeiBo.getId(), queryWeiBo);
            }
        }
        return map;
    }
}
