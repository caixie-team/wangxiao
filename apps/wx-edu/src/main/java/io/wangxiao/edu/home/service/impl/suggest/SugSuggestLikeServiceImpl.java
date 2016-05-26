package io.wangxiao.edu.home.service.impl.suggest;


import io.wangxiao.edu.home.dao.suggest.SugSuggestLikeDao;
import io.wangxiao.edu.home.entity.suggest.SugSuggestLike;
import io.wangxiao.edu.home.service.suggest.SugSuggestLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("sugSuggestLikeService")
public class SugSuggestLikeServiceImpl implements SugSuggestLikeService {

    @Autowired
    private SugSuggestLikeDao sugSuggestLikeDao;

    @Override
    public Long createSugSuggestLike(SugSuggestLike sugSuggestLike) {
        return sugSuggestLikeDao.createSugSuggestLike(sugSuggestLike);
    }

    @Override
    public void deleteSugSuggestLike(SugSuggestLike sugSuggestLike) {
        sugSuggestLikeDao.deleteSugSuggestLike(sugSuggestLike);
    }

    @Override
    public int getSugSuggestLikeCount(SugSuggestLike sugSuggestLike){
        return sugSuggestLikeDao.getSugSuggestLikeCount(sugSuggestLike);
    }
}