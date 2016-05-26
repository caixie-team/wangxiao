package io.wangxiao.edu.home.dao.suggest;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.suggest.SecondReply;
import io.wangxiao.edu.home.entity.suggest.SugSuggestReply;

import java.util.List;

public interface SugSuggestReplyDao {

    /**
     * 添加SugSuggestReply
     *
     * @param sugSuggestReply 要添加的SugSuggestReply
     * @return id
     */
    java.lang.Long addSugSuggestReply(SugSuggestReply sugSuggestReply);

    /**
     * 根据id删除一个SugSuggestReply
     *
     * @param id 要删除的id
     */
    Long deleteSugSuggestReplyById(Long id);

    /**
     * 根据id删除一个SugSuggestReply
     *
     * @param suggestId 建议id
     */
    Long deleteSugSuggestReplyBySuggestId(Long suggestId);

    /**
     * 修改SugSuggestReply
     *
     * @param sugSuggestReply 要修改的SugSuggestReply
     */
    Long updateSugSuggestReply(SugSuggestReply sugSuggestReply);

    /**
     * 根据id获取单个SugSuggestReply对象
     *
     * @param id 要查询的id
     * @return SugSuggestReply
     */
    SugSuggestReply getSugSuggestReplyById(Long id);

    /**
     * 根据条件获取SugSuggestReply列表
     *
     * @param sugSuggestReply 查询条件
     * @return List<SugSuggestReply>
     */
    List<SugSuggestReply> getSugSuggestReplyList(SugSuggestReply sugSuggestReply);

    /**
     * 通过建议id 查询该建议下的回复
     *
     * @param SuggestId 建议id
     * @return List<SugSuggestReply>
     */
    List<SugSuggestReply> querySugSuggestReplyListBySuggestId(SugSuggestReply sugSuggestReply, PageEntity page);

    /**
     * 通过建议id 查询该建议下全部的回复
     *
     * @param sugSuggestReply 建议id
     * @return List<SugSuggestReply>
     */
    List<SugSuggestReply> querySugSuggestReplyAllListBySuggestId(SugSuggestReply sugSuggestReply);

    /**
     * 通过回复id 更新isBest 推荐回复为最佳答案
     *
     * @param sugSuggestReply
     * @return Long
     */
    Long updateSugSuggestReplyBySugSuggestReplyIdForIsBest(SugSuggestReply sugSuggestReply);

    /**
     * 添加二级回复
     *
     * @param secondReply
     * @return
     */
    Long createSecondReply(SecondReply secondReply);

    /**
     * 查询二级回复
     *
     * @param secondReply
     * @param page
     * @return
     */
    List<SecondReply> getSecondReplyList(SecondReply secondReply, PageEntity page);
}