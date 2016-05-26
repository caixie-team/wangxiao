package io.wangxiao.edu.home.dao.suggest;


import io.wangxiao.edu.home.entity.suggest.SugSuggestLike;

public interface SugSuggestLikeDao {
    /**
     * 问答点赞
     * @param sugSuggestLike
     * @return
     */
    Long createSugSuggestLike(SugSuggestLike sugSuggestLike);

    /**
     * 问答取消点赞
     * @param sugSuggestLike
     * @return
     */
    void deleteSugSuggestLike(SugSuggestLike sugSuggestLike);

    /**
     * 获取点赞数量
     * @param sugSuggestLike
     * @return
     */
    int getSugSuggestLikeCount(SugSuggestLike sugSuggestLike);
}