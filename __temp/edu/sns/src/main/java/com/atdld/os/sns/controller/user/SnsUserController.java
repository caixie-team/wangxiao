package com.atdld.os.sns.controller.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.common.contoller.SingletonLoginUtils;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.DateUtils;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.sns.constants.LuceneConstans;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.controller.common.SnsBaseController;
import com.atdld.os.sns.entity.blog.BlogBlog;
import com.atdld.os.sns.entity.course.Course;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.customer.Visitor;
import com.atdld.os.sns.entity.discuss.DisArticle;
import com.atdld.os.sns.entity.discuss.DisGroup;
import com.atdld.os.sns.entity.discuss.DisGroupClassify;
import com.atdld.os.sns.entity.friend.BlackList;
import com.atdld.os.sns.entity.friend.Friend;
import com.atdld.os.sns.entity.lucene.SearchType;
import com.atdld.os.sns.entity.lucene.SearchWord;
import com.atdld.os.sns.entity.search.SearchDto;
import com.atdld.os.sns.entity.weibo.QueryWeiBo;
import com.atdld.os.sns.entity.weibo.WeiBo;
import com.atdld.os.sns.service.blog.BlogBlogService;
import com.atdld.os.sns.service.customer.VisitorService;
import com.atdld.os.sns.service.discuss.DisArticleService;
import com.atdld.os.sns.service.discuss.DisGroupService;
import com.atdld.os.sns.service.friend.BlackListService;
import com.atdld.os.sns.service.friend.FriendService;
import com.atdld.os.sns.service.lucene.LuceneSearchBlogService;
import com.atdld.os.sns.service.lucene.LuceneSearchSuggestService;
import com.atdld.os.sns.service.lucene.LuceneSearchWeiBoService;
import com.atdld.os.sns.service.lucene.SearchWordService;
import com.atdld.os.sns.service.user.SnsUserService;
import com.atdld.os.sns.service.weibo.WeiBoService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.controller.user.UserAction
 * @description
 * @Create Date : 2014-1-17 下午5:26:00
 */
@Controller
public class SnsUserController extends SnsBaseController {

    private static final Logger logger = Logger.getLogger(SnsUserController.class);

    // 路径
    private static final String home = getViewPath("/home/home");// 到添加建议页面
    private static final String phome = getViewPath("/home/p_home");// 个人详情页

    // 搜索页面
    private static final String search = getViewPath("/search/searchglobal");

    private static final String ajax_courseright = getViewPath("/home/ajax_courseright");
    private static final String ajax_discussright = getViewPath("/home/ajax_discussright");

    @Autowired
    private WebHessianService webHessianService;
    
    @Autowired
    private LuceneSearchBlogService luceneSearchBlogService;

    @Autowired
    private LuceneSearchWeiBoService luceneSearchWeiBoService;
    @Autowired
    private LuceneSearchSuggestService luceneSearchSuggestService;

    @Autowired
    private SnsUserService snsUserService;
    @Autowired
    private BlogBlogService blogBlogService;
    @Autowired
    private DisGroupService disGroupService;
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private WeiBoService weiBoService;
    @Autowired
    private SearchWordService searchWordService;
    @Autowired
    private FriendService friendService;
    @Autowired
    private BlackListService blackListService;
    @Autowired
    private DisArticleService disArticleService;

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("search")
    public void initBindersearch(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("search.");
    }

    // 去登录页面 登录页面 注释掉

	
      @RequestMapping("/login") 
      public String toIndex() { 
    	  return "/login/login"; }
	 

    // 首页
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        return "redirect:/u/home";
    }

    /**
     * 个人主页
     */
    @RequestMapping("/u/home")
    public ModelAndView home(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(home);
        try {
            // 获得用户的信息
            SnsUserExpandDto userExpand = snsUserService.getUserExpandByCusId(getLoginUserId(request));
            List<Visitor> visitorList = visitorService.queryVisitorByCusId(getLoginUserId(request));// 最近访客
            int num = visitorService.queryVisitorNumByCusId(getLoginUserId(request));// 查询访问量
            modelAndView.addObject("visitorList", visitorList);// 最近访客
            modelAndView.addObject("num", num);// 查询访问量
            modelAndView.addObject("userExpandDto", userExpand);// 用户的信息放到modelAndView中
            return modelAndView;
        } catch (Exception e) {
            logger.error("UserAction.home", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
    }
    
    /**
     * 验证是否登陆
     */
    @RequestMapping("/islogin")
    @ResponseBody
    public Map<String, Object> islogin(HttpServletRequest request) {
    	if (this.isLogin(request)) {
			setJson(true, null, null);
		}
		return json;
    }
    
    /**
	 * 查询登陆用户id
	 * 
	 * @return
	 */
	@RequestMapping("/user/loginuser")
	@ResponseBody
	public Object loginuser(HttpServletRequest request) {
		try {
			JsonObject userJsonObject = SingletonLoginUtils.getLoginUser(request);
			if (ObjectUtils.isNotNull(userJsonObject)) {
				userJsonObject.addProperty("password","");
				userJsonObject.addProperty("customerkey", "");
				this.setJson(true, null, userJsonObject);
			} else {
				this.setJson(false, null, null);
			}
		} catch (Exception e) {
			logger.error("UserController.exit", e);
			this.setJson(false, "", null);
		}
		return json;
	}
	
    /**
     * 他人主页
     */
    @RequestMapping("/p/{userid}/home")
    public ModelAndView personhome(HttpServletRequest request, @PathVariable Long userid) {
        ModelAndView modelAndView = new ModelAndView(phome);
        try {
            List<Visitor> visitorList = visitorService.queryVisitorByCusId(userid);// 最近访客
            int num = visitorService.queryVisitorNumByCusId(userid);// 查询访问量

            // 获得用户的信息
            SnsUserExpandDto userExpand = snsUserService.getUserExpandByCusId(userid);
            if (userExpand == null) {
                return new ModelAndView(setExceptionRequestExsit(request, "对不起，该会员不存在！"));
            }
            // 获得文章
            List<BlogBlog> blogBlogList = blogBlogService.getPersonMyArticleList(userid, SnsConstants.PERSON_BLOG_NUM);
            // 查询微博
            int status = 0;// 如果为0 能看到不公开的微博 为1 则 只能看到公开的微博
            if (userid.longValue() != getLoginUserId(request).longValue()) {// 如果观看别人微博时
                // 只能看公开的微博
                status = 1;
            }
            List<WeiBo> weiBoList = weiBoService.queryPersonWeiBoById(userid, status, SnsConstants.PERSON_WEIBO_NUM);
           // List<SellWay> sellWayList = userExpandService.getSellWayHotList();
            // 查询我加入的小组
            List<DisGroup> disGroupList = disGroupService.queryPersonDisGroupById(userid);

            // 查询是否关注过
            if (getLoginUserId(request) != 0L) {
                // 查询是否是好友
                Friend friend = new Friend();
                friend.setCusId(getLoginUserId(request));
                friend.setCusFriendId(userid);
                friend = friendService.queryFriendByCusIdAndCusFriendId(friend);
                Friend fri = new Friend();
                fri.setCusId(getLoginUserId(request));
                fri.setCusFriendId(userid);
                int cusAttentionNum = friendService.queryFriendByCusIdAndFriendIdNum(fri);
                BlackList blackList = new BlackList();// 实例化黑名单
                blackList.setCusId(getLoginUserId(request));// set 用户id
                blackList.setCusBlackListId(userid);// set 黑名单的用户id
                int blacknum = blackListService.queryBlackListByCusIdAndCusBlacklistId(blackList);
                modelAndView.addObject("friend", friend);// 判断是够为好友
                modelAndView.addObject("cusAttentionNum", cusAttentionNum);// 是否关注
                modelAndView.addObject("blacknum", blacknum);
            }
            if (userid.intValue() != getLoginUserId(request).intValue() && getLoginUserId(request) != 0L) {
                // 添加最近访客
                Visitor visitor = new Visitor();
                visitor.setCusId(userid);
                visitor.setAddTime(new Date());
                visitor.setVisitorCusId(getLoginUserId(request));
                visitor.setViewDay(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
                // 获得showname
                SnsUserExpandDto  
                customer = snsUserService.getUserExpandByCusId(getLoginUserId(request));
                visitor.setShowname(customer.getShowname());
                // 添加访问者
                visitorService.addVisitor(visitor);
            }

            SnsUserExpandDto customer1 = new SnsUserExpandDto();
            customer1.setCusId(userid);
            customer1 = snsUserService.getUserExpandByCusId(userid);
            modelAndView.addObject("userid", userid);// 用户的uid放到modelAndView中
            modelAndView.addObject("userExpandDto", userExpand);// 用户的信息放到modelAndView中
            modelAndView.addObject("blogBlogList", blogBlogList);// 把文章list放到modelAndView中
            modelAndView.addObject("weiBoList", weiBoList);// 把微博的list放到modelAndView中
            modelAndView.addObject("disGroupList", disGroupList);// 把我加入的小组list放到modelAndView中
            modelAndView.addObject("customer", customer1);// 用户showname
            modelAndView.addObject("loginId", getLoginUserId(request));// 当前登录用户id

            modelAndView.addObject("visitorList", visitorList);// 最近访客
            modelAndView.addObject("num", num);// 查询访问量
        } catch (Exception e) {
            logger.error("UserAction.personhome", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
        return modelAndView;// 转到添加建议页面
    }

    /**
     * 搜索功能
     *
     * @param request
     * @param init 关键字
     * @param page
     * @return
     */
    @RequestMapping(value = "/search")
    public ModelAndView search(HttpServletRequest request, @RequestParam(value = "init", required = false) String init, @ModelAttribute("page") PageEntity page,
                               @ModelAttribute("search") SearchDto searchDao) {
        ModelAndView modelAndView = new ModelAndView(search);

        try {
            if (snsUserService.checkLimitOpt(MemConstans.SEARCH_LIMIT, getLoginUserId(request))) {
                snsUserService.customerOptLimitCountAdd(MemConstans.SEARCH_LIMIT, getLoginUserId(request));// 给该用户添加一次搜索次数

                // 获得小组分类列表
                List<DisGroupClassify> disGroupClassifyList = disGroupService.querydisGroupList();
                if (StringUtils.isEmpty(searchDao.getTab())) {
                    searchDao.setTab("weibo");// 默认tab微博搜索
                }
                //高级展开时，关键字不作为搜索条件
                if ("show".equalsIgnoreCase(searchDao.getFalgshow())) {
                    searchDao.setKeyword("");
                } else {
                    //如果高级收起的。只用搜索词 其他的清除
                    SearchDto searchDtoTmp = new SearchDto();
                    searchDtoTmp.setKeyword(searchDao.getKeyword());
                    searchDtoTmp.setTab(searchDao.getTab());
                    searchDao = searchDtoTmp;
                }
                // 初始化跳转时
                if (init != null) {
                    modelAndView.addObject("init", true);
                    return modelAndView;
                }
                // 只有微博，博客，荐言堂，小组关键字不能为空
				/*
				 * if (SearchType.blog.toString().equals(searchDao.getTab()) ||
				 * SearchType.dis.toString().equals(searchDao.getTab()) ||
				 * SearchType.sug.toString().equals(searchDao.getTab()) ||
				 * SearchType.weibo.toString().equals(searchDao.getTab())) { if
				 * (StringUtils.isEmpty(searchDao.getKeyword())) { return
				 * modelAndView; } }
				 */

                page.setPageSize(LuceneConstans.LUCENE_PAGE_SIZE.intValue());
                Map<String, String> searchMap = new HashMap<String, String>();
                searchMap.put(LuceneConstans.LUCENE_CURRID, "" + getLoginUserId(request));
                searchMap.put(LuceneConstans.LUCENE_SEARCH_KEYWORD, searchDao.getKeyword());

                if (SearchType.blog.toString().equals(searchDao.getTab())) {// 博文
                    // showname
                    if (StringUtils.isNotEmpty(searchDao.getBlogshowname())) {
                        searchMap.put(LuceneConstans.LUCENE_SEARCH_USER, searchDao.getBlogshowname());
                    }
                    if (StringUtils.isNotEmpty(searchDao.getBlogdate())) {
                        searchMap.put(LuceneConstans.LUCENE_SEARCH_DATE, searchDao.getBlogdate());
                    }
                    // title
                    if (StringUtils.isNotEmpty(searchDao.getBlogtitle())) {
                        searchMap.put(LuceneConstans.BLOGFIELD[0], searchDao.getBlogtitle());
                    }
                    Map<String, Object> map = luceneSearchBlogService.queryPageBlogByKeyWord(searchMap, page);
                    if (!ObjectUtils.isNull(map)) {
                        modelAndView.addObject("datalist", map.get(LuceneConstans.LUCENE_SEARCHED_RESULT_IDS));
                    }
                } else if (SearchType.weibo.toString().equals(searchDao.getTab())) {// 微博
                    if (StringUtils.isNotEmpty(searchDao.getWeiboshowname())) {
                        searchMap.put(LuceneConstans.LUCENE_SEARCH_USER, searchDao.getWeiboshowname());
                    }
                    if (StringUtils.isNotEmpty(searchDao.getWeibodate())) {
                        searchMap.put(LuceneConstans.LUCENE_SEARCH_DATE, searchDao.getWeibodate());
                    }

                    Map<String, Object> map = luceneSearchWeiBoService.queryPageWeiBoByKeyWord(searchMap, page);
                    if (!ObjectUtils.isNull(map)) {
                        @SuppressWarnings("unchecked")
                        List<QueryWeiBo> queryWeiBoList = (List<QueryWeiBo>) map.get(LuceneConstans.LUCENE_SEARCHED_RESULT_IDS);
                        if (queryWeiBoList != null && queryWeiBoList.size() > 0) {
                            for (QueryWeiBo queryWeiBo : queryWeiBoList) {
                                SnsUserExpandDto userExpand = snsUserService.getUserExpandByCusId(queryWeiBo.getCusId());// 查询用户的信息
                                if (userExpand != null) {// 如果能够查到则set 头像信息
                                    queryWeiBo.setAvatar(userExpand.getAvatar());
                                }
                            }
                        }
                        modelAndView.addObject("datalist", queryWeiBoList);
                    }
                } else if (SearchType.sug.toString().equals(searchDao.getTab())) {// 建议
                    // user
                    if (StringUtils.isNotEmpty(searchDao.getSugshowname())) {
                        searchMap.put(LuceneConstans.LUCENE_SEARCH_USER, searchDao.getSugshowname());
                    }
                    // date
                    if (StringUtils.isNotEmpty(searchDao.getSugbodate())) {
                        searchMap.put(LuceneConstans.LUCENE_SEARCH_DATE, searchDao.getSugbodate());
                    }
                    // acccptusername
                    if (StringUtils.isNotEmpty(searchDao.getSugRecUser())) {
                        searchMap.put(LuceneConstans.SUGGESTFIELD[4], searchDao.getSugRecUser());
                    }
                    // type
                    if (StringUtils.isNotEmpty(searchDao.getSugType()) && !"0".equals(searchDao.getSugType())) {
                        searchMap.put(LuceneConstans.SUGGESTFIELD[5], searchDao.getSugType());
                    }
                    // title
                    if (StringUtils.isNotEmpty(searchDao.getSugtitle())) {
                        searchMap.put(LuceneConstans.SUGGESTFIELD[0], searchDao.getSugtitle());
                    }
                    Map<String, Object> map = luceneSearchSuggestService.queryPageSuggestByKeyWord(searchMap, page);
                    if (!ObjectUtils.isNull(map)) {
                        modelAndView.addObject("datalist", map.get(LuceneConstans.LUCENE_SEARCHED_RESULT_IDS));
                    }
                } else if (SearchType.dis.toString().equals(searchDao.getTab())) {// 小组

                    DisGroup disGroup = new DisGroup();
                    disGroup.setName(searchDao.getKeyword());
                    if (StringUtils.isNotEmpty(searchDao.getDisshowname())) {
                        disGroup.setShowName(searchDao.getDisshowname());
                    }
                    if (StringUtils.isNotEmpty(searchDao.getDisclasstiy()) && !"0".equals(searchDao.getDisclasstiy())) {
                        disGroup.setDisclassifyId(Long.valueOf(searchDao.getDisclasstiy()));
                    }
                    disGroup.setFlag(searchDao.getDisgroupFlag());
                    logger.info("qry disGroup:" + disGroup);

                    // disGroupService.queryDisGroupByCondition(disGroup, page);
                    modelAndView.addObject("datalist", disGroupService.queryDisGroupByCondition(disGroup, page));
                }
                // 操作搜索词计数
                SearchWord searchWord = new SearchWord();
                searchWord.setType(searchDao.getTab());
                searchWord.setWord(searchDao.getKeyword());
                searchWordService.updateSearchWord(searchWord);

                modelAndView.addObject("disGroupClassifyList", disGroupClassifyList);

            } else {
                modelAndView.addObject("error", "搜索频繁，请稍后再试。");
            }
            modelAndView.addObject("search", searchDao);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            logger.error("searchblog error:", e);
        }
        return modelAndView;
    }

    /**
     * 头部查询用户
     *
     * @param request
     * @param showName 会员名
     * @return
     */
    @RequestMapping(value = "/search/cus")
    @ResponseBody
    public Map<String, Object> queryCustomer(HttpServletRequest request, @RequestParam("showName") String showName) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<SnsUserExpandDto> customerList = snsUserService.queryCustomerByShowName(showName, 3);// 查询好友
            map.put("customerList", customerList);// 放入map中
        } catch (Exception e) {
            logger.error("UserAction.queryCustomer:", e);
        }
        return map;
    }

    /**
     * 头部热词查询
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/search/hotK")
    @ResponseBody
    public Map<String, Object> hotKey(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<SearchWord> hotKeylist = searchWordService.getSearchWordList(SearchType.weibo);// 查询热词
            map.put("hotKeylist", hotKeylist);// 放入map中
        } catch (Exception e) {
            logger.error("UserAction.hotKey", e);
        }
        return map;
    }

    /**
     * 头部查询登陆用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/u/info")
    @ResponseBody
    public Map<String, Object> queryCustomerByCusId(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(getLoginUserId(request));// 通过登陆用户的id查询用户信息
            map.put("userExpandDto", userExpandDto);// 用户信息
        } catch (Exception e) {
            logger.error("UserAction.queryCustomerByCusId:", e);
        }
        return map;
    }

    /**
     * 右侧热门课程
     *
     * @param request
     * @return
     */
    @RequestMapping("/u/ajax/cright")
    public ModelAndView queryCourseRightList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName(ajax_courseright);//返回页面
            // 获得所有推荐课程
            Map<String, List<Course>> mapCourseList = snsUserService.getCourseListByHomePage(0L);
            modelAndView.addObject("mapCourseList", mapCourseList);
        } catch (Exception e) {
            logger.error("UserAction.queryCourseRightList", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 右侧最新话题
     *
     * @param request
     * @return
     */
    @RequestMapping("/u/ajax/wbright")
    public ModelAndView queryDiscussRightList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<DisArticle> disArticleList = disArticleService.queryDisArticleListForHome();// 查询全站微博
            modelAndView.setViewName(ajax_discussright);//返回页面
            modelAndView.addObject("disArticleList", disArticleList);//返回数据
        } catch (Exception e) {
            logger.error("UserAction.queryWeiBoRightList", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }
}
