package com.atdld.os.sns.controller.discuss;

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
import com.atdld.os.sns.constants.DisGroupConstans;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.controller.common.LogController;
import com.atdld.os.sns.controller.common.SnsBaseController;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.discuss.Artclassify;
import com.atdld.os.sns.entity.discuss.DisArticle;
import com.atdld.os.sns.entity.discuss.DisArticleFavor;
import com.atdld.os.sns.entity.discuss.DisArticleReply;
import com.atdld.os.sns.entity.discuss.DisGroup;
import com.atdld.os.sns.entity.discuss.DisGroupClassify;
import com.atdld.os.sns.entity.discuss.DisLookArticle;
import com.atdld.os.sns.entity.discuss.DisMember;
import com.atdld.os.sns.entity.discuss.QueryDisArtAndRep;
import com.atdld.os.sns.entity.friend.Friend;
import com.atdld.os.sns.service.discuss.DisArticleService;
import com.atdld.os.sns.service.discuss.DisGroupService;
import com.atdld.os.sns.service.discuss.DisMemberService;
import com.atdld.os.sns.service.friend.FriendService;
import com.atdld.os.sns.service.impl.visitstat.VisitStatServiceImpl;
import com.atdld.os.sns.service.user.SnsUserService;
import com.atdld.os.sns.service.visitstat.VisitStatService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.controller.discuss.DisArticleAction
 * @description 小组话题
 * @Create Date : 2014年3月26日 下午1:01:13
 */
@Controller
public class DisArticleController extends SnsBaseController {
    /**
     * log对象
     */
    private Logger logger = Logger.getLogger(DisArticleController.class);

    @Autowired
    private DisGroupService disGroupService;
    @Autowired
    private DisMemberService disMemberService;
    @Autowired
    private DisArticleService disArticleService;
    @Autowired
    private SnsUserService snsUserService;
    @Autowired
    private VisitStatService visitStatService;
    @Autowired
    private FriendService friendService;

    private static final String query_DisArticleList = getViewPath("/discuss/disgroup_article_list");// 返回到文章列表页面
    private static final String query_ArticleDetail = getViewPath("/discuss/disarticle_infor");// 返回文章详情页面
    private static final String add_DisArticle = getViewPath("/discuss/release_disarticle");// 添加文章页面
    private static final String query_MyArticleById = getViewPath("/discuss/my_article_list"); // 返回到我的小组文章首页
    private static final String query_HisArticleById = getViewPath("/discuss/person_article_list");// 他的小组文章
    private static final String query_MyArticleDetail = getViewPath("/discuss/edit_my_article_list");// 返回修改页面
    private static final String query_ClassifyDisArticleById = getViewPath("/discuss/classify_article_list");// 分类小组文章
    private static final String ajax_reply_list = getViewPath("/discuss/ajax_reply_list");
    private static final String ajax_myarticle_list = getViewPath("/discuss/ajax_myarticle_list");

    /**
     * 小组文章列表
     *
     * @param groupId 小组id
     */
    @RequestMapping("/dis/art/{groupId}")
    public ModelAndView queryDisArticleList(HttpServletRequest request, @PathVariable Long groupId, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(query_DisArticleList);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            // 查询出小组详细信息
            DisGroup disGroup = disGroupService.queryDisGroupById(groupId);
            if (disGroup == null) {
                return new ModelAndView(setExceptionRequestExsit(request, "对不起，该小组不存在！"));
            }
            DisMember disMember = new DisMember();
            disMember.setGroupId(groupId);
            disMember.setCusId(getLoginUserId(request));
            // 判断该用户是否加入小组
            int isJoin = disGroupService.queryIsJoin(disMember);
            // 查询小组文章列表
            List<DisArticle> disArticleList = disArticleService.queryDisArticleList(groupId, this.getPage());
            // 把返回的结果放到modelAndView中
            modelAndView.addObject("disArticleList", disArticleList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("groupId", groupId);
            modelAndView.addObject("isJoin", isJoin);
            modelAndView.addObject("disGroup", disGroup);
        } catch (Exception e) {
            logger.error("DisArticleAction.queryDisArticleList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 小组文章详情
     *
     * @param articleId 文章id
     */
    @RequestMapping("/dis/artinfor/{articleId}/{groupId}")
    public ModelAndView queryArticleDetail(HttpServletRequest request, @PathVariable Long articleId, @PathVariable Long groupId, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(query_ArticleDetail);
        try {
            // 查询出小组详细信息
            DisGroup disGroup = disGroupService.queryDisGroupById(groupId);
            if (ObjectUtils.isNull(disGroup)) {
                return new ModelAndView(setExceptionRequestExsit(request, "对不起，该小组不存在！"));
            }
            // 判断是否加入小组
            DisMember disMember = new DisMember();
            disMember.setGroupId(groupId);
            disMember.setCusId(getLoginUserId(request));
            int isJoin = disGroupService.queryIsJoin(disMember);
            /*
			 * if (isJoin == 0) { return new
			 * ModelAndView(setExceptionRequestExsit(request, "" +
			 * "对不起，您还没有<a href='" + SnsConstants.contextPath + "/dis/info/" +
			 * groupId + "'>加入小组</a>！")); }
			 */
            // 查看文章详情
            DisArticle disArticle = new DisArticle();
            disArticle.setId(articleId);
            disArticle.setGroupId(groupId);
            disArticle = disArticleService.queryDisArticleDetail(disArticle);
            if (ObjectUtils.isNull(disArticle)) {
                return new ModelAndView(setExceptionRequestExsit(request, "对不起，该文章不存在！"));
            }
            // 浏览过的人
            DisLookArticle disLookArticle = new DisLookArticle();
            // 获得用户id
            Long cusId = getLoginUserId(request);
            disLookArticle.setCusId(cusId);
            disLookArticle.setType(ArticleConstants.ARTICLE_VISIT_TYPE_DISGROUP);
            disLookArticle.setBizId(articleId);
            disLookArticle.setViewDay(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
            // 判断用户是否存在
            if (disArticle != null && disArticle.getCusId().intValue() != cusId.intValue() && cusId.intValue() != 0) {
                DisLookArticle lookRecord = disArticleService.queryLookArticleRecord(disLookArticle);
                if (ObjectUtils.isNull(lookRecord) && cusId.longValue() != 0L) {
                    disLookArticle.setAddTime(new Date());
                    disLookArticle.setCusId(cusId);
                    disLookArticle.setType(ArticleConstants.ARTICLE_VISIT_TYPE_DISGROUP);
                    disLookArticle.setViewDay(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
                    // 获得showname
                    SnsUserExpandDto  userExpandDto = snsUserService.getUserExpandByCusId(cusId);
                    disLookArticle.setShowName(userExpandDto.getShowname());// 添加名字
                    disArticleService.addLookDisArticle(disLookArticle);// 添加用户浏览记录
                }
            }
            List<DisLookArticle> disLookArticleList = disArticleService.queryDisLookArticleRecord(disLookArticle);
            // 更新文章浏览数
            visitStatService.record(VisitStatServiceImpl.TYPES[6], articleId);
            // 文章分类
            // List<Artclassify> artClassify =
            // disArticleService.querydisArtcicleList();
            int visitNum = disArticleService.queryVisitArticleNum(disLookArticle);
            if (cusId.intValue() != 0) {
                Friend friend = new Friend();
                friend.setCusId(getLoginUserId(request));
                friend.setCusFriendId(disArticle.getCusId());
                friend = friendService.queryFriendByCusIdAndCusFriendId(friend);
                modelAndView.addObject("friend", friend);
            }
            DisArticleFavor disArticleFavor = new DisArticleFavor();
            disArticleFavor.setArticleId(articleId);
            disArticleFavor.setCusId(cusId);
            disArticleFavor.setType(ArticleConstants.ACTIVITY_LIKE);
            int flag = disArticleService.queryDisArticleIsFavorOrRecom(disArticleFavor);
            // 把返回的值放到modleAndView中
            modelAndView.addObject("disArticle", disArticle);// 存放文章信息
            modelAndView.addObject("disLookArticleList", disLookArticleList);// 存放浏览记录
            // modelAndView.addObject("artClassify", artClassify);// 文章分类
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("loginId", getLoginUserId(request));
            modelAndView.addObject("isJoin", isJoin);
            modelAndView.addObject("visitNum", visitNum);
            modelAndView.addObject("flag", flag);
        } catch (Exception e) {
            logger.error("DisGroupAction.queryArticleDetail", e);// 记录log
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
    @RequestMapping("/dis/ajax/reply")
    public ModelAndView queryblogReplyList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @RequestParam("status") Integer status) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获得回复列表
            this.setPage(page);
            Long articleId = Long.valueOf(request.getParameter("articleId"));
            Long groupId = Long.valueOf(request.getParameter("groupId"));
            DisArticle disArticle = new DisArticle();
            disArticle.setId(articleId);
            disArticle.setGroupId(groupId);
            // 查看回复列表
            List<DisArticleReply> disArticleReplyList = disArticleService.queryDisArticleReplyList(disArticle, this.getPage());
            modelAndView.setViewName(ajax_reply_list);
            modelAndView.addObject("disArticleReplyList", disArticleReplyList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("loginId", getLoginUserId(request));
            modelAndView.addObject("distatus", status);
        } catch (Exception e) {
            logger.error("DisGroupAction.queryblogReplyList", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 文章分类
     *
     * @param request
     * @return
     */
    @RequestMapping("/dis/item/{groupId}")
    public ModelAndView queryArtclassifyList(HttpServletRequest request, @PathVariable Long groupId) {
        ModelAndView modelAndView = new ModelAndView(add_DisArticle);
        try {
            // 查询文章分类
            // List<Artclassify> artClassifyList =
            // disArticleService.querydisArtcicleList();
            // 把返回的的结果放到mdoelAndView中
            // modelAndView.addObject("artClassifyList", artClassifyList);
            String type = request.getParameter("type");
            if (StringUtils.isEmpty(type)) {
                type = "0";
            }
            DisGroup disGroup = disGroupService.queryDisGroupDetailById(groupId);
            modelAndView.addObject("loginId", getLoginUserId(request));
            modelAndView.addObject("groupId", groupId);
            modelAndView.addObject("disGroup", disGroup);
            modelAndView.addObject("type", type);
        } catch (Exception e) {
            logger.error("DisArticleAction.queryArtclassifyList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    // 发表小组文章 绑定变量名字和属性，把参数封装到类
    @InitBinder("disArticle")
    public void InitBinderDisArticle(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("disArticle.");
    }

    /**
     * 添加小组文章
     *
     * @param request
     * @param disArticle
     * @return
     */
    @RequestMapping("/dis/addart")
    @ResponseBody
    public Map<String, Object> addDisArticle(HttpServletRequest request, @ModelAttribute("disArticle") DisArticle disArticle) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            // 判断是否加入该小组
            DisMember disMember = new DisMember();
            disMember.setCusId(getLoginUserId(request));
            disMember.setGroupId(disArticle.getGroupId());
            int isJoin = disGroupService.queryIsJoin(disMember);
            // 添加小组文章
            if (ObjectUtils.isNotNull(disArticle) && isJoin == 1) {
                if (StringUtils.isEmpty(disArticle.getContent()) || StringUtils.isEmpty(disArticle.getTitle())) {
                    map.put("message", SnsConstants.FALSE);
                    return map;
                }
                disArticle.setCusId(getLoginUserId(request));
                String flag = disArticleService.addDisArticle(disArticle);
                // 日志打印
                Map<String, Object> logMap = LogController.getlogMap(request);
                logMap.put(LogController.ACTION, "addDisArticle");
                logMap.put("articleId", "" + disArticle.getId());
                LogController.printLog(logMap);
                // 日志打印结束
                map.put("message", flag);// 返回成功
            } else {
                map.put("message", SnsConstants.FALSE);
            }
        } catch (Exception e) {
            logger.error("DisArticleAction.addDisArticle", e);// 记录log
            map.put("message", SnsConstants.FALSE);// 报错提示信息
        }
        return map;
    }

    // 发表文章回复 绑定变量名字和属性，把参数封装到类
    @InitBinder("disArticleReply")
    public void InitBinderDisArticleReply(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("disArticleReply.");
    }

    /**
     * 添加回复
     *
     * @param request
     * @param disArticleReply
     * @return
     */
    @RequestMapping("/dis/addrep")
    @ResponseBody
    public Map<String, Object> addDisArticleReply(HttpServletRequest request, @ModelAttribute("disArticleReply") DisArticleReply disArticleReply) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 添加回复
            if (disArticleReply != null && disArticleReply.getReplyContent().length() < 5000) {
                // 查看文章详情
                DisArticle disArticle = new DisArticle();
                disArticle.setId(disArticleReply.getArticleId());
                disArticle.setGroupId(disArticleReply.getGroupId());
                disArticle = disArticleService.queryDisArticleDetail(disArticle);
                if (ObjectUtils.isNotNull(disArticle)) {
                    if (disArticle.getStatus() == 1) {
                        map.put("message", "prohibit");
                        return map;
                    }
                }
                disArticleReply.setRecusId(getLoginUserId(request));
                String flag = disArticleService.addDisArticleReply(disArticleReply);
                SnsUserExpandDto expandDto = snsUserService.getUserExpandByCusId(disArticleReply.getRecusId());
                if (expandDto != null) {
                    disArticleReply.setUserExpandDto(expandDto);
                }
                // 日志打印
                Map<String, Object> logMap = LogController.getlogMap(request);
                logMap.put(LogController.ACTION, "addDisArticleReply");
                logMap.put("replyId", "" + disArticleReply.getId());
                LogController.printLog(logMap);
                // 日志打印结束
                map.put("message", flag);
                map.put("disArticleReply", disArticleReply);
            } else {
                map.put("message", SnsConstants.FALSE);
            }
        } catch (Exception e) {
            logger.error("DisArticleAction.addDisArticleReply", e);
            map.put("message", SnsConstants.FALSE);
        }
        return map;
    }

    /**
     * 删除回复
     *
     * @param request
     * @param disArticleReply 文章id 回复id
     * @return
     */
    @RequestMapping("/dis/delartrep")
    @ResponseBody
    public Map<String, Object> deleteDisArticleReply(HttpServletRequest request, @ModelAttribute("disArticleReply") DisArticleReply disArticleReply) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (disArticleReply != null) {
                // 获得用户id
                disArticleReply.setRecusId(getLoginUserId(request));
                // 查询此条回复是否是本人
                Integer isEmpty = disArticleService.getDisArticleReplyIsMine(disArticleReply);
                if (isEmpty != 0) {
                    // 删除返回结果
                    String flag = disArticleService.deleteDisArticleReply(disArticleReply);
                    // 日志打印
                    Map<String, Object> logMap = LogController.getlogMap(request);
                    logMap.put(LogController.ACTION, "deleteDisArticleReply");
                    logMap.put("replyId", "" + disArticleReply.getId());
                    LogController.printLog(logMap);
                    // 日志打印结束
                    map.put("message", flag);
                } else {
                    map.put("isEmpty", SnsConstants.ISEMPTY);
                }

            } else {
                map.put("message", "isNull");
            }
        } catch (Exception e) {
            logger.error("DisArticleAction.deleteDisArticleReply", e);// 记录log
            map.put("message", SnsConstants.FALSE);// 报错返回错误信息
        }
        return map;
    }

    /**
     * 小组首页查询我的小组文章
     *
     * @param request
     * @param page    分页参数
     * @return
     */
    @RequestMapping("/dis/myart")
    public ModelAndView queryMyArticleById(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(query_MyArticleById);
        try {
            // 获得小组分类列表
            List<DisGroupClassify> disGroupClassify = disGroupService.querydisGroupList();
            // 把返回的list放到modelAndView中
            modelAndView.addObject("disGroupClassify", disGroupClassify);
        } catch (Exception e) {
            logger.error("DisArticleAction.queryMyArticleById,e");// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
        return modelAndView;
    }

    /**
     * ajax 查询我的小组话题
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/dis/ajax/myarticle")
    public ModelAndView queryajaxMyArticle(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            // 获得文章详情
            List<QueryDisArtAndRep> myDisArticle = disArticleService.queryMyArticleById(getLoginUserId(request), this.getPage());
            // 把返回的list放到modelAndView中
            modelAndView.setViewName(ajax_myarticle_list);
            modelAndView.addObject("myDisArticle", myDisArticle);
            modelAndView.addObject("page", this.getPage());
        } catch (Exception e) {
            logger.error("DisArticleAction.queryMyArticleById,e");// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
        return modelAndView;
    }

    /**
     * 查询他的小组文章
     *
     * @param request
     * @param page    分页参数
     * @return
     */
    @RequestMapping("/dis/{userid}/hisart")
    public ModelAndView queryHisArticleById(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @PathVariable Long userid) {
        ModelAndView modelAndView = new ModelAndView(query_HisArticleById);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            List<Artclassify> artClassifyList = disArticleService.querydisArtcicleList();
            // 获得文章详情
            List<QueryDisArtAndRep> disArticleList = disArticleService.queryMyArticleById(userid, this.getPage());
            // 把返回的list放到modelAndView中
            SnsUserExpandDto userExpand = snsUserService.getUserExpandByCusId(getLoginUserId(request));
            modelAndView.addObject("userExpandDto", userExpand);
            modelAndView.addObject("loginId", getLoginUserId(request));
            modelAndView.addObject("disArticleList", disArticleList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("artClassifyList", artClassifyList);
            modelAndView.addObject("userid", userid);
        } catch (Exception e) {
            logger.error("DisArticleAction.queryMyArticleById,e");// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
        return modelAndView;
    }

    /**
     * 删除我的小组文章
     *
     * @param request
     * @param disArticle
     * @return
     */
    @RequestMapping("/dis/delart")
    @ResponseBody
    public Map<String, Object> deleteMyArticleById(HttpServletRequest request, @ModelAttribute("disArticle") DisArticle disArticle) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (disArticle != null) {
                // 删除我的小组文章，返回参数
                String flag = disArticleService.deleteMyArticleById(disArticle);
                // 日志打印
                Map<String, Object> logMap = LogController.getlogMap(request);
                logMap.put(LogController.ACTION, "deleteMyArticleById");
                logMap.put("articleId", "" + disArticle.getId());
                LogController.printLog(logMap);
                // 日志打印结束
                map.put("message", flag);
            } else {
                map.put("message", SnsConstants.FALSE);
            }
        } catch (Exception e) {
            logger.error("DisArticleAction.deleteMyArticleById", e);// 记录log
            map.put("message", SnsConstants.FALSE);
        }
        return map;
    }

    /**
     * 查询单个文章详情，修改时调用
     *
     * @param request
     * @param articleId 文章id
     * @param groupId   小组id
     * @return
     */
    @RequestMapping("/dis/artail/{articleId}/{groupId}")
    public ModelAndView queryMyArticleDetail(HttpServletRequest request, @PathVariable Long articleId, @PathVariable Long groupId) {
        ModelAndView modelAndView = new ModelAndView(query_MyArticleDetail);
        try {
            // 实例文章
            DisArticle disArticle = new DisArticle();
            disArticle.setGroupId(groupId);// 小组id
            disArticle.setId(articleId);// 文章id
            // 获得文章详情
            DisArticle disArticleDetail = disArticleService.queryMyArticleDetail(disArticle);
            // 查询文章分类
            List<Artclassify> artClassifyList = disArticleService.querydisArtcicleList();
            // 返回的结果放到modelAndView中
            modelAndView.addObject("artClassifyList", artClassifyList);
            modelAndView.addObject("disArticleDetail", disArticleDetail);
        } catch (Exception e) {
            logger.error("DisArticleAction.queryMyArticleDetail", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 修改我的小组文章
     *
     * @param request
     * @param disArticle
     * @return
     */
    @RequestMapping("/dis/updateMyArticle")
    @ResponseBody
    public Map<String, Object> updateMyArticle(HttpServletRequest request, @ModelAttribute("disArticle") DisArticle disArticle) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (ObjectUtils.isNotNull(disArticle)) {
                if (StringUtils.isEmpty(disArticle.getContent()) || StringUtils.isEmpty(disArticle.getTitle())) {
                    map.put("message", SnsConstants.FALSE);
                    return map;
                }
                // 更新我的小组文章，返回结果
                String flag = disArticleService.updateMyArticle(disArticle);
                // 日志打印
                Map<String, Object> logMap = LogController.getlogMap(request);
                logMap.put(LogController.ACTION, "updateMyArticle");
                logMap.put("articleId", "" + disArticle.getId());
                LogController.printLog(logMap);
                // 日志打印结束
                map.put("message", flag);
            } else {
                map.put("message", SnsConstants.FALSE);
            }

        } catch (Exception e) {
            logger.error("DisArticleAction.updateMyArticle", e);// 记录log
            map.put("message", SnsConstants.FALSE);
        }
        return map;
    }

    /**
     * 置顶小组文章
     *
     * @param request
     * @param articleId 小组文章articleId
     * @return
     */
    @RequestMapping("/dis/top")
    @ResponseBody
    public Map<String, Object> updateDisArticleByTop(HttpServletRequest request, @RequestParam("articleId") Long articleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获得返回更新结果
            String flag = disArticleService.updateDisArticleByTop(articleId);
            // 把获得结果放到map中
            map.put("message", flag);
        } catch (Exception e) {
            logger.error("DisArticleAction.updateDisArticleByTop", e);// 记录log
            map.put("message", SnsConstants.FALSE);
        }
        return map;
    }

    /**
     * 取消置顶小组文章
     *
     * @param request
     * @param articleId 小组文章articleId
     * @return
     */
    @RequestMapping("/dis/cancel")
    @ResponseBody
    public Map<String, Object> updateCancelDisArticleByTop(HttpServletRequest request, @RequestParam("articleId") Long articleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获得返回更新结果
            String flag = disArticleService.updateCancelDisArticleByTop(articleId);
            // 把获得结果放到map中
            map.put("message", flag);
        } catch (Exception e) {
            logger.error("DisArticleAction.updateCancelDisArticleByTop", e);// 记录log
            map.put("message", SnsConstants.FALSE);
        }
        return map;
    }

    /**
     * 查询分类下的文章
     *
     * @param request
     * @param ClassifyId 分类id
     * @param page       分页参数
     * @return
     */

    @RequestMapping("/dis/classifyart/{classifyId}")
    public ModelAndView queryClassifyDisArticleById(HttpServletRequest request, @PathVariable Long classifyId, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(query_ClassifyDisArticleById);
        try { // 页面传来的数据放到page中
            this.setPage(page);
            // 根据分类id查询分类下的博文
            List<DisArticle> disArticleList = disArticleService.queryClassifyDisArticleById(classifyId, this.getPage()); // 文章分类
            List<Artclassify> artClassifyList = disArticleService.querydisArtcicleList(); // 把返回的list放到modelAndView中
            SnsUserExpandDto userExpand = snsUserService.getUserExpandByCusId(getLoginUserId(request));
            modelAndView.addObject("userExpandDto", userExpand);
            modelAndView.addObject("loginId", getLoginUserId(request));
            modelAndView.addObject("disArticleList", disArticleList);
            modelAndView.addObject("artClassifyList", artClassifyList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("classifyId", classifyId);
        } catch (Exception e) {
            logger.error("DisArticleAction.queryClassifyDisArticleById", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
            //
        }
        return modelAndView;
    }

    /**
     * 更新文章的状态
     *
     * @param request
     * @param disArticle
     * @return
     */
    @RequestMapping("/dis/updatestatus")
    @ResponseBody
    public Map<String, Object> updateDisArticleStatus(HttpServletRequest request, @ModelAttribute("disArticle") DisArticle disArticle, @RequestParam("type") Integer type) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (ObjectUtils.isNotNull(disArticle)) {
                DisMember disMember = new DisMember();
                disMember.setCusId(getLoginUserId(request));
                disMember.setGroupId(disArticle.getGroupId());
                // 查询当前用户是否有权限
                int transfer = disMemberService.queryMemberTransferId(disMember);
                if (transfer == DisGroupConstans.GROUP_MEMBER_TRANSFERID_ADMINISTRATOR) {// 管理员
                    if (type == 1) {// 类型为1 禁言
                        disArticle.setStatus(ArticleConstants.ACTIVITY_STATUS_NO);// 禁止发言
                    } else {
                        disArticle.setStatus(ArticleConstants.ACTIVITY_STATUS_YES);// 允许发言
                    }
                    String flag = disArticleService.updateDisArticleStatus(disArticle);
                    map.put("message", flag);
                } else {
                    map.put("message", "transfer");// 无权限
                }
            }
        } catch (Exception e) {
            logger.error("DisArticleAction.updateDisArticleStatus", e);
            map.put("message", SnsConstants.FALSE);
        }
        return map;
    }

    /**
     * 添加 取消 喜歡數
     *
     * @param request
     * @param blogId
     * @return
     */
    @RequestMapping("/dis/like")
    @ResponseBody
    public Map<String, Object> updateDisArticleFavorite(HttpServletRequest request, @RequestParam("articleId") Long articleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String flag;
            DisArticleFavor disArticleFavor = new DisArticleFavor();
            disArticleFavor.setArticleId(articleId);
            disArticleFavor.setCusId(getLoginUserId(request));
            disArticleFavor.setType(ArticleConstants.ACTIVITY_LIKE);// 喜欢
            int status = disArticleService.queryDisArticleIsFavorOrRecom(disArticleFavor);
            if (status == 0) {
                flag = disArticleService.addDisArticleFavorAndRecom(disArticleFavor);

            } else {
                flag = disArticleService.deleteDisArticleFavorAndRecom(disArticleFavor);
            }
            map.put("message", flag);
            map.put("status", status);
        } catch (Exception e) {
            logger.error("DisArticleAction.updateDisArticleFavorite", e);
            map.put("message", SnsConstants.FALSE);
        }
        return map;
    }

    // 发表小组文章 绑定变量名字和属性，把参数封装到类
    @InitBinder("disArticleFavor")
    public void InitBinderdisArticleFavor(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("disArticleFavor.");
    }

    /**
     * 添加收藏
     *
     * @param request
     * @param disArticleFavor
     * @return
     */
    @RequestMapping("/dis/recommend")
    @ResponseBody
    public Map<String, Object> addDisArticleRecommend(HttpServletRequest request, @ModelAttribute("disArticleFavor") DisArticleFavor disArticleFavor) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (ObjectUtils.isNotNull(disArticleFavor)) {
                disArticleFavor.setCusId(getLoginUserId(request));
                disArticleFavor.setType(ArticleConstants.ACTIVITY_RECOMMEND);
                int status = disArticleService.queryDisArticleIsFavorOrRecom(disArticleFavor);
                if (status == 0) {
                    String flag = disArticleService.addDisArticleFavorAndRecom(disArticleFavor);
                    map.put("message", flag);
                } else {
                    map.put("message", SnsConstants.EXSIT);
                }
            }
        } catch (Exception e) {
            logger.error("DisArticleAction.addDisArticleRecommend", e);
            map.put("message", SnsConstants.FALSE);
        }
        return map;
    }
}
