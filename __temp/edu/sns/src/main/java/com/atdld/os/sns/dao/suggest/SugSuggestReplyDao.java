package com.atdld.os.sns.dao.suggest;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.suggest.SugSuggestReply;

/**
 * SugSuggestReply管理接口 User:  Date: 2013-12-26
 */
public interface SugSuggestReplyDao {

    /**
     * 添加SugSuggestReply
     *
     * @param sugSuggestReply 要添加的SugSuggestReply
     * @return id
     */
    public java.lang.Long addSugSuggestReply(SugSuggestReply sugSuggestReply);

    /**
     * 根据id删除一个SugSuggestReply
     *
     * @param id 要删除的id
     */
    public Long deleteSugSuggestReplyById(Long id);

    /**
     * 根据id删除一个SugSuggestReply
     *
     * @param suggestId 建议id
     */
    public Long deleteSugSuggestReplyBySuggestId(Long suggestId);

    /**
     * 修改SugSuggestReply
     *
     * @param sugSuggestReply 要修改的SugSuggestReply
     */
    public void updateSugSuggestReply(SugSuggestReply sugSuggestReply);

    /**
     * 根据id获取单个SugSuggestReply对象
     *
     * @param id 要查询的id
     * @return SugSuggestReply
     */
    public SugSuggestReply getSugSuggestReplyById(Long id);

    /**
     * 根据条件获取SugSuggestReply列表
     *
     * @param sugSuggestReply 查询条件
     * @return List<SugSuggestReply>
     */
    public List<SugSuggestReply> getSugSuggestReplyList(SugSuggestReply sugSuggestReply);

    /**
     * 通过建议id 查询该建议下的回复
     *
     * @param SuggestId 建议id
     * @return List<SugSuggestReply>
     */
    public List<SugSuggestReply> querySugSuggestReplyListBySuggestId(SugSuggestReply sugSuggestReply, PageEntity page);

    /**
     * 通过建议id 查询该建议下全部的回复
     *
     * @param sugSuggestReply 建议id
     * @return List<SugSuggestReply>
     */
    public List<SugSuggestReply> querySugSuggestReplyAllListBySuggestId(SugSuggestReply sugSuggestReply);

    /**
     * 通过回复id 更新isBest 推荐回复为最佳答案
     *
     * @param sugSuggestReply
     * @return Long
     */
    public Long updateSugSuggestReplyBySugSuggestReplyIdForIsBest(SugSuggestReply sugSuggestReply);
}