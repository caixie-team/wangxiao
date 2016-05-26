package io.wangxiao.edu.home.service.suggest;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.suggest.QuerySugSuggest;
import io.wangxiao.edu.home.entity.suggest.SugSuggest;

import java.util.List;
import java.util.Map;

public interface SugSuggestService {

    /**
     * 添加SugSuggest
     *
     * @param sugSuggest 要添加的SugSuggest
     * @return id
     */
    String addSugSuggest(SugSuggest sugSuggest);

    /**
     * 根据id删除一个SugSuggest
     *
     * @param id 要删除的id
     * @throws Exception
     */
    String deleteSugSuggestById(Long id) throws Exception;

    /**
     * 修改SugSuggest
     *
     * @param sugSuggest 要修改的SugSuggest
     */
    void updateSugSuggest(SugSuggest sugSuggest);

    /**
     * 更具建议的id修改SugSuggest的内容和标题
     *
     * @param sugSuggest 要修改的SugSuggest
     */
    void updateSugSuggestBySugSuggestIdForContentAndTitle(SugSuggest sugSuggest);

    /**
     * 根据id获取单个SugSuggest对象
     *
     * @param id 要查询的id
     * @return SugSuggest
     */
    SugSuggest getSugSuggestById(Long id);

    /**
     * 根据条件获取SugSuggest列表
     *
     * @param sugSuggest 查询条件
     * @param page       分页参数
     * @return List<SugSuggest>
     */
    List<SugSuggest> getSugSuggestList(QuerySugSuggest querySugSuggest, PageEntity page) throws Exception;

    /**
     * 通过type查询建议
     *
     * @param type 传入建议类型
     * @param page 分页参数
     * @return
     */
    List<SugSuggest> querySugSuggestListByStatus(int status, PageEntity page) throws Exception;

    /**
     * 通过cusId 和status状态 用户id查询我的建议
     *
     * @param cusId  用户id
     * @param status 状态
     * @param page   分页参数
     * @return List<SugSuggest>
     */
    List<SugSuggest> querySuggestByCusIdAndStatus(Long cusId, int status, PageEntity page) throws Exception;

    /**
     * 我回答的建议
     *
     * @param cusId 当前用户id
     * @param page  分页参数
     * @return List<SugSuggest>
     */
    List<SugSuggest> querySuggestByReplyCusId(Long cusId, PageEntity page) throws Exception;

    /**
     * 我被推荐的建议
     *
     * @param cusId 当前用户id
     * @param page  分页参数
     * @return List<SugSuggest>
     */
    List<SugSuggest> querySuggestByReplyCusIdAndIsBest(Long cusId, PageEntity page) throws Exception;

    /**
     * 推荐列表
     *
     * @param querySugSuggest
     * @param page            分页参数
     * @return List<SugSuggest>
     */
    List<SugSuggest> querySuggestRecommend(QuerySugSuggest querySugSuggest, PageEntity page) throws Exception;

    /**
     * 查询推荐的建议的智慧排行分页
     *
     * @param querySugSuggest 传入类型是意境还是务实
     * @param page            分页参数
     * @return List<SugSuggest>
     */
    List<SugSuggest> queryRecommendSuggestOrderByWisdomNum(QuerySugSuggest querySugSuggest, PageEntity page) throws Exception;


    /**
     * 最新问题排行
     *
     * @param size 传入显示的条数
     * @return List<SugSuggest>
     */
    List<SugSuggest> querySuggestOrderById(int size);

    /**
     * 该建议id的回复数更新
     *
     * @param suggestId 建议id
     * @param count     更新的数量
     */
    void updateSugSuggestBySuggestIdCount(Long suggestId, int count);

    /**
     * 根据建议id 置顶该建议
     *
     * @param suggestId 建议id
     * @param top       置顶
     */
    String updateSugSuggestBySuggestIdForTop(Long suggestId, int top);

    /**
     * 根据回复id 更新title content
     *
     * @param sugSuggest 传入回复id title 和content
     * @return String
     */
    String updateSugSuggestBySuggestIdForContentAndTitle(SugSuggest sugSuggest);

    /**
     * 根据建议id更新建议的浏览次数
     *
     * @param sugSuggestId 建议id
     * @param count        浏览数
     * @return
     */
    String updateSuggestViewCount(Long sugSuggestId, int count);

    /**
     * lucene方法 查询传入建议id 查询建议
     *
     * @param SugSuggestIds 建议id的list
     * @return List<SugSuggest>
     * @throws Exception
     */
    List<SugSuggest> getLuceneSugSuggestByIds(List<Long> sugSuggestIds) throws Exception;

    /**
     * 查出这个建议id之后的建议行数和最大建议id
     *
     * @param SugSuggestId 起始建议id
     * @return Map<String,Object> 返回两个参数一个是建议行数和最大建议id
     * @throws Exception
     */
    Map<String, Object> getSugSuggestCountAfterId(Long sugSuggestId) throws Exception;

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
    List<SugSuggest> getSugSuggestByPageQuery(Long beginRow, Long pageSize, Long minSugSuggestId, Long maxSugSuggestId) throws Exception;


    /**
     * 传入id和common来就行修改
     *
     * @param sugsuggest
     */

    void getSugSuggesByCommon(SugSuggest sugsuggest);

    void getSugSuggesByCommons(SugSuggest sugsuggest);

    /**
     * 全部问答
     *
     * @param querySugSuggest
     * @param page            分页参数
     * @return List<SugSuggest>
     */
    List<SugSuggest> querySuggestAll(QuerySugSuggest querySugSuggest, PageEntity page) throws Exception;

    /**
     * 热门评论
     *
     * @param querySugSuggest
     * @return
     */
    List<SugSuggest> querySuggestHtoComments(QuerySugSuggest querySugSuggest);

    /**
     * 用户提问量
     *
     * @param cusId
     * @return
     */
    Long querySuggestQuestionNum(Long cusId);

    /**
     * 用户回答量
     */
    Long querySuggestAnswerNum(Long cusId);

    /**
     * 赞一下
     *
     * @param suggestId
     * @param count
     */
    void updateSugSuggestBySuggestIdForPraiseNumAddCount(Long suggestId, int count);
}