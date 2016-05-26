package io.wangxiao.edu.home.dao.impl.suggest;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.suggest.SugSuggestDao;
import io.wangxiao.edu.home.entity.suggest.QuerySugSuggest;
import io.wangxiao.edu.home.entity.suggest.SugSuggest;
import io.wangxiao.edu.home.entity.suggest.SugSuggestReply;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 建议Dao
 */

@Repository("sugSuggestDao")
public class SugSuggestDaoImpl extends GenericDaoImpl implements SugSuggestDao {
    /**
     * 添加SugSuggest
     *
     * @param sugSuggest 要添加的SugSuggest
     * @return id
     */
    public java.lang.Long addSugSuggest(SugSuggest sugSuggest) {
        return this.insert("SugSuggestMapper.createSugSuggest", sugSuggest);
    }

    /**
     * 根据id删除一个SugSuggest
     *
     * @param id 要删除的id
     */
    public Long deleteSugSuggestById(Long id) {
        return this.delete("SugSuggestMapper.deleteSugSuggestById", id);
    }

    @Override
    public Long deleteSugSuggestByCourseId(Long courseId) {
        return this.delete("SugSuggestMapper.deleteSugSuggestByCourseId", courseId);
    }

    /**
     * 修改SugSuggest
     *
     * @param sugSuggest 要修改的SugSuggest
     */
    public Long updateSugSuggest(SugSuggest sugSuggest) {
        return this.update("SugSuggestMapper.updateSugSuggest", sugSuggest);
    }

    /**
     * 更具建议的id修改SugSuggest的内容和标题
     *
     * @param sugSuggest 要修改的SugSuggest
     */
    public void updateSugSuggestBySugSuggestIdForContentAndTitle(SugSuggest sugSuggest) {
        this.update("SugSuggestMapper.updateSugSuggestBySugSuggestIdForContentAndTitle", sugSuggest);
    }

    /**
     * 根据id获取单个SugSuggest对象
     *
     * @param id 要查询的id
     * @return SugSuggest
     */
    public SugSuggest getSugSuggestById(Long id) {
        return this.selectOne("SugSuggestMapper.getSugSuggestById", id);
    }

    /**
     * 根据条件获取SugSuggest列表
     *
     * @param querySugSuggest 查询条件
     * @param page            分页参数
     * @return List<SugSuggest>
     */
    public List<SugSuggest> getSugSuggestList(QuerySugSuggest querySugSuggest, PageEntity page) {
        return this.queryForListPage("SugSuggestMapper.getSugSuggestList", querySugSuggest, page);// 根据条件获取SugSuggest列表
    }

    /**
     * 通过type查询建议
     *
     * @param status 传入建议类型
     * @return List<SugSuggest>
     */
    public List<SugSuggest> querySugSuggestListByStatus(int status, PageEntity page) {
        return this.queryForListPage("SugSuggestMapper.querySugSuggestListByStatus", status, page);// 通过type查询建议
    }

    /**
     * 通过cusId 和status状态 用户id查询我的建议
     *
     * @param sugSuggest 用户id
     * @param page       分页参数
     * @return
     */
    public List<SugSuggest> querySuggestByCusIdAndStatus(SugSuggest sugSuggest, PageEntity page) {
        return this.queryForListPage("SugSuggestMapper.querySuggestByCusIdAndStatus", sugSuggest, page);// 通过cusId
        // 和status状态
        // 用户id查询我的建议
    }

    /**
     * 我回答的建议
     *
     * @param sugSuggestReply 用户id
     * @param page            分页参数
     * @return List<SugSuggest>
     */
    public List<SugSuggest> querySuggestByReplyCusId(SugSuggestReply sugSuggestReply, PageEntity page) {
        return this.queryForListPage("SugSuggestMapper.querySuggestByReplyCusId", sugSuggestReply, page);// 我回答的建议
    }

    /**
     * 我被推荐的建议
     *
     * @param sugSuggestReply 当前用户id
     * @param page            分页参数
     * @return List<SugSuggest>
     */
    public List<SugSuggest> querySuggestByReplyCusIdAndIsBest(SugSuggestReply sugSuggestReply, PageEntity page) {
        return this.queryForListPage("SugSuggestMapper.querySuggestByReplyCusIdAndIsBest", sugSuggestReply, page);// 我被推荐的建议
    }

    /**
     * 推荐列表
     *
     * @param querySugSuggest
     * @param page            分页参数
     * @return List<SugSuggest>
     */
    public List<SugSuggest> querySuggestRecommend(QuerySugSuggest querySugSuggest, PageEntity page) {
        return this.queryForListPage("SugSuggestMapper.querySuggestRecommend", querySugSuggest, page);// 推荐列表
    }

    /**
     * 查询推荐的建议的智慧排行分页
     *
     * @param querySugSuggest 传入类型是意境还是务实
     * @param page            分页参数
     * @return List<SugSuggest>
     */
    public List<SugSuggest> queryRecommendSuggestOrderByWisdomNum(QuerySugSuggest querySugSuggest, PageEntity page) {
        return this.queryForListPage("SugSuggestMapper.queryRecommendSuggestOrderByWisdomNumPage", querySugSuggest,
                page);// 查询推荐的建议的智慧排行分页
    }

    /**
     * 最新问题排行
     *
     * @param size 传入显示的条数
     * @return List<SugSuggest>
     */
    public List<SugSuggest> querySuggestOrderById(int size) {
        return this.selectList("SugSuggestMapper.querySuggestOrderById", size);// 查询推荐的建议的智慧排行
    }

    /**
     * 查询推荐的建议的热心排行
     *
     * @param size 传入显示的条数
     * @return List<SugSuggest>
     */
    public List<SugSuggest> queryRecommendSuggestOrderByHotNum(int size) {
        return this.selectList("SugSuggestMapper.queryRecommendSuggestOrderByHotNum", size);// 查询推荐的建议的热心排行
    }

    /**
     * 给建议的智慧次数加一
     *
     * @param sugSuggest 传入建议id给该建议智慧次数加一
     */
    public Long updateSugSuggestBySuggestIdForWisdomScore(SugSuggest sugSuggest) {
        return this.update("SugSuggestMapper.updateSugSuggestBySuggestIdForWisdomScore", sugSuggest);
    }

    /**
     * 给建议的热心次数字段加一
     *
     * @param sugSuggest 传入要更新的建议的id
     */
    public Long updateSugSuggestBySuggestIdForHotScore(SugSuggest sugSuggest) {
        return this.update("SugSuggestMapper.updateSugSuggestBySuggestIdForHotScore", sugSuggest);// 给建议的热心次数字段加一
    }

    /**
     * 给建议的热度次数字段加一
     *
     * @param sugSuggest 传入要更新的建议的id
     */
    public Long updateSugSuggestBySuggestIdFoHeat(SugSuggest sugSuggest) {
        return this.update("SugSuggestMapper.updateSugSuggestBySuggestIdFoHeat", sugSuggest);// 给建议的热心次数字段加一
    }

    /**
     * 该建议id的回复数更新
     *
     * @param suggestId 建议id
     * @param count     更新的数量
     */
    public void updateSugSuggestBySuggestIdCount(Long suggestId, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("suggestId", suggestId);
        map.put("count", count);
        this.update("SugSuggestMapper.updateSugSuggestBySuggestIdCount", map);// 该建议id的回复数更新
    }

    /**
     * 根据建议id更新建议的回复次数减一
     *
     * @param sugSuggest 传入建议id
     * @return Integer
     */
    public Long updateSugSuggestBySuggestIdForReplycountSubtractOne(SugSuggest sugSuggest) {
        return this.update("SugSuggestMapper.updateSugSuggestBySuggestIdForReplycountSubtractOne", sugSuggest);// 根据建议id更新建议的浏览次数加一
    }

    /**
     * 更新建议的status 通过建议id
     *
     * @param sugSuggest 建议的id 和status状态
     */
    public Long updateSugSuggestBySuggestIdForStatus(SugSuggest sugSuggest) {
        return this.update("SugSuggestMapper.updateSugSuggestBySuggestIdForStatus", sugSuggest);// 更新建议的status
        // 通过建议id
    }

    /**
     * 根据建议id 置顶该建议
     *
     * @param sugSuggest 传入建议id 和top
     */
    public Long updateSugSuggestBySuggestIdForTop(SugSuggest sugSuggest) {
        return this.update("SugSuggestMapper.updateSugSuggestBySuggestIdForTop", sugSuggest);// 根据建议id
        // 置顶该建议
    }

    /**
     * 根据回复id 更新title content
     *
     * @param sugSuggest 传入回复id title 和content
     * @return Long
     */
    public Long updateSugSuggestBySuggestIdForContentAndTitle(SugSuggest sugSuggest) {
        return this.update("SugSuggestMapper.updateSugSuggestBySuggestIdForContentAndTitle", sugSuggest);// 根据回复id
        // 更新title
        // content
    }

    /**
     * 根据建议id更新建议的浏览次数
     *
     * @param sugSuggestId 建议id
     * @param count        浏览数
     * @return
     */
    public Long updateSugSuggestBySuggestIdForBrowseNumAddCount(Long sugSuggestId, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sugSuggestId", sugSuggestId);// 建议id
        map.put("count", count);// 更新数量
        return this.update("SugSuggestMapper.updateSugSuggestBySuggestIdForBrowseNumAddCount", map);// 根据建议id更新建议的浏览次数
    }

    /**
     * lucene方法 查询传入建议id 查询建议
     *
     * @param SugSuggestIds 建议id的list
     * @return List<SugSuggest>
     * @throws Exception
     */
    public List<SugSuggest> getLuceneSugSuggestByIds(List<Long> SugSuggestIds) throws Exception {
        return this.selectList("SugSuggestMapper.getLuceneSugSuggestByIds", SugSuggestIds);// lucene方法
        // 查询传入建议id
        // 查询建议
    }

    /**
     * 查出这个建议id之后的建议行数和最大建议id
     *
     * @param SugSuggestId 起始建议id
     * @return Map<String,Object> 返回两个参数一个是建议行数和最大建议id
     * @throws Exception
     */
    public Map<String, Object> getSugSuggestCountAfterId(Long SugSuggestId) throws Exception {
        List<QuerySugSuggest> querySugSuggest = this.selectList("SugSuggestMapper.getSugSuggestCountAfterId",
                SugSuggestId);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("lineNum", querySugSuggest.get(0).getLineNum());// 把得到的行数数据放入map中
        map.put("maxId", querySugSuggest.get(0).getMaxId());// 把得到的最大建议id数据放入map中
        return map;
    }

    /**
     * 查出从starSugSuggestId（起始建议id）开始的建议 一共 pageSize（需要查出多少条）条
     * 要在minSugSuggestId（查询的where条件的最小建议id
     * ）和maxSugSuggestId（查询的where条件的最大建议id）之间查
     *
     * @param beginRow        起始建议id
     * @param pageSize        需要查出多少条
     * @param minSugSuggestId 查询的where条件的最小建议id
     * @param maxSugSuggestId 查询的where条件的最大建议id
     * @return List<WeiBo>
     * @throws Exception
     */
    public List<SugSuggest> getSugSuggestByPageQuery(Long beginRow, Long pageSize, Long minSugSuggestId,
                                                     Long maxSugSuggestId) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("beginRow", beginRow);// 把起始行数 放入map中
        map.put("pageSize", pageSize);// 把需要查出多少条 放入map中
        map.put("minSugSuggestId", minSugSuggestId);// 把 查询的where条件的最小建议id
        // 放入map中
        map.put("maxSugSuggestId", maxSugSuggestId);// 把 查询的where条件的最大建议id
        // 放入map中
        return this.selectList("SugSuggestMapper.getSugSuggestByPageQuery", map);
    }

    @Override
    public void getSugSuggesByCommon(SugSuggest sugsuggest) {
        // TODO Auto-generated method stub
        this.update("SugSuggestMapper.updateSugSggestByCommon", sugsuggest);
    }

    @Override
    public void getSugSuggesByCommons(SugSuggest sugsuggest) {
        // TODO Auto-generated method stub
        this.update("SugSuggestMapper.updateSugSggestByCommons", sugsuggest);
    }

    /**
     * 查询全部问答
     */
    public List<SugSuggest> querySuggestAll(QuerySugSuggest querySugSuggest, PageEntity page) {
        return this.queryForListPage("SugSuggestMapper.querySuggestAll", querySugSuggest, page);
    }

    /**
     * 热门评论
     */
    public List<SugSuggest> querySuggestHtoComments(QuerySugSuggest querySugSuggest) {
        return this.selectList("SugSuggestMapper.querySuggestHtoComments", querySugSuggest);
    }

    /**
     * 用户提问量
     */
    public Long querySuggestQuestionNum(Long cusId) {
        return this.selectOne("SugSuggestMapper.querySuggestQuestionNum", cusId);
    }

    /**
     * 用户回复量
     */
    public Long querySuggestAnswerNum(Long cusId) {
        return this.selectOne("SugSuggestMapper.querySuggestAnswerNum", cusId);
    }

    /**
     * 赞一下
     */
    public void updateSugSuggestBySuggestIdForPraiseNumAddCount(Long suggestId, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sugSuggestId", suggestId);// 建议id
        map.put("count", count);// 更新数量
        this.update("SugSuggestMapper.updateSugSuggestBySuggestIdForPraiseNumAddCount", map);// 根据建议id更新建议的浏览次数
    }

}
