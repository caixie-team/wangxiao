package com.atdld.os.edu.service.impl.suggest;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.common.service.SnsHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.edu.constants.enums.CourseProfileType;
import com.atdld.os.edu.constants.web.DynamicConstans;
import com.atdld.os.edu.constants.web.SnsConstants;
import com.atdld.os.edu.constants.web.SuggestConstans;
import com.atdld.os.edu.dao.suggest.SugSuggestDao;
import com.atdld.os.edu.dao.suggest.SugSuggestReplyDao;
import com.atdld.os.edu.entity.course.CourseProfile;
import com.atdld.os.edu.entity.suggest.QuerySugSuggest;
import com.atdld.os.edu.entity.suggest.SugSuggest;
import com.atdld.os.edu.entity.suggest.SugSuggestReply;
import com.atdld.os.edu.entity.user.UserExpandDto;
import com.atdld.os.edu.service.course.CourseProfileService;
import com.atdld.os.edu.service.suggest.SugSuggestService;
import com.atdld.os.edu.service.user.UserExpandService;
import com.atdld.os.sysuser.service.KeywordService;

/**
 * SugSuggest管理接口 User:  Date: 2013-12-26
 */
@Service("sugSuggestService")
public class SugSuggestServiceImpl implements SugSuggestService {

    @Autowired
    private SugSuggestDao sugSuggestDao;// 建议Dao
    @Autowired
    private SugSuggestReplyDao sugSuggestReplyDao;// 建议回复 的Dao
    @Autowired
    private UserExpandService userExpandService;
    @Autowired
    private SnsHessianService snsHessianService;
    @Autowired
    private CourseProfileService courseProfileService;
    private MemCache memCache = MemCache.getInstance();// 缓存

    @Autowired
    private KeywordService keywordService;

    /**
     * 添加SugSuggest
     *
     * @param sugSuggest 要添加的SugSuggest
     * @return
     */
    public String addSugSuggest(SugSuggest sugSuggest) {
        sugSuggest.setAddtime(new Date());
        sugSuggest.setContent(keywordService.doFilter(sugSuggest.getContent()));
        if (userExpandService.checkLimitOpt(MemConstans.SUGGEST_LIMIT, sugSuggest.getCusId())) {
            UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(sugSuggest.getCusId());
            
            sugSuggest.setShowname(userExpandDto.getShowname());// set 会员名
            sugSuggest.setContent(keywordService.doFilter(sugSuggest.getContent()));
            String content = sugSuggest.getContent();
            //添加摘要去除内容的html截取300
            if (StringUtils.isNotEmpty(content)) {
                String noHtml = content.replaceAll("\\s\\w+=\\\"[^\"]+\\\"", "");
                noHtml = noHtml.replaceAll("</?[^>]+>", "");// 剔出了<html>的标签
                noHtml = noHtml.replace("&nbsp;", "");
                noHtml = noHtml.replaceAll("\r|\n|\t", "");
                // 字符长度过长则截取
                if (noHtml.length() > 200) {
                    noHtml = noHtml.substring(0, 200);
                }
                sugSuggest.setSummary(noHtml);
            }
            sugSuggestDao.addSugSuggest(sugSuggest);
          //更新课程的笔记数量
            courseProfileService.updateCourseProfile(CourseProfileType.questiongcount.toString(), sugSuggest.getCourseId(), 1L,CourseProfile.ADD);
            userExpandService.customerOptLimitCountAdd(MemConstans.SUGGEST_LIMIT, sugSuggest.getCusId());// 给该用户添加一次添加建议的次数
            try {
                Map<String, String> map = new HashMap<String, String>();
    	        map.put("userId",sugSuggest.getCusId() + "");//用户id
    	        map.put("bizId", sugSuggest.getId() + "");// 学员活动（事件）id 商品id 试卷id
    	        map.put("type", 8 + "");//11观看课程 12购买了商品 13 考试
    	        map.put("desc", "发布了问题");// 动态描述
    	        map.put("title", sugSuggest.getTitle());// 辅助title
    	        map.put("assistId",0L+"");// 辅助id
    			content=WebUtils.replaceTagHTML(sugSuggest.getContent());
    			 if (StringUtils.isNotEmpty(content)) {// 回复的内容
    				 content=StringUtils.getLength(content,DynamicConstans.DYNAMICWEB_CONTENT_LENGTH);
    				 map.put("content", content);
    			 }else{
    				 map.put("content", "");
    			 }
    			 map.put("url", "");//操作url
    		     //snsHessianService.addDynamic(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return SnsConstants.SUCCESS;// 添加成功
        } else {
            return SnsConstants.ADDMOST;// 添加频率过快请重试
        }
    }

    /**
     * 根据id删除一个SugSuggest
     *
     * @param id 要删除的id
     * @throws Exception
     */
    public String deleteSugSuggestById(Long id) throws Exception {
    	//查询问答
    	SugSuggest sugSuggest = sugSuggestDao.getSugSuggestById(id);
        Long falgNum = sugSuggestDao.deleteSugSuggestById(id);// 删除建议
        if (falgNum > 0) {
            sugSuggestReplyDao.deleteSugSuggestReplyBySuggestId(id);// 删除该建议下
           // 删除建议动态
            //snsHessianService.delDynamic(id,DynamicConstans.DYNAMICWEB_TYPE_SUGSUGGEST);
            
			//如果是课程问答 课程问答数减一
            if(sugSuggest.getCourseId()!=null&&sugSuggest.getCourseId()!=0){
            	 courseProfileService.updateCourseProfile(CourseProfileType.questiongcount.toString(), sugSuggest.getCourseId(), 1L,CourseProfile.SUBTRACTION);
            }
            return SnsConstants.SUCCESS;// 删除成功
        } else {
            return SnsConstants.FALSE;// 删除失败
        }

    }

    /**
     * 修改SugSuggest
     *
     * @param sugSuggest 要修改的SugSuggest
     */
    public void updateSugSuggest(SugSuggest sugSuggest) {
        sugSuggestDao.updateSugSuggest(sugSuggest);
    }

    /**
     * 更具建议的id修改SugSuggest的内容和标题
     *
     * @param sugSuggest 要修改的SugSuggest
     */
    public void updateSugSuggestBySugSuggestIdForContentAndTitle(SugSuggest sugSuggest) {
    	sugSuggest.setContent(keywordService.doFilter(sugSuggest.getContent()));
        String summary = WebUtils.replaceTagHTML(sugSuggest.getContent());
        if(StringUtils.isNotEmpty(summary)){
            summary=StringUtils.getLength(summary, SuggestConstans.SUGSUGGEST_SUMMARY_LENGTH);
            sugSuggest.setSummary(summary);
        }else{
            sugSuggest.setSummary("");
        }
        sugSuggestDao.updateSugSuggestBySugSuggestIdForContentAndTitle(sugSuggest);
    }

    /**
     * 根据id获取单个SugSuggest对象
     *
     * @param id 要查询的id
     * @return SugSuggest
     */
    public SugSuggest getSugSuggestById(Long id) {
        SugSuggest sugSuggest = sugSuggestDao.getSugSuggestById(id);
        if(ObjectUtils.isNull(sugSuggest)){
	        return null;
		}
        //通过cusid 查询showname
        UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(sugSuggest.getCusId());
        //查询的showname放入sugsuggest中
        sugSuggest.setShowname(userExpandDto.getShowname());
        return sugSuggest;
    }

    /**
     * 根据条件获取SugSuggest列表
     *
     * @param querySugSuggest 查询条件
     * @param page       分页参数
     * @return List<SugSuggest>
     * @throws Exception
     */
    public List<SugSuggest> getSugSuggestList(QuerySugSuggest querySugSuggest, PageEntity page) throws Exception {
        List<SugSuggest> sugSuggestList = sugSuggestDao.getSugSuggestList(querySugSuggest, page);
        //循环博客查询showname
        if (ObjectUtils.isNotNull(sugSuggestList)) {
            List<Long> cusIdlist = new ArrayList<Long>();
            for (SugSuggest sug : sugSuggestList) {
                cusIdlist.add(sug.getCusId());
            }
            //通过用户id查询出customer的map
            Map<String, UserExpandDto> map = userExpandService.queryCustomerInCusIds(cusIdlist);
            if(ObjectUtils.isNotNull(map)){
                for (SugSuggest sug : sugSuggestList) {
                    UserExpandDto customer=map.get(sug.getCusId().toString());
                    if(ObjectUtils.isNotNull(customer)){
                        sug.setShowname(customer.getShowname());
                        sug.setQueryCustomer(customer);
                    }
                }
            }
            
        }
        return sugSuggestList;
    }

    /**
     * 查询我推荐的建议
     *
     * @param status 用户id
     * @param page  分页参数
     * @return
     * @throws Exception
     */
    public List<SugSuggest> querySugSuggestListByStatus(int status, PageEntity page) throws Exception {
        SugSuggest sugSuggest = new SugSuggest();
        sugSuggest.setStatus(status);
        List<SugSuggest> sugSuggestList = sugSuggestDao.querySugSuggestListByStatus(sugSuggest, page);// 通过type查询建议
        List<Long> cusIdlist = new ArrayList<Long>();
        //循环博客查询showname
        if (ObjectUtils.isNotNull(sugSuggestList)) {
            for (SugSuggest sug : sugSuggestList) {
                cusIdlist.add(sug.getCusId());
            }
            //通过用户id查询出customer的map
            Map<String, UserExpandDto> map = userExpandService.queryCustomerInCusIds(cusIdlist);
            if(ObjectUtils.isNotNull(map)){
                for (SugSuggest sug : sugSuggestList) {
                    UserExpandDto customer = map.get(sug.getCusId().toString());
                    if(ObjectUtils.isNotNull(customer)){
                        sug.setQueryCustomer(customer);
                        sug.setShowname(customer.getShowname());
                    }
                }
            }
        }
        return sugSuggestList;
    }

    /**
     * 通过cusId 和status状态 用户id查询我的建议
     *
     * @param cusId  用户id
     * @param status 状态
     * @param page   分页参数
     * @return List<SugSuggest>
     * @throws Exception
     */
    public List<SugSuggest> querySuggestByCusIdAndStatus(Long cusId, int status, PageEntity page) throws Exception {
        SugSuggest sugSuggest = new SugSuggest();
        sugSuggest.setCusId(cusId);// 用户id
        sugSuggest.setStatus(status);// 状态
        List<SugSuggest> sugSuggestList = sugSuggestDao.querySuggestByCusIdAndStatus(sugSuggest, page);// 查询我推荐的建议
        List<Long> cusIdlist = new ArrayList<Long>();
        //循环博客查询showname
        if (ObjectUtils.isNotNull(sugSuggestList)) {
            for (SugSuggest sug : sugSuggestList) {
                cusIdlist.add(sug.getCusId());
            }
            //通过用户id查询出customer的map
            Map<String, UserExpandDto> map = userExpandService.queryCustomerInCusIds(cusIdlist);
            if(ObjectUtils.isNotNull(map)){
                for (SugSuggest sug : sugSuggestList) {
                    UserExpandDto customer = map.get(sug.getCusId().toString());
                    if(ObjectUtils.isNotNull(customer)){
                        sug.setQueryCustomer(customer);
                        sug.setShowname(customer.getShowname());
                    }
                }
            }
        }
        return sugSuggestList;
    }

    /**
     * 我回答的建议
     *
     * @param cusId 当前用户id
     * @param page  分页参数
     * @return List<SugSuggest>
     * @throws Exception
     */
    public List<SugSuggest> querySuggestByReplyCusId(Long cusId, PageEntity page) throws Exception {
        SugSuggestReply sugSuggestReply = new SugSuggestReply();
        sugSuggestReply.setCusId(cusId);// 当前用户id
        List<SugSuggest> sugSuggestList = sugSuggestDao.querySuggestByReplyCusId(sugSuggestReply, page);// 我回答的建议
        List<Long> cusIdlist = new ArrayList<Long>();
        //循环博客查询showname
        if (ObjectUtils.isNotNull(sugSuggestList)) {
            for (SugSuggest sug : sugSuggestList) {
                cusIdlist.add(sug.getCusId());
            }
            //通过用户id查询出customer的map
            Map<String, UserExpandDto> map = userExpandService.queryCustomerInCusIds(cusIdlist);
            if(ObjectUtils.isNotNull(map)){
                for (SugSuggest sug : sugSuggestList) {
                    UserExpandDto customer = map.get(sug.getCusId().toString());
                    if(ObjectUtils.isNotNull(customer)){
                        sug.setQueryCustomer(customer);
                        sug.setShowname(customer.getShowname());
                    }
                }
            }
        }
        return sugSuggestList;
    }

    /**
     * 我被推荐的建议
     *
     * @param cusId 当前用户id
     * @param page  分页参数
     * @return List<SugSuggest>
     * @throws Exception
     */
    public List<SugSuggest> querySuggestByReplyCusIdAndIsBest(Long cusId, PageEntity page) throws Exception {
        SugSuggestReply sugSuggestReply = new SugSuggestReply();
        sugSuggestReply.setCusId(cusId);// 当前用户id
        List<SugSuggest> sugSuggestList = sugSuggestDao.querySuggestByReplyCusIdAndIsBest(sugSuggestReply, page);// 我被推荐的建议
        List<Long> cusIdlist = new ArrayList<Long>();
        //循环博客查询showname
        if (ObjectUtils.isNotNull(sugSuggestList)) {
            for (SugSuggest sug : sugSuggestList) {
                cusIdlist.add(sug.getCusId());
            }
            //通过用户id查询出customer的map
            Map<String, UserExpandDto> map = userExpandService.queryCustomerInCusIds(cusIdlist);
            if(ObjectUtils.isNotNull(map)){
                for (SugSuggest sug : sugSuggestList) {
                    UserExpandDto customer = map.get(sug.getCusId().toString());
                    if(ObjectUtils.isNotNull(customer)){
                        sug.setQueryCustomer(customer);
                        sug.setShowname(customer.getShowname());
                    }
                }
            }
        }
        return sugSuggestList;
    }

    /**
     * 推荐列表
     *
     * @param querySugSuggest
     * @param page            分页参数
     * @return List<SugSuggest>
     * @throws Exception
     */
    public List<SugSuggest> querySuggestRecommend(QuerySugSuggest querySugSuggest, PageEntity page) throws Exception {
        List<SugSuggest> sugSuggestList = sugSuggestDao.querySuggestRecommend(querySugSuggest, page);// 推荐列表
        List<Long> cusIdlist = new ArrayList<Long>();
        //循环博客查询showname
        if (ObjectUtils.isNotNull(sugSuggestList)) {
            for (SugSuggest sug : sugSuggestList) {
                cusIdlist.add(sug.getCusId());
            }
            //通过用户id查询出customer的map
            Map<String, UserExpandDto> map = userExpandService.queryCustomerInCusIds(cusIdlist);
            if(ObjectUtils.isNotNull(map)){
                for (SugSuggest sug : sugSuggestList) {
                    UserExpandDto customer = map.get(sug.getCusId().toString());
                    if(ObjectUtils.isNotNull(customer)){
                        sug.setQueryCustomer(customer);
                        sug.setShowname(customer.getShowname());
                    }
                }
            }
            
        }
        return sugSuggestList;
    }

    /**
     * 查询推荐的建议的智慧排行分页
     *
     * @param querySugSuggest 传入类型是意境还是务实
     * @param page            分页参数
     * @return List<SugSuggest>
     * @throws Exception
     */
    public List<SugSuggest> queryRecommendSuggestOrderByWisdomNum(QuerySugSuggest querySugSuggest, PageEntity page) throws Exception {
        List<SugSuggest> sugSuggestList = sugSuggestDao.queryRecommendSuggestOrderByWisdomNum(querySugSuggest, page);// 查询推荐的建议的智慧排行分页
        List<Long> cusIdlist = new ArrayList<Long>();
        //循环博客查询showname
        if (ObjectUtils.isNotNull(sugSuggestList)) {
            for (SugSuggest sug : sugSuggestList) {
                cusIdlist.add(sug.getCusId());
            }
            //通过用户id查询出customer的map
            Map<String, UserExpandDto> map = userExpandService.queryCustomerInCusIds(cusIdlist);
            if(ObjectUtils.isNotNull(map)){
                for (SugSuggest sug : sugSuggestList) {
                    UserExpandDto customer = map.get(sug.getCusId().toString());
                    if(ObjectUtils.isNotNull(customer)){
                        sug.setShowname(customer.getShowname());
                    }
                }
            }
        }
        return sugSuggestList;
    }


    /**
     * 最新问题排行
     *
     * @param size 传入显示的条数
     * @return List<SugSuggest>
     */
    @SuppressWarnings("unchecked")
    public List<SugSuggest> querySuggestOrderById(int size) {
        List<SugSuggest> sugSuggestList = (List<SugSuggest>) memCache.get(MemConstans.SUGGEST_WISDOM);// 读取缓存中的数据
        if (ObjectUtils.isNull(sugSuggestList)) {// 如果缓存中数据为null则读备份缓存的数据
            sugSuggestList = (List<SugSuggest>) memCache.get(MemConstans.SUGGEST_WISDOM + MemConstans.MEM_HOT_BAK);// 读备份缓存的数据
            if (ObjectUtils.isNull(sugSuggestList)) {// 如果两个缓存都为空则从数据库中读取放入缓存中
                sugSuggestList = sugSuggestDao.querySuggestOrderById(size);// 查询推荐的建议的智慧排行
                memCache.set(MemConstans.SUGGEST_WISDOM, sugSuggestList, MemConstans.SUGGEST_WISDOM_WEEK);// 存入memcache6个小时
                memCache.set(MemConstans.SUGGEST_WISDOM + MemConstans.MEM_HOT_BAK, sugSuggestList, MemConstans.SUGGEST_WISDOM_WEEK);// 存入memcache6个小时
            }
        }
        return sugSuggestList;
    }


    /**
     * 该建议id的回复数更新
     *
     * @param suggestId 建议id
     * @param count     更新的数量
     */
    public void updateSugSuggestBySuggestIdCount(Long suggestId, int count) {
        sugSuggestDao.updateSugSuggestBySuggestIdCount(suggestId, count);// 建议的回复数更新
    }

    /**
     * 根据建议id 置顶该建议
     *
     * @param suggestId 建议id
     * @param top       置顶
     */
    public String updateSugSuggestBySuggestIdForTop(Long suggestId, int top) {
        SugSuggest sugSuggest = new SugSuggest();
        sugSuggest.setId(suggestId);// 建议id
        sugSuggest.setTop(top);// 置顶id
        Long falgNum = sugSuggestDao.updateSugSuggestBySuggestIdForTop(sugSuggest);// 根据建议id
        // 置顶该建议
        if (falgNum > 0) {
            return SnsConstants.SUCCESS;// 成功
        } else {
            return SnsConstants.FALSE;// 失败
        }
    }

    /**
     * 根据回复id 更新title content
     *
     * @param sugSuggest 传入回复id title 和content
     * @return String
     */
    public String updateSugSuggestBySuggestIdForContentAndTitle(SugSuggest sugSuggest) {
        Long falgNum = sugSuggestDao.updateSugSuggestBySuggestIdForContentAndTitle(sugSuggest);// 根据回复id
        // 更新title
        // content
        if (falgNum > 0) {
            return SnsConstants.SUCCESS;// 成功
        } else {
            return SnsConstants.FALSE;// 失败
        }
    }

    /**
     * 根据建议id更新建议的浏览次数
     *
     * @param sugSuggestId 建议id
     * @param count        浏览数
     * @return
     */
    public String updateSuggestViewCount(Long sugSuggestId, int count) {
        SugSuggest sugSuggest = new SugSuggest();
        sugSuggest.setId(sugSuggestId);// 传入建议id
        Long falgNum = sugSuggestDao.updateSugSuggestBySuggestIdForBrowseNumAddCount(sugSuggestId, count);// 根据建议id更新建议的浏览次数
        if (falgNum > 0) {
            return SnsConstants.SUCCESS;// 成功
        } else {
            return SnsConstants.FALSE;// 失败
        }
    }

    /**
     * lucene方法 查询传入建议id 查询建议
     *
     * @param sugSuggestIds 建议id的list
     * @return List<SugSuggest>
     * @throws Exception
     */
    public List<SugSuggest> getLuceneSugSuggestByIds(List<Long> sugSuggestIds) throws Exception {
        return sugSuggestDao.getLuceneSugSuggestByIds(sugSuggestIds);
    }

    /**
     * 查出这个建议id之后的建议行数和最大建议id
     *
     * @param sugSuggestId 起始建议id
     * @return Map<String,Object> 返回两个参数一个是建议行数和最大建议id
     * @throws Exception
     */
    public Map<String, Object> getSugSuggestCountAfterId(Long sugSuggestId) throws Exception {
        return sugSuggestDao.getSugSuggestCountAfterId(sugSuggestId);
    }

    /**
     * 查出从beginRow（起始建议行数）开始的建议 一共 pageSize（需要查出多少条）条
     * 要在minSugSuggestId（查询的where条件的最小建议id
     * ）和maxSugSuggestId（查询的where条件的最大建议id）之间查
     *
     * @param beginRow   起始建议行数
     * @param pageSize   需要查出多少条
     * @param minSugSuggestId 查询的where条件的最小建议id
     * @param maxSugSuggestId 查询的where条件的最大建议id
     * @return List<WeiBo>
     * @throws Exception
     */
    public List<SugSuggest> getSugSuggestByPageQuery(Long beginRow, Long pageSize, Long minSugSuggestId, Long maxSugSuggestId) throws Exception {
        return sugSuggestDao.getSugSuggestByPageQuery(beginRow, pageSize, minSugSuggestId, maxSugSuggestId);
    }
}