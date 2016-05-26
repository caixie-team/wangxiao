package com.atdld.os.sns.controller.blog;

import java.util.ArrayList;
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

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.DateUtils;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.sns.constants.ArticleConstants;
import com.atdld.os.sns.constants.BlogConstans;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.controller.common.LogController;
import com.atdld.os.sns.controller.common.SnsBaseController;
import com.atdld.os.sns.entity.blog.BlogBlog;
import com.atdld.os.sns.entity.blog.BlogReply;
import com.atdld.os.sns.entity.blog.QueryBlogAndReply;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.customer.Visitor;
import com.atdld.os.sns.entity.discuss.Artclassify;
import com.atdld.os.sns.entity.discuss.DisLookArticle;
import com.atdld.os.sns.entity.friend.Friend;
import com.atdld.os.sns.service.blog.BlogBlogService;
import com.atdld.os.sns.service.blog.BlogReplyService;
import com.atdld.os.sns.service.customer.VisitorService;
import com.atdld.os.sns.service.discuss.DisArticleService;
import com.atdld.os.sns.service.friend.FriendService;
import com.atdld.os.sns.service.impl.visitstat.VisitStatServiceImpl;
import com.atdld.os.sns.service.user.SnsUserService;
import com.atdld.os.sns.service.visitstat.VisitStatService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.controller.blog.BlogAction
 * @description 博客controller
 * @Create Date : 2013-12-30 下午6:13:27
 */
@Controller
public class BlogController extends SnsBaseController {
    // log对象
    private static final Logger logger = Logger.getLogger(BlogController.class);
    @Autowired
    private BlogBlogService blogBlogService;
    @Autowired
    private SnsUserService snsUserService;
    @Autowired
    private DisArticleService disArticleService;
    @Autowired
    private BlogReplyService blogReplyService;
    @Autowired
    private VisitStatService visitStatService;
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private FriendService friendService;

    private static final String add_blog = getViewPath("/blog/release_blog");// 返回到发表博客页面
    private static final String getMy_BlogBlogList = getViewPath("/blog/my_blog_list");// 我的博文列表
    private static final String get_BlogBlogAllList = getViewPath("/blog/all_blog_list");// 返回全站博客页面
    private static final String get_HotBlogBlogList = getViewPath("/blog/hot_blog_list");// 返回热门博客页面
    private static final String get_BlogBlogDetailById = getViewPath("/blog/blog_infor");// 返回博客详情
    private static final String get_BlogBlogResult = getViewPath("/blog/search_blog");// 搜索返回列表页面
    private static final String get_FriendBlogBlogList = getViewPath("/blog/friend_blog_list");// 好友博文页面
    private static final String get_BlogBlogListByReply = getViewPath("/blog/comment_blog_list");// 评论最多页面
    private static final String get_ArticleListByClassifyId = getViewPath("/blog/classify_blog_list");// 分类博文列表
    private static final String get_BlogBlogById = getViewPath("/blog/edit_blog");// 返回博文编辑页面
    private static final String get_OtherBlogBlogList = getViewPath("/phome/p_blog_list");// 返回他人的博文页面
    private static final String query_BlogBlogAjax = getViewPath("/blog/query_BlogBlogAjax");// 博客ajax
    // 取html
    private static final String ajax_reply_list = getViewPath("/blog/ajax_reply_list");

    // 创建小组 绑定变量名字和属性，把参数封装到类
    @InitBinder("blogBlog")
    public void initBinderBlog(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("blogBlog.");
    }

    /**
     * 添加博文
     *
     * @param request
     * @param blogBlog 博客
     * @return
     */
    @RequestMapping("/blog/crt")
    @ResponseBody
    public Map<String, Object> createBlogBlog(HttpServletRequest request, @ModelAttribute("blogBlog") BlogBlog blogBlog) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            if (ObjectUtils.isNotNull(blogBlog)) {
                if (StringUtils.isEmpty(blogBlog.getContent()) || StringUtils.isEmpty(blogBlog.getTitle())) {
                    map.put("message", SnsConstants.FALSE);
                    return map;
                }
                // 添加用户id
                blogBlog.setCusId(getLoginUserId(request));
                // 添加博文
                String flag = blogBlogService.addBlogBlog(blogBlog);
                // 把返回结果放到map中
                map.put("message", flag);
                // 日志打印
                Map<String, Object> logMap = LogController.getlogMap(request);
                logMap.put(LogController.ACTION, "createBlogBlog");
                logMap.put("blogBlogId", "" + blogBlog.getId());
                LogController.printLog(logMap);
                // 日志打印结束
            } else {
                map.put("message", SnsConstants.FALSE);
            }

        } catch (Exception e) {
            logger.error("BlogAction.createBlogBlog", e);// 记录log
            map.put("message", SnsConstants.FALSE);
        }
        return map;
    }

    /**
     * 文章分类
     *
     * @param request
     * @return
     */
    @RequestMapping("/blog/rele")
    public ModelAndView queryArtclassifyList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(add_blog);
        try {
            // 查询文章分类
            List<Artclassify> artClassifyList = disArticleService.queryArtclassifyList();
            // 把数据放到modelAndView中
            modelAndView.addObject("artClassifyList", artClassifyList);
            modelAndView.addObject("loginId", getLoginUserId(request));
        } catch (Exception e) {
            logger.error("BlogAction.queryArtclassifyList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 查询我的博文
     *
     * @param request
     * @param page    分页参数
     * @return
     */
    @RequestMapping("/blog/my")
    public ModelAndView getMyBlogBlogList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        // 返回到页面
        ModelAndView modelAndView = new ModelAndView(getMy_BlogBlogList);
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return modelAndView;
            }
            // 获得用户id
            Long cusId = getLoginUserId(request);
            // 页面传来的数据放到page中
            this.setPage(page);
            // 查询我的博文
            List<QueryBlogAndReply> blogBlogList = blogBlogService.getMyBlogBlogList(cusId, this.getPage());
            // 一周内文章排行
            List<BlogBlog> BlogBlogListWeek = blogBlogService.getOneWeekBlogBlogList();

            // 把数据放到modelAndView中
            modelAndView.addObject("blogBlogList", blogBlogList);
            modelAndView.addObject("BlogBlogListWeek", BlogBlogListWeek);
            modelAndView.addObject("page", this.getPage());
        } catch (Exception e) {
            logger.error("BlogAction.getMyBlogBlogList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 查询他人的博文
     *
     * @param request
     * @param page    分页参数
     * @return
     */
    @RequestMapping("/p/{userid}/blog")
    public ModelAndView getOtherBlogBlogList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @PathVariable Long userid) {
        // 返回到页面
        ModelAndView modelAndView = new ModelAndView(get_OtherBlogBlogList);
        try {

            // 页面传来的数据放到page中
            this.setPage(page);
            // 查询我的博文
            List<QueryBlogAndReply> blogBlogList = blogBlogService.getMyBlogBlogList(userid, this.getPage());
            // 一周内文章排行
            List<BlogBlog> BlogBlogListWeek = blogBlogService.getOneWeekBlogBlogList();
            if (getLoginUserId(request) != 0L) {
                // 查询是否是好友
                Friend friend = new Friend();
                friend.setCusId(getLoginUserId(request));
                friend.setCusFriendId(userid);
                friend = friendService.queryFriendByCusIdAndCusFriendId(friend);
                modelAndView.addObject("friend", friend);
            }
            if (userid.intValue() != getLoginUserId(request).intValue() && getLoginUserId(request) != 0L) {
                // 添加最近访客
                Visitor visitor = new Visitor();
                visitor.setCusId(userid);
                visitor.setAddTime(new Date());
                visitor.setVisitorCusId(getLoginUserId(request));
                visitor.setViewDay(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
                // 获得showname
                SnsUserExpandDto  userExpandDto = snsUserService.getUserExpandByCusId(getLoginUserId(request));
                visitor.setShowname(userExpandDto.getShowname());
                // 添加访问者
                visitorService.addVisitor(visitor);
            }
            SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(userid);
            // 获得用户的信息
            SnsUserExpandDto userExpand =snsUserService.getUserExpandByCusId(userid);
            // 把数据放到modelAndView中
            modelAndView.addObject("blogBlogList", blogBlogList);
            modelAndView.addObject("BlogBlogListWeek", BlogBlogListWeek);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("userid", userid);
            modelAndView.addObject("showname", userExpandDto.getShowname());
            modelAndView.addObject("loginId", getLoginUserId(request));
            modelAndView.addObject("userExpandDto", userExpand);
        } catch (Exception e) {
            logger.error("BlogAction.getMyBlogBlogList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 根据id删除博文
     *
     * @param request
     * @param id      博文id
     * @return
     */
    @RequestMapping("/blog/dbl")
    @ResponseBody
    public Map<String, Object> deleteBlogBlogById(HttpServletRequest request, @RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BlogBlog blogBlog = new BlogBlog();
            blogBlog.setId(id);
            blogBlog.setCusId(getLoginUserId(request));
            Integer isEmpty = blogBlogService.getBlogIsMine(blogBlog);
            if (isEmpty != 0) {
                // 根据id删除博文，返回结果
                String flag = blogBlogService.deleteBlogBlogById(id);
                // 日志打印
                Map<String, Object> logMap = LogController.getlogMap(request);
                logMap.put(LogController.ACTION, "deleteBlogBlogById");
                logMap.put("blogId", "" + id);
                LogController.printLog(logMap);
                // 日志打印结束
                // 把返回结果放到map中
                map.put("message", flag);
            } else {
                map.put("message", SnsConstants.ISEMPTY);
            }
        } catch (Exception e) {
            logger.error("BlogAction.deleteBlogBlogById", e);// 记录log
            map.put("message", SnsConstants.FALSE);// 报错返回提示信息
        }
        return map;
    }

    /**
     * 查询全站博客
     *
     * @param request
     * @param page    分页参数
     * @return
     */
    @RequestMapping("/blog")
    public ModelAndView getBlogBlogAllList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(get_BlogBlogAllList);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            // 查询文章分类
            List<Artclassify> artClassifyList = disArticleService.queryArtclassifyList();
            // 一周内文章排行
            List<BlogBlog> BlogBlogListWeek = blogBlogService.getOneWeekBlogBlogList();
            modelAndView.addObject("artClassifyList", artClassifyList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("BlogBlogListWeek", BlogBlogListWeek);
        } catch (Exception e) {
            logger.error("BlogAction.getBlogBlogAllList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * ajax 取页面 博客
     *
     * @param request
     * @param page    分页参数
     * @return
     */
    @RequestMapping("/blog/ajax/queryBlogBlogAjax")
    public ModelAndView queryBlogBlogAjax(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("type") int type) {
        ModelAndView modelAndView = new ModelAndView(query_BlogBlogAjax);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            // 获得用户id
            Long cusId = getLoginUserId(request);
            List<QueryBlogAndReply> queryBlogAndReplyList = new ArrayList<QueryBlogAndReply>();
            // 全站博客
            if (type == 1) {
                // 查询全站的博客
                queryBlogAndReplyList = blogBlogService.getBlogBlogAllList(this.getPage());
            }
            // 查询热门的博客
            if (type == 2) {
                // 查询热门的博客
                queryBlogAndReplyList = blogBlogService.getHotBlogBlogList(this.getPage());
            }
            // 查询我的博文
            if (type == 3) {
                //未登录不可操作
                if (getLoginUserId(request).longValue() == 0) {
                    return modelAndView;
                }
                // 查询我的博文
                queryBlogAndReplyList = blogBlogService.getMyBlogBlogList(cusId, this.getPage());
            }
            // 查询好友（好友）博文
            if (type == 4) {
                //未登录不可操作
                if (getLoginUserId(request).longValue() == 0) {
                    return modelAndView;
                }
                // 查询好友（好友）博文
                queryBlogAndReplyList = blogBlogService.getFriendBlogBlogList(cusId, this.getPage());
            }
            // 查询评论最多博文
            if (type == 5) {
                // 查询评论最多博文
                queryBlogAndReplyList = blogBlogService.getBlogBlogListByReply(this.getPage());
            }

            // 把数据放到modelAndView中
            modelAndView.addObject("type", type);
            modelAndView.addObject("queryBlogAndReplyList", queryBlogAndReplyList);
            modelAndView.addObject("page", this.getPage());
        } catch (Exception e) {
            logger.error("BlogAction.queryBlogBlogAjax", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 查询热门博客
     *
     * @param request
     * @param page    分页参数
     * @return
     */
    @RequestMapping("/blog/hot")
    public ModelAndView getHotBlogBlogList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(get_HotBlogBlogList);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            // 查询热门的博客
            List<QueryBlogAndReply> queryBlogAndReplyList = blogBlogService.getHotBlogBlogList(this.getPage());
            // 查询文章分类
            List<Artclassify> artClassifyList = disArticleService.queryArtclassifyList();
            // 把数据放到modelAndView中
            modelAndView.addObject("queryBlogAndReplyList", queryBlogAndReplyList);
            modelAndView.addObject("artClassifyList", artClassifyList);
            modelAndView.addObject("page", this.getPage());
        } catch (Exception e) {
            logger.error("BlogAction.getHotBlogBlogList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 查询好友（好友）博文
     *
     * @param request
     * @param page    分页参数
     * @return
     */
    @RequestMapping("/blog/fri")
    public ModelAndView getFriendBlogBlogList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(get_FriendBlogBlogList);
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return modelAndView;
            }
            // 页面传来的数据放到page中
            this.setPage(page);
            // 获得用户id
            Long cusId = getLoginUserId(request);
            // 查询好友（好友）博文
            List<QueryBlogAndReply> queryBlogAndReplyList = blogBlogService.getFriendBlogBlogList(cusId, this.getPage());
            // 查询文章分类
            List<Artclassify> artClassifyList = disArticleService.queryArtclassifyList();
            // 把数据放到modelAndView中
            modelAndView.addObject("queryBlogAndReplyList", queryBlogAndReplyList);
            modelAndView.addObject("artClassifyList", artClassifyList);
            modelAndView.addObject("page", this.getPage());
        } catch (Exception e) {
            logger.error("BlogAction.getFriendBlogBlogList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 查询评论最多的博文排行
     *
     * @param request
     * @param page    分页参数
     * @return
     */
    @RequestMapping("/blog/rep")
    public ModelAndView getBlogBlogListByReply(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(get_BlogBlogListByReply);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            // 查询评论最多博文
            List<QueryBlogAndReply> queryBlogAndReplyList = blogBlogService.getBlogBlogListByReply(this.getPage());
            // 查询文章分类
            List<Artclassify> artClassifyList = disArticleService.queryArtclassifyList();
            // 把返回的list放到modelAndView中
            modelAndView.addObject("queryBlogAndReplyList", queryBlogAndReplyList);
            modelAndView.addObject("artClassifyList", artClassifyList);
            modelAndView.addObject("page", this.getPage());
        } catch (Exception e) {
            logger.error("BlogAction.getBlogBlogListByReply", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 查询博客详情
     *
     * @param request
     * @param blogId  博客id
     * @return
     */
    @RequestMapping("/blog/info/{blogId}")
    public ModelAndView getBlogBlogDetailById(HttpServletRequest request, @PathVariable Long blogId, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(get_BlogBlogDetailById);
        try {

            BlogBlog blogBlog = blogBlogService.getBlogBlogDetailById(blogId);
            if (blogBlog == null) {
                return new ModelAndView(setExceptionRequestExsit(request, "对不起，该文章不存在！"));
            }
            // 查询文章分类
            List<Artclassify> artClassifyList = disArticleService.queryArtclassifyList();
            // 浏览过的人
            Long cusId = getLoginUserId(request);

            DisLookArticle disLookArticle = new DisLookArticle();
            disLookArticle.setBizId(Long.valueOf(blogId));
            disLookArticle.setType(ArticleConstants.ARTICLE_VISIT_TYPE_BLOG);
            disLookArticle.setCusId(cusId);
            disLookArticle.setViewDay(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
            if (blogBlog != null && blogBlog.getCusId().intValue() != cusId.intValue()) {
                DisLookArticle lookRecord = disArticleService.queryLookArticleRecord(disLookArticle);
                if (lookRecord == null && cusId.longValue() != 0L) {
                    disLookArticle.setAddTime(new Date());
                    disLookArticle.setViewDay(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
                    // 获得showname
                    SnsUserExpandDto  userExpandDto = snsUserService.getUserExpandByCusId(cusId);
                    disLookArticle.setShowName(userExpandDto.getShowname());// 添加名字
                    disArticleService.addLookDisArticle(disLookArticle);// 添加用户浏览记录
                } else {
                    // 如果用户存在，更新用户浏览时间
                    /* disArticleService.updateCusLookRecord(disLookArticle); */
                }
            }
            List<DisLookArticle> blogLookArticleList = disArticleService.queryDisLookArticleRecord(disLookArticle);
            // 每点击一次浏览数则加1
			/* blogBlogService.updateBlogViewCount(blogId); */
            visitStatService.record(VisitStatServiceImpl.TYPES[2], blogId);
            // 活跃度
            blogBlog.setActivity(BlogConstans.BLOG_ACTIVITY_VIEW);
            blogBlogService.updateBlogActivity(blogBlog);
            int visitNum = disArticleService.queryVisitArticleNum(disLookArticle);

            Friend friend = new Friend();
            friend.setCusId(getLoginUserId(request));
            friend.setCusFriendId(blogBlog.getCusId());
            friend = friendService.queryFriendByCusIdAndCusFriendId(friend);
            // 把数据放到modelAndView中
            modelAndView.addObject("blogBlog", blogBlog);
            modelAndView.addObject("loginId", cusId);
            modelAndView.addObject("artClassifyList", artClassifyList);
            modelAndView.addObject("blogLookArticleList", blogLookArticleList);
            modelAndView.addObject("visitNum", visitNum);
            modelAndView.addObject("friend", friend);
        } catch (Exception e) {
            logger.error("BlogAction.getBlogBlogDetailById", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * ajax 获取回复列表
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/blog/ajax/reply")
    public ModelAndView queryblogReplyList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获得回复列表
            this.setPage(page);
            this.getPage().setPageSize(10);
            Long blogId = Long.valueOf(request.getParameter("blogId"));
            List<BlogReply> blogReplyList = blogReplyService.getBlogReplyByBlogId(blogId, this.getPage());
            modelAndView.setViewName(ajax_reply_list);
            modelAndView.addObject("blogReplyList", blogReplyList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("loginId", getLoginUserId(request));
        } catch (Exception e) {
            logger.error("BlogAction.queryblogReplyList", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    // 创建小组 绑定变量名字和属性，把参数封装到类
    @InitBinder("blogReply")
    public void initBinderblogReply(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("blogReply.");
    }

    /**
     * 添加回复
     *
     * @param request
     * @param blogReply 回复博文
     * @return
     */
    @RequestMapping("/blog/addrep")
    @ResponseBody
    public Map<String, Object> addBlogReply(HttpServletRequest request, @ModelAttribute("blogReply") BlogReply blogReply) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            if (ObjectUtils.isNotNull(blogReply)) {
                if (StringUtils.isEmpty(blogReply.getContent())) {
                    map.put("message", SnsConstants.FALSE);
                    return map;
                }
                if (blogReply.getContent().length() < 5000) {
                    // 添加回复
                    blogReply.setCusId(getLoginUserId(request));
                    blogReply.setAddTime(new Date());
                    // 返回结果
                    String flag = blogReplyService.addBlogReply(blogReply);
                    SnsUserExpandDto userExpand = snsUserService.getUserExpandByCusId(blogReply.getCusId());
                    if (userExpand != null) {
                        blogReply.setUserExpandDto(userExpand);
                    }
                    // 日志打印
                    Map<String, Object> logMap = LogController.getlogMap(request);
                    logMap.put(LogController.ACTION, "addBlogReply");
                    logMap.put("blogReplyId", "" + blogReply.getId());
                    LogController.printLog(logMap);
                    // 日志打印结束
                    // 把返回结果放到map中
                    map.put("message", flag);
                    // 把评论信息放到map中
                    map.put("blogReply", blogReply);

                } else {
                    map.put("message", "limitsize");
                }
            } else {
                map.put("message", SnsConstants.ISEMPTY);
            }
        } catch (Exception e) {
            logger.error("BlogAction.addDisArticleReply", e);// 记录log
            map.put("message", SnsConstants.FALSE);

        }
        return map;
    }

    /**
     * 删除回复
     *
     * @param request
     * @param blogReply
     * @return
     */
    @RequestMapping("/blog/drep")
    @ResponseBody
    public Map<String, Object> deleteBlogReplyById(HttpServletRequest request, @ModelAttribute("blogReply") BlogReply blogReply) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            if (blogReply != null) {
                blogReply.setCusId(getLoginUserId(request));
                Integer isEmpty = blogReplyService.getBlogReplyIsMine(blogReply);// 查询此条回复是否是本人
                if (isEmpty != 0) {
                    // 根据id删除回复，返回结果
                    String flag = blogReplyService.deleteBlogReplyById(blogReply);
                    // 日志打印
                    Map<String, Object> logMap = LogController.getlogMap(request);
                    logMap.put(LogController.ACTION, "deleteBlogReplyById");
                    logMap.put("blogReplyId", "" + blogReply.getId());
                    LogController.printLog(logMap);
                    // 日志打印结束
                    map.put("message", flag);
                } else {
                    // 把返回结果放到map中
                    map.put("message", SnsConstants.ISEMPTY);
                }
            } else {
                map.put("message", SnsConstants.FALSE);
            }
        } catch (Exception e) {
            logger.error("AdminBlogAction.deleteBlogReplyById", e);// 记录log
            map.put("message", SnsConstants.FALSE);
        }
        return map;
    }

    /**
     * 搜素博文
     *
     * @param request
     * @param blogBlog 标题 作者
     * @param page     分页参数
     * @return
     */
    @RequestMapping("/blog/sear")
    public ModelAndView getBlogBlogResult(HttpServletRequest request, @ModelAttribute("blogBlog") BlogBlog blogBlog, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(get_BlogBlogResult);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            List<QueryBlogAndReply> queryBlogAndReplyList = blogBlogService.getBlogBlogResult(blogBlog, page);
            // 把返回的list放到ModelAndView中
            modelAndView.addObject("queryBlogAndReplyList", queryBlogAndReplyList);
        } catch (Exception e) {
            logger.error("BlogAction.getBlogBlogResult", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * @param binder
     */
    @InitBinder("disLookArticle")
    public void initBinderdisLookArticle(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("disLookArticle.");
    }

    /**
     * 查询分类下的文章
     *
     * @param request
     * @param ClassifyId 分类id
     * @param page       分页参数
     * @return
     */
    @RequestMapping("/blog/art/{classifyId}")
    public ModelAndView getArticleListByClassifyId(HttpServletRequest request, @PathVariable Integer classifyId, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(get_ArticleListByClassifyId);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            // 根据分类id查询分类下的博文
            List<QueryBlogAndReply> queryBlogAndReplyList = blogBlogService.getArticleListByClassifyId(classifyId, this.getPage());
            // 查询文章分类
            List<Artclassify> artClassifyList = disArticleService.queryArtclassifyList();
            // 把返回的list放到modelAndView中
            modelAndView.addObject("queryBlogAndReplyList", queryBlogAndReplyList);
            modelAndView.addObject("artClassifyList", artClassifyList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("classifyId", classifyId);
        } catch (Exception e) {
            logger.error("BlogAction.getArticleListByClassifyId", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 查询单个博客
     *
     * @param request
     * @param blogBlog
     * @return
     */
    @RequestMapping("/blog/edit/{blogId}")
    public ModelAndView getBlogBlogById(HttpServletRequest request, @PathVariable Long blogId) {
        ModelAndView modelAndView = new ModelAndView(get_BlogBlogById);
        try {
            // 查询单个博客详情
            BlogBlog blogBlogDetail = blogBlogService.getBlogBlogDetailById(blogId);
            // 把返回的结果放到modelAndView中
            modelAndView.addObject("blogBlogDetail", blogBlogDetail);

        } catch (Exception e) {
            logger.error("BlogAction.getBlogBlogById", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 修改我的博文
     *
     * @param request
     * @param disArticle
     * @return
     */
    @RequestMapping("/blog/update")
    @ResponseBody
    public Map<String, Object> updateMyArticle(HttpServletRequest request, @ModelAttribute("blogBlog") BlogBlog blogBlog) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            if (blogBlog != null) {
                if (StringUtils.isEmpty(blogBlog.getContent()) || StringUtils.isEmpty(blogBlog.getTitle())) {
                    map.put("message", SnsConstants.FALSE);
                    return map;
                }
                blogBlog.setCusId(getLoginUserId(request));
                Integer isEmpty = blogBlogService.getBlogIsMine(blogBlog);
                if (isEmpty != 0) {
                    // 更新我的文章，返回结果
                    String flag = blogBlogService.updateBlogBlog(blogBlog);
                    // 日志打印
                    Map<String, Object> logMap = LogController.getlogMap(request);
                    logMap.put(LogController.ACTION, "updateMyArticle");
                    logMap.put("blogBlogId", "" + blogBlog.getId());
                    LogController.printLog(logMap);
                    // 日志打印结束
                    map.put("message", flag);
                } else {
                    map.put("message", SnsConstants.FALSE);
                }
            } else {
                map.put("message", SnsConstants.FALSE);
            }

        } catch (Exception e) {
            logger.error("DisGroupAction.updateMyArticle", e);// 记录log
            map.put("message", SnsConstants.FALSE);
        }
        return map;
    }
}
