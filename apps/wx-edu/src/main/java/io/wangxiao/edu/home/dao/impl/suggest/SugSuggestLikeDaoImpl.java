package io.wangxiao.edu.home.dao.impl.suggest;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.suggest.SugSuggestLikeDao;
import io.wangxiao.edu.home.entity.suggest.SugSuggestLike;
import org.springframework.stereotype.Repository;

@Repository("sugSuggestLikeDao")
public class SugSuggestLikeDaoImpl extends GenericDaoImpl implements SugSuggestLikeDao {

    @Override
    public Long createSugSuggestLike(SugSuggestLike sugSuggestLike) {
        return this.insert("SugSuggestLikeMapper.createSugSuggestLike", sugSuggestLike);
    }

    @Override
    public void deleteSugSuggestLike(SugSuggestLike sugSuggestLike) {
        this.delete("SugSuggestLikeMapper.deleteSugSuggestLike", sugSuggestLike);
    }

    @Override
    public int getSugSuggestLikeCount(SugSuggestLike sugSuggestLike) {
        return this.selectOne("SugSuggestLikeMapper.getSugSuggestLikeCount", sugSuggestLike);
    }
}
