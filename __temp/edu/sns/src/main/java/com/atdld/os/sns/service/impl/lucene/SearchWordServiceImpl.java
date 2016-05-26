package com.atdld.os.sns.service.impl.lucene;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.sns.dao.lucene.SearchWordDao;
import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.lucene.SearchType;
import com.atdld.os.sns.entity.lucene.SearchWord;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.sns.service.lucene.SearchWordService;
import com.atdld.os.core.util.EnumUtil;
import com.atdld.os.core.util.ObjectUtils;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.impl.lucene.SearchWordServiceImpl
 * @description SearchWord管理接口
 * @Create Date : 2014-2-20 上午11:20:49
 */
@Service("searchWordService")
public class SearchWordServiceImpl implements SearchWordService {

    MemCache memCache = MemCache.getInstance();

    @Autowired
    private SearchWordDao searchWordDao;

    /**
     * 添加SearchWord
     *
     * @param searchWord 要添加的SearchWord
     * @return id
     */
    public java.lang.Long addSearchWord(SearchWord searchWord) {
        return searchWordDao.addSearchWord(searchWord);
    }

    /**
     * 根据id删除一个SearchWord
     *
     * @param id 要删除的id
     */
    public void deleteSearchWordById(Long id) {
        searchWordDao.deleteSearchWordById(id);
    }

    /**
     * 根据id获取单个SearchWord对象
     *
     * @param id 要查询的id
     * @return SearchWord
     */
    public SearchWord getSearchWordById(Long id) {
        return searchWordDao.getSearchWordById(id);
    }

    /**
     * 修改SearchWord
     *
     * @param searchWord 要修改的SearchWord
     */
    public void updateSearchWord(SearchWord searchWord) {
        SearchWord searchWord2 = searchWordDao.getSearchWordByTypeAndWord(EnumUtil.transStringToEnum(SearchType.class, searchWord.getType()), searchWord.getWord());
        if (ObjectUtils.isNull(searchWord2)) {
            searchWord2 = searchWord;
            searchWord2.setCount(1L);
            searchWordDao.addSearchWord(searchWord2);
        } else {
            searchWordDao.updateSearchWord(searchWord2);
        }
    }

    /**
     * 修改SearchWord
     *
     * @param searchWord 要修改的SearchWord
     */
    public void updateSearchWordByTypeAndWord(SearchWord searchWord) {
        SearchWord searchWord2 = searchWordDao.getSearchWordByTypeAndWord(EnumUtil.transStringToEnum(SearchType.class, searchWord.getType()), searchWord.getWord());
        if (ObjectUtils.isNull(searchWord2)) {
            searchWordDao.addSearchWord(searchWord);
        } else {
            searchWordDao.updateSearchWordByTypeAndWord(searchWord);
        }
    }

    /**
     * 根据id获取单个SearchWord对象
     *
     * @param id 要查询的id
     * @return SearchWord
     */
    public SearchWord getSearchWordByTypeAndWord(SearchType type, String word) {
        return searchWordDao.getSearchWordByTypeAndWord(type, word);
    }

    /**
     * 查询条件 获取i前10条作为页面显示用
     *
     * @param SearchType
     * @return List<SearchWord>
     */
    @SuppressWarnings("unchecked")
    public List<SearchWord> getSearchWordList(SearchType type) {
        List<SearchWord> list = (List<SearchWord>) memCache.get(MemConstans.MEM_SEARCH_WORD + type.toString());
        if (ObjectUtils.isNull(list)) {
            list = searchWordDao.getSearchWordList(type);
            if (ObjectUtils.isNotNull(list)) {
                memCache.set(MemConstans.MEM_SEARCH_WORD + type.toString(), list, 60 * 60);
            }
        }
        return list;
    }

    /**
     * 根据条件获取SearchWord列表
     *
     * @param searchWord 查询条件
     * @return List<SearchWord>
     */
    public List<SearchWord> getSearchWordListPage(SearchWord searchWord, PageEntity page) {
        return searchWordDao.getSearchWordListPage(searchWord, page);// 根据条件获取SearchWord列表
    }

}