package com.atdld.os.sns.dao.impl.lucene;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.sns.dao.lucene.SearchWordDao;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.lucene.SearchType;
import com.atdld.os.sns.entity.lucene.SearchWord;

/**
 * SearchWord User:  Date: 2014-02-20
 */
@Repository("searchWordDao")
public class SearchWordDaoImpl extends GenericDaoImpl implements SearchWordDao {

    public java.lang.Long addSearchWord(SearchWord searchWord) {
        return this.insert("SearchWordMapper.createSearchWord", searchWord);
    }

    public void deleteSearchWordById(Long id) {
        this.delete("SearchWordMapper.deleteSearchWordById", id);
    }

    public void updateSearchWord(SearchWord searchWord) {
        this.update("SearchWordMapper.updateSearchWord", searchWord);
    }

    /**
     * 修改SearchWord
     *
     * @param searchWord 要修改的SearchWord
     */
    public void updateSearchWordByTypeAndWord(SearchWord searchWord) {
        this.update("SearchWordMapper.updateSearchWordByTypeAndWord", searchWord);
    }

    /**
     * 根据id获取单个SearchWord对象
     *
     * @param id 要查询的id
     * @return SearchWord
     */
    public SearchWord getSearchWordById(Long id) {
        return this.selectOne("SearchWordMapper.getSearchWordById", id);
    }

    public SearchWord getSearchWordByTypeAndWord(SearchType type, String word) {
        SearchWord searchWord = new SearchWord();
        searchWord.setType(type.toString());
        searchWord.setWord(word);
        return this.selectOne("SearchWordMapper.getSearchWordByTypeAndWord", searchWord);
    }

    public List<SearchWord> getSearchWordList(SearchType type) {
        return this.selectList("SearchWordMapper.getSearchWordList", type.toString());
    }

    /**
     * 根据条件获取SearchWord列表
     *
     * @param searchWord 查询条件
     * @return List<SearchWord>
     */
    public List<SearchWord> getSearchWordListPage(SearchWord searchWord, PageEntity page) {
        return this.queryForListPage("SearchWordMapper.getSearchWordListPage", searchWord, page);
    }

}
