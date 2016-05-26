package com.atdld.os.sns.dao.lucene;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.lucene.SearchType;
import com.atdld.os.sns.entity.lucene.SearchWord;

/**
 * SearchWord管理接口 User:  Date: 2014-02-20
 */
public interface SearchWordDao {

    /**
     * 添加SearchWord
     *
     * @param searchWord 要添加的SearchWord
     * @return id
     */
    public java.lang.Long addSearchWord(SearchWord searchWord);

    /**
     * 根据id删除一个SearchWord
     *
     * @param id 要删除的id
     */
    public void deleteSearchWordById(Long id);

    /**
     * 修改SearchWord
     *
     * @param searchWord 要修改的SearchWord
     */
    public void updateSearchWord(SearchWord searchWord);

    /**
     * 修改SearchWord
     *
     * @param searchWord 要修改的SearchWord
     */
    public void updateSearchWordByTypeAndWord(SearchWord searchWord);

    /**
     * 根据id获取单个SearchWord对象
     *
     * @param id 要查询的id
     * @return SearchWord
     */
    public SearchWord getSearchWordById(Long id);

    /**
     * 根据id获取单个SearchWord对象
     *
     * @param id 要查询的id
     * @return SearchWord
     */
    public SearchWord getSearchWordByTypeAndWord(SearchType type, String word);

    /**
     * 根据条件获取SearchWord列表
     *
     * @param searchWord 查询条件
     * @return List<SearchWord>
     */
    public List<SearchWord> getSearchWordList(SearchType type);

    /**
     * 根据条件获取SearchWord列表
     *
     * @param searchWord 查询条件
     * @return List<SearchWord>
     */
    public List<SearchWord> getSearchWordListPage(SearchWord searchWord, PageEntity page);

}