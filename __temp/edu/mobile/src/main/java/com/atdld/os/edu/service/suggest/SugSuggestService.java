package com.atdld.os.edu.service.suggest;

import java.util.List;
import java.util.Map;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.suggest.QuerySugSuggest;
import com.atdld.os.edu.entity.suggest.SugSuggest;

/**
 * SugSuggest管理接口 User:  Date: 2013-12-26
 */
public interface SugSuggestService {

    /**
     * 添加SugSuggest
     *
     * @param sugSuggest 要添加的SugSuggest
     * @return id
     */
    public String addSugSuggest(SugSuggest sugSuggest);

    /**
     * 根据id删除一个SugSuggest
     *
     * @param id 要删除的id
     * @throws Exception
     */
    public String deleteSugSuggestById(Long id) throws Exception;

    /**
     * 修改SugSuggest
     *
     * @param sugSuggest 要修改的SugSuggest
     */
    public void updateSugSuggest(SugSuggest sugSuggest);

    /**
     * 更具建议的id修改SugSuggest的内容和标题
     *
     * @param sugSuggest 要修改的SugSuggest
     */
    public void updateSugSuggestBySugSuggestIdForContentAndTitle(SugSuggest sugSuggest);

    /**
     * 根据id获取单个SugSuggest对象
     *
     * @param id 要查询的id
     * @return SugSuggest
     */
    public SugSuggest getSugSuggestById(Long id);

    /**
     * 根据条件获取SugSuggest列表
     *
     * @param sugSuggest 查询条件
     * @param page       分页参数
     * @return List<SugSuggest>
     */
    public List<SugSuggest> getSugSuggestList(QuerySugSuggest querySugSuggest, PageEntity page) throws Exception;

    /**
     * 通过type查询建议
     *
     * @param type 传入建议类型
     * @param page 分页参数
     * @return
     */
    public List<SugSuggest> querySugSuggestListByStatus(int status, PageEntity page) throws Exception;

    /**
     * 通过cusId 和status状态 用户id查询我的建议
     *
     * @param cusId  用户id
     * @param status 状态
     * @param page   分页参数
     * @return List<SugSuggest>
     */
    public List<SugSuggest> querySuggestByCusIdAndStatus(Long cusId, int status, PageEntity page) throws Exception;

    /**
     * 我回答的建议
     *
     * @param cusId 当前用户id
     * @param page  分页参数
     * @return List<SugSuggest>
     */
    public List<SugSuggest> querySuggestByReplyCusId(Long cusId, PageEntity page) throws Exception;

    /**
     * 我被推荐的建议
     *
     * @param cusId 当前用户id
     * @param page  分页参数
     * @return List<SugSuggest>
     */
    public List<SugSuggest> querySuggestByReplyCusIdAndIsBest(Long cusId, PageEntity page) throws Exception;

    /**
     * 推荐列表
     *
     * @param querySugSuggest
     * @param page            分页参数
     * @return List<SugSuggest>
     */
    public List<SugSuggest> querySuggestRecommend(QuerySugSuggest querySugSuggest, PageEntity page) throws Exception;

    /**
     * 查询推荐的建议的智慧排行分页
     *
     * @param querySugSuggest 传入类型是意境还是务实
     * @param page            分页参数
     * @return List<SugSuggest>
     */
    public List<SugSuggest> queryRecommendSuggestOrderByWisdomNum(QuerySugSuggest querySugSuggest, PageEntity page) throws Exception;


    /**
     * 最新问题排行
     *
     * @param size 传入显示的条数
     * @return List<SugSuggest>
     */
    public List<SugSuggest> querySuggestOrderById(int size);

    /**
     * 该建议id的回复数更新
     *
     * @param suggestId 建议id
     * @param count     更新的数量
     */
    public void updateSugSuggestBySuggestIdCount(Long suggestId, int count);

    /**
     * 根据建议id 置顶该建议
     *
     * @param suggestId 建议id
     * @param top       置顶
     */
    public String updateSugSuggestBySuggestIdForTop(Long suggestId, int top);

    /**
     * 根据回复id 更新title content
     *
     * @param sugSuggest 传入回复id title 和content
     * @return String
     */
    public String updateSugSuggestBySuggestIdForContentAndTitle(SugSuggest sugSuggest);

    /**
     * 根据建议id更新建议的浏览次数
     *
     * @param sugSuggestId 建议id
     * @param count        浏览数
     * @return
     */
    public String updateSuggestViewCount(Long sugSuggestId, int count);

    /**
     * lucene方法 查询传入建议id 查询建议
     *
     * @param SugSuggestIds 建议id的list
     * @return List<SugSuggest>
     * @throws Exception
     */
    public List<SugSuggest> getLuceneSugSuggestByIds(List<Long> sugSuggestIds) throws Exception;

    /**
     * 查出这个建议id之后的建议行数和最大建议id
     *
     * @param SugSuggestId 起始建议id
     * @return Map<String,Object> 返回两个参数一个是建议行数和最大建议id
     * @throws Exception
     */
    public Map<String, Object> getSugSuggestCountAfterId(Long sugSuggestId) throws Exception;

    /**
     * 查出从starSugSuggestId（起始建议行数）开始的建议 一共 pageSize（需要查出多少条）条
     * 要在minSugSuggestId（查询的where条件的最小建议id
     * ）和maxSugSuggestId（查询的where条件的最大建议id）之间查
     *
     * @param beginRow   起始建议行数
     * @param pageSize   需要查出多少条
     * @param minWeiBoId 查询的where条件的最小建议id
     * @param maxWeiBoId 查询的where条件的最大建议id
     * @return List<WeiBo>
     * @throws Exception
     */
    public List<SugSuggest> getSugSuggestByPageQuery(Long beginRow, Long pageSize, Long minSugSuggestId, Long maxSugSuggestId) throws Exception;
}