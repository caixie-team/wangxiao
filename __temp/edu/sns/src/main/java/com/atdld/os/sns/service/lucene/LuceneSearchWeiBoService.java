package com.atdld.os.sns.service.lucene;

import java.util.List;
import java.util.Map;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.weibo.WeiBo;

/**
 * @author
 * @ClassName LuceneSearchWeiBoService
 * @package com.atdld.open.exam.service.lucene
 * @description
 * @Create Date: 2013-11-30 下午5:03:12
 */
public interface LuceneSearchWeiBoService {

    /**
     * 根据关键字查询微博，返回map: 总行数 当前页微博的list
     *
     * @param keyword     关键字
     * @param currentPage 当前页
     * @param pagesize    一次获取的行数
     * @return
     * @throws Exception
     */
    public Map<String, Object> queryPageWeiBoByKeyWord(Map<String, String> searchMap, PageEntity page) throws Exception;

    /**
     * 初始化微博索引生成（定时调用）
     */
    public boolean indexInitForWeiBo() throws Exception;

    /**
     * 删除微博的索引(demo用)
     */
    public void indexDeleteForWeiBo(List<WeiBo> WeiBolist) throws Exception;

    /**
     * 更新微博的索引(demo用)
     */
    public void indexUpdateForWeiBo(List<WeiBo> WeiBolist) throws Exception;

    /**
     * 根据微博的id在索引中获得数据(demo用)
     */
    public String getLuceneWeiBoByWeiBoId(int weiBoId) throws Exception;

}
