package io.wangxiao.edu.sysuser.dao.impl;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.sysuser.dao.KeywordDao;
import io.wangxiao.edu.sysuser.entity.Keyword;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("keywordDao")
public class KeywordDaoImpl extends GenericDaoImpl implements KeywordDao {

    public java.lang.Long addKeyword(Keyword keyword) {
        return this.insert("KeywordMapper.createKeyword", keyword);
    }

    public void deleteKeywordById(Long id) {
        this.delete("KeywordMapper.deleteKeywordById", id);
    }

    public void updateKeyword(Keyword keyword) {
        this.update("KeywordMapper.updateKeyword", keyword);
    }

    public Keyword getKeywordById(Long id) {
        return this.selectOne("KeywordMapper.getKeywordById", id);
    }

    public List<Keyword> getKeywordList(Keyword keyword) {
        return this.selectList("KeywordMapper.getKeywordList", keyword);
    }

    /**
     * @param keyword
     * @param page
     * @return
     */
    public List<Keyword> getKeywordListPage(Keyword keyword, PageEntity page) {
        return this.queryForListPage("KeywordMapper.getKeywordListPage", keyword, page);
    }
}
