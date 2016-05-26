package com.atdld.os.sns.service.lucene;

import java.util.Map;

import com.atdld.os.core.entity.PageEntity;

/**
 * @author :
 * @ClassName LuceneSearchWeiBoService.LuceneSearchBlogService
 * @description
 * @Create Date : 2014-1-17 上午9:54:45
 */
public interface LuceneSearchBlogService {

    /**
     * 根据关键字查询博文，返回map: 总行数 当前页博文的list
     *
     * @param searchMap   关键字 、用户、时间
     * @param currentPage 当前页
     * @param pagesize    一次获取的行数
     * @return
     * @throws Exception
     */
    public Map<String, Object> queryPageBlogByKeyWord(Map<String, String> searchMap, PageEntity page) throws Exception;

    /**
     * 初始化博文索引生成（定时调用）
     */
    public boolean indexInitForBlog() throws Exception;

}
