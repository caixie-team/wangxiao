package io.wangxiao.edu.home.service.suggest;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.suggest.SecondReply;
import io.wangxiao.edu.home.entity.suggest.SugSuggestReply;

import java.util.List;

public interface SugSuggestReplyService {

    /**
     * 添加SugSuggestReply
     *
     * @param sugSuggestReply 要添加的SugSuggestReply
     * @return id
     */
    String addSugSuggestReply(SugSuggestReply sugSuggestReply) throws Exception;

    /**
     * 根据id删除一个SugSuggestReply
     *
     * @param id    要删除的id
     * @param cusId 当前用户id
     */
    String deleteSugSuggestReplyById(Long id, Long cusId);

    /**
     * 根据id删除一个SugSuggestReply
     *
     * @param id 要删除的id
     */
    String deleteSugSuggestReplyById(Long id);

    /**
     * 修改SugSuggestReply
     *
     * @param sugSuggestReply 要修改的SugSuggestReply
     */
    void updateSugSuggestReply(SugSuggestReply sugSuggestReply);

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
     * @param isBest    是否是最佳答案
     * @return List<SugSuggestReply>
     */
    List<SugSuggestReply> querySugSuggestReplyListBySuggestId(Long SuggestId, int isBest, PageEntity page) throws Exception;

    /**
     * 通过建议id 查询该建议下全部的回复
     *
     * @param SuggestId 建议id
     * @return List<SugSuggestReply>
     */
    List<SugSuggestReply> querySugSuggestReplyAllListBySuggestId(Long SuggestId) throws Exception;

    /**
     * 通过回复id 更新isBest 推荐回复为最佳答案或取消最佳答案
     *
     * @param SugSuggestReplyId
     * @param isBest            是否为最佳答案的状态 建议的status状态和回复的isbest一样所以才能这么用
     * @param cusId             自己的id
     * @return int
     */
    Long updateSugSuggestReplyBySugSuggestReplyIdForIsBest(Long sugSuggestReplyId, Long sugSuggestId, int isBest, Long cusId) throws Exception;

    /**
     * 通过回复id 更新isBest 推荐回复为最佳答案或取消最佳答案
     *
     * @param SugSuggestReplyId
     * @param isBest            是否为最佳答案的状态 建议的status状态和回复的isbest一样所以才能这么用
     * @return int
     */
    Long updateSugSuggestReplyBySugSuggestReplyIdForIsBest(Long sugSuggestReplyId, Long sugSuggestId, int isBest);

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