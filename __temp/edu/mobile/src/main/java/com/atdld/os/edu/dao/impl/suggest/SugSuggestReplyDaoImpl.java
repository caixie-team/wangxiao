package com.atdld.os.edu.dao.impl.suggest;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.dao.suggest.SugSuggestReplyDao;
import com.atdld.os.edu.entity.suggest.SugSuggestReply;

/**
 * @author :
 * @ClassName com.atdld.os.sns.dao.impl.suggest.SugSuggestReplyDaoImpl
 * @description
 * @Create Date : 2013-12-26 下午1:25:43
 */
@Repository("sugSuggestReplyDao")
public class SugSuggestReplyDaoImpl extends GenericDaoImpl implements SugSuggestReplyDao {
    /**
     * 添加SugSuggestReply
     *
     * @param sugSuggestReply 要添加的SugSuggestReply
     * @return id
     */
    public java.lang.Long addSugSuggestReply(SugSuggestReply sugSuggestReply) {
        return this.insert("SugSuggestReplyMapper.createSugSuggestReply", sugSuggestReply);
    }

    /**
     * 根据id删除一个SugSuggestReply
     *
     * @param id 要删除的id
     */
    public Long deleteSugSuggestReplyById(Long id) {
        return this.delete("SugSuggestReplyMapper.deleteSugSuggestReplyById", id);
    }

    /**
     * 根据建议id删除一个SugSuggestReply
     *
     * @param suggestId 建议id
     */
    public Long deleteSugSuggestReplyBySuggestId(Long suggestId) {
        return this.delete("SugSuggestReplyMapper.deleteSugSuggestReplyBySuggestId", suggestId);// 根据建议id删除一个SugSuggestReply
    }

    /**
     * 修改SugSuggestReply
     *
     * @param sugSuggestReply 要修改的SugSuggestReply
     */
    public void updateSugSuggestReply(SugSuggestReply sugSuggestReply) {
        this.update("SugSuggestReplyMapper.updateSugSuggestReply", sugSuggestReply);
    }

    /**
     * 根据id获取单个SugSuggestReply对象
     *
     * @param id 要查询的id
     * @return SugSuggestReply
     */
    public SugSuggestReply getSugSuggestReplyById(Long id) {
        return this.selectOne("SugSuggestReplyMapper.getSugSuggestReplyById", id);
    }

    /**
     * 根据条件获取SugSuggestReply列表
     *
     * @param sugSuggestReply 查询条件
     * @return List<SugSuggestReply>
     */
    public List<SugSuggestReply> getSugSuggestReplyList(SugSuggestReply sugSuggestReply) {
        return this.selectList("SugSuggestReplyMapper.getSugSuggestReplyList", sugSuggestReply);
    }

    /**
     * 通过建议id 查询该建议下的回复
     *
     * @param SuggestId 建议id
     * @return List<SugSuggestReply>
     */
    public List<SugSuggestReply> querySugSuggestReplyListBySuggestId(SugSuggestReply sugSuggestReply, PageEntity page) {
        return this.queryForListPage("SugSuggestReplyMapper.querySugSuggestReplyListBySuggestId", sugSuggestReply, page);// 通过建议id
        // 查询该建议下的回复
    }

    /**
     * 通过建议id 查询该建议下全部的回复
     *
     * @param sugSuggestReply 建议id
     * @return List<SugSuggestReply>
     */
    public List<SugSuggestReply> querySugSuggestReplyAllListBySuggestId(SugSuggestReply sugSuggestReply) {
        return this.selectList("SugSuggestReplyMapper.querySugSuggestReplyAllListBySuggestId", sugSuggestReply);
    }

    /**
     * 通过回复id 更新isBest 推荐回复为最佳答案
     *
     * @param sugSuggestReply 传入回复id 和 isbest最佳答案
     * @return Long
     */
    public Long updateSugSuggestReplyBySugSuggestReplyIdForIsBest(SugSuggestReply sugSuggestReply) {
        return this.update("SugSuggestReplyMapper.updateSugSuggestReplyBySugSuggestReplyIdForIsBest", sugSuggestReply);// 通过回复id
        // 更新isBest
        // 推荐回复为最佳答案
    }
}
