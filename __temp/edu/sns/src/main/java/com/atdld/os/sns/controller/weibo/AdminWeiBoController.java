package com.atdld.os.sns.controller.weibo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.EnumUtil;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.controller.common.SnsBaseController;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.friend.BlackList;
import com.atdld.os.sns.entity.friend.Friend;
import com.atdld.os.sns.entity.lucene.SearchType;
import com.atdld.os.sns.entity.lucene.SearchWord;
import com.atdld.os.sns.entity.weibo.Comment;
import com.atdld.os.sns.entity.weibo.QueryComment;
import com.atdld.os.sns.entity.weibo.QueryWeiBo;
import com.atdld.os.sns.entity.weibo.WeiBo;
import com.atdld.os.sns.service.friend.BlackListService;
import com.atdld.os.sns.service.friend.FriendService;
import com.atdld.os.sns.service.lucene.SearchWordService;
import com.atdld.os.sns.service.user.SnsUserService;
import com.atdld.os.sns.service.weibo.WeiBoCommentService;
import com.atdld.os.sns.service.weibo.WeiBoService;

/**
 * @author :
 * @ClassName com.atdld.os.core.action.weibo.AdminWeiBoAction
 * @description 后台微博action
 * @Create Date : 2013-12-18 上午9:59:56
 */
@Controller
@RequestMapping("/admin")
public class AdminWeiBoController extends SnsBaseController {

    private static final Logger logger = Logger.getLogger(AdminWeiBoController.class);

    // 路径
    private static final String toWeiBo = getViewPath("/admin/weibo/weibo_list");// 到添加微博页面
    private static final String toWeiBoInfo = getViewPath("/admin/weibo/weibo_info");// 微博详情页面
    private static final String toAllCustomerList = getViewPath("/admin/weibo/to_all_customer_list");// 好友列表
    private static final String toFriendManage = getViewPath("/admin/weibo/to_friend_manage");// 我的好友列表
    private static final String toFansManage = getViewPath("/admin/weibo/to_fans_manage");// 我的粉丝列表
    private static final String queryBlackList = getViewPath("/admin/weibo/query_black_list");// 黑名单管理
    private static final String querySearchWordList = getViewPath("/admin/weibo/query_searchword_list");// 搜索词管理
    private static final String toUpdateSearchWordList = getViewPath("/admin/weibo/to_upate_searchword");// 到搜索词更新页面
    private static final String toAddSearchWordList = getViewPath("/admin/weibo/to_add_searchword");// 到搜索词添加页面

    @Autowired
    private SnsUserService snsUserService;// 用户service
    @Autowired
    private WeiBoService weiBoService;// 微博service
    @Autowired
    private WeiBoCommentService weiBoCommentService;// 微博回复service
    @Autowired
    private BlackListService blackListService;// 黑名单service
    @Autowired
    private FriendService friendService;
    @Autowired
    private SearchWordService searchWordService;

    /**
     * queryWeiBo 微博实体
     *
     * @param binder
     */
    @InitBinder("queryWeiBo")
    public void initBinderQueryWeiBo(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryWeiBo.");
    }

    /**
     * 微博实体
     *
     * @param binder
     */
    @InitBinder("weiBo")
    public void initBinderWeiBo(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("weiBo.");
    }

    /**
     * 微博评论
     *
     * @param binder
     */
    @InitBinder("comment")
    public void initBinderComment(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("comment.");
    }

    /**
     * 用户实体
     *
     * @param binder
     */
    @InitBinder("userExpand")
    public void initBinderUserExpand(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userExpand.");
    }

    /**
     * 好友实体
     *
     * @param binder
     */
    @InitBinder("friend")
    public void initBinderFriend(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("friend.");
    }


    /**
     * 黑名单实体
     *
     * @param binder
     */
    @InitBinder("blackList")
    public void initBinderBlackList(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("blackList.");
    }

    /**
     * 搜索词实体
     *
     * @param binder
     */
    @InitBinder("searchWord")
    public void initBinderSearchWord(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("searchWord.");
    }

    /**
     * 后台查询微博
     *
     * @param request
     * @param weiBo   页面传入查询数据
     * @param page    分页参数
     * @return ModelAndView
     */
    @RequestMapping(value = "/weiBo/toWeiBoList")
    public ModelAndView toWeiBoList(HttpServletRequest request, @ModelAttribute QueryWeiBo queryWeiBo, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(toWeiBo);
        try {
            this.setPage(page);// set 分页参数
            List<QueryWeiBo> queryWeiBoList = weiBoService.queryAdminAllWeiBo(queryWeiBo, this.getPage());// 后台查询微博
            modelAndView.addObject("queryWeiBoList", queryWeiBoList);// 把数据放入modelAndView中
            modelAndView.addObject("page", this.getPage());// 把分页参数放入modelAndView中
        } catch (Exception e) {
            logger.error("AdminWeiBoAction.toWeiBoList", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
        return modelAndView;// 转到添加微博页面
    }

    /**
     * 删除微博
     *
     * @param weiBo   传入微博Id
     * @param request
     * @return
     */
    @RequestMapping(value = "/weiBo/delWeiBo")
    @ResponseBody
    public Map<String, Object> delWeiBo(@ModelAttribute WeiBo weiBo, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (weiBo != null) {// 如果传入的微博不为null则执行
                String falg = weiBoService.delWeiBo(weiBo);// 删除微博
                map.put("message", falg);// 是否删除成功标记 如果等于success则成功等于false则失败
            }
        } catch (Exception e) {
            logger.error("WeiBoAction.delWeiBo", e);
        }
        return map;
    }

    /**
     * 微博置顶
     *
     * @param weiBo 传入微博id
     */
    @RequestMapping(value = "/weiBo/upateWeiBoTop")
    @ResponseBody
    public Map<String, Object> upateWeiBoTop(@ModelAttribute WeiBo weiBo, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String falg = weiBoService.updateWeiBoForTop(weiBo);// 微博置顶
            map.put("message", falg);
        } catch (Exception e) {
            logger.error("WeiBoAction.upateWeiBoTop", e);
        }
        return map;
    }

    /**
     * 取消微博置顶
     *
     * @param weiBo 传入微博id
     */
    @RequestMapping(value = "/weiBo/quxiaoUupateWeiBoTop")
    @ResponseBody
    public Map<String, Object> quxiaoUupateWeiBoTop(@ModelAttribute WeiBo weiBo, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            weiBoService.updateQuXiaoWeiBoForTop(weiBo);// 取消微博置顶
            map.put("message", SnsConstants.SUCCESS);
        } catch (Exception e) {
            logger.error("WeiBoAction.quxiaoUupateWeiBoTop", e);
        }
        return map;
    }

    /**
     * 后台查询一条微博的详情 回复详情
     *
     * @param request
     * @param weiBo   页面传入微博id
     * @param page    分页参数
     * @return ModelAndView
     */
    @RequestMapping(value = "/weiBo/toWeiBoInfo")
    public ModelAndView toWeiBoInfo(HttpServletRequest request, @ModelAttribute Comment comment, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(toWeiBoInfo);
        try {
            this.setPage(page);// set 分页参数
            this.getPage().setPageSize(12);
            List<QueryComment> queryCommentList = weiBoCommentService.queryCommentByWbId(comment, this.getPage());// 查询该微博评论的list
            modelAndView.addObject("queryCommentList", queryCommentList);// 把查出的queryCommentList放入modelAndView中
            modelAndView.addObject("page", this.getPage());// 把分页参数放入modelAndView中
        } catch (Exception e) {
            logger.error("AdminWeiBoAction.toWeiBoInfo", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
        return modelAndView;// 转到添加微博页面
    }

    /**
     * 根据评论id删除评论
     *
     * @param comment 传入评论id
     * @param request
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/weiBo/delCommentById")
    @ResponseBody
    public Map<String, Object> delCommentById(@ModelAttribute Comment comment, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String falg = weiBoCommentService.delCommentById(comment);// 根据评论id删除评论
            map.put("message", falg);// 把信息放入map中返回到页面用json接收
        } catch (Exception e) {
            logger.error("WeiBoAction.delCommentById", e);
        }
        return map;
    }

    /**
     * 用户分页
     *
     * @param request
     * @param customer
     * @param page     分页参数
     * @return
     */
    @RequestMapping(value = "/cus/toAllCustomerList")
    public ModelAndView toAllCustomerList(Model model, HttpServletRequest request, @ModelAttribute("userExpand") SnsUserExpandDto  userExpand , @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(toAllCustomerList);
        try {

        	this.setPage(page);
        	Map<String, Object> map = snsUserService.queryAllCustomer(userExpand, this.getPage());
            List<SnsUserExpandDto> queryCustomerList = (List<SnsUserExpandDto>) map.get("allCustomer");
            modelAndView.addObject("queryCustomerList", queryCustomerList);// 把查出的queryCommentList放入modelAndView中
            modelAndView.addObject("page", map.get("page"));// 把分页参数放入modelAndView中
            modelAndView.addObject("userExpand", userExpand);// 把分页参数放入modelAndView中
        } catch (Exception e) {
            logger.error("AdminWeiBoAction.toAllCustomerList", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
        return modelAndView;// 转到添加微博页面
    }

    /**
     * 好友管理
     *
     * @param request
     * @param customer
     * @param page     分页参数
     * @return
     */
    @RequestMapping(value = "/cus/toFriendManage")
    public ModelAndView toFriendManage(HttpServletRequest request, @ModelAttribute Friend friend, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(toFriendManage);
        try {
            this.setPage(page);// set 分页参数
            List<SnsUserExpandDto> queryCustomerList = weiBoService.queryMyAttentionCustomer(friend, page);
            modelAndView.addObject("queryCustomerList", queryCustomerList);// 把查出的queryCommentList放入modelAndView中
            modelAndView.addObject("friend", friend);// friend放入modelAndView中
            modelAndView.addObject("page", this.getPage());// 把分页参数放入modelAndView中
        } catch (Exception e) {
            logger.error("AdminWeiBoAction.toFriendManage", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
        return modelAndView;// 转到添加微博页面
    }

    /**
     * 删除好友
     *
     * @param friend  传入cusFriendId 和 cusId
     * @param request
     * @return
     */
    @RequestMapping(value = "/cus/delFriend")
    @ResponseBody
    public Map<String, Object> delFriend(@ModelAttribute Friend friend, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String falg = friendService.delFriend(friend);// 删除好友
            map.put("message", falg);
        } catch (Exception e) {
            logger.error("WeiBoAction.delFriend", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 粉丝管理
     *
     * @param request
     * @param customer
     * @param page     分页参数
     * @return
     */
    @RequestMapping(value = "/cus/toFansManage")
    public ModelAndView toFansManage(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(toFansManage);
        try {
            Long id = Long.valueOf(request.getParameter("id"));
            Friend friend = new Friend();
            friend.setCusId(id);
            this.setPage(page);// set传来的分页参数
            List<SnsUserExpandDto> customerList = weiBoService.queryMyFans(friend, this.getPage());// 查询我的粉丝用户列表
            modelAndView.addObject("customerList", customerList);// 查询出的用户list放入modelAndView中
            modelAndView.addObject("id", id);// cusAttention放入modelAndView中
            modelAndView.addObject("page", this.getPage());// 传入分页参数放入modelAndView中
        } catch (Exception e) {
            logger.error("AdminWeiBoAction.toFansManage", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
        return modelAndView;// 转到添加微博页面
    }

    /**
     * 取消微博关注
     *
     * @param cusAttention
     * @param request
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/weiBo/quxiaoAttentionCustomer")
    @ResponseBody
    public Map<String, Object> quxiaoAttentionCustomer(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Long cusId = Long.valueOf(request.getParameter("cusId"));
            Long friId = Long.valueOf(request.getParameter("friId"));
            Friend fri = new Friend();
            fri.setCusFriendId(friId);
            fri.setCusId(cusId);
            String falg = weiBoService.delCusAttention(fri);// 取消微博关注
            map.put("message", falg);
        } catch (Exception e) {
            logger.error("WeiBoAction.quxiaoAttentionCustomer", e);
        }
        return map;
    }

    /**
     * 黑名单列表
     *
     * @param request
     * @param pageEntity 分页参数
     * @return
     */
    @RequestMapping(value = "/black/queryblackList")
    public ModelAndView queryBlackList(HttpServletRequest request, @ModelAttribute BlackList blackList, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(queryBlackList);
        try {
            this.setPage(page);
            List<SnsUserExpandDto> queryCustomerList = blackListService.queryBlackListByCusId(blackList, this.getPage());// 黑名单列表
            modelAndView.addObject("queryCustomerList", queryCustomerList);// 查询出
            // 的黑名单list放入modelAndView中
            modelAndView.addObject("blackList", blackList);// blackList放入modelAndView中
            modelAndView.addObject("page", this.getPage());// 分页参数放入modelAndView中//分页参数放入modelAndView中
        } catch (Exception e) {
            logger.error("LetterAction.queryBlackList", e);
            setExceptionRequest(request, e);
            ModelAndView modelAndViewError = new ModelAndView(ERROR);
            return modelAndViewError;// 返回404页面
        }
        return modelAndView;
    }

    /**
     * 移除黑名单
     *
     * @param request
     * @param blackList 传入cusBlackListId
     */
    @RequestMapping(value = "/black/delblackList")
    @ResponseBody
    public Map<String, Object> delBlackList(HttpServletRequest request, @ModelAttribute BlackList blackList) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            blackListService.delBlackList(blackList);// 移除黑名单 通过cusBlackListId
            // 和cusId
            map.put("message", SnsConstants.SUCCESS);
        } catch (Exception e) {
            logger.error("LetterAction.delBlackList", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 搜索词
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/search/searchWord")
    public ModelAndView querySearchWordList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(querySearchWordList);
        try {
            String type = request.getParameter("type");
            String word = request.getParameter("word");
            if (StringUtils.isEmpty(type)) {
                type = SearchType.weibo.toString();
            }
            SearchWord searchWord = new SearchWord();
            searchWord.setType(type);
            searchWord.setWord(word);

            List<SearchWord> searchWordList = searchWordService.getSearchWordListPage(searchWord, page);
            modelAndView.addObject("searchWordList", searchWordList);// 查询的搜索词的list
            modelAndView.addObject("type", type);
            modelAndView.addObject("word", word);
        } catch (Exception e) {
            logger.error("LetterAction.querySearchWordList", e);
            setExceptionRequest(request, e);
            ModelAndView modelAndViewError = new ModelAndView(ERROR);
            return modelAndViewError;// 返回404页面
        }
        return modelAndView;
    }

    /**
     * 到搜索词修改页面
     *
     * @param request
     * @param word    搜索词
     * @param type    类型
     * @return
     */
    @RequestMapping(value = "/search/toupdatesearchWord")
    public ModelAndView toUpdateSearchWord(HttpServletRequest request, @RequestParam("word") String word, @RequestParam("type") String type) {
        ModelAndView modelAndView = new ModelAndView(toUpdateSearchWordList);
        try {
            SearchWord searchWord = searchWordService.getSearchWordByTypeAndWord(EnumUtil.transStringToEnum(SearchType.class, type), word);
            modelAndView.addObject("searchWord", searchWord);// 通过id查询的搜索词
        } catch (Exception e) {
            logger.error("LetterAction.toUpdateSearchWord", e);
            setExceptionRequest(request, e);
            ModelAndView modelAndViewError = new ModelAndView(ERROR);
            return modelAndViewError;// 返回404页面
        }
        return modelAndView;
    }

    /**
     * 修改搜索词
     */
    @RequestMapping(value = "/search/updateSearchWord")
    public ModelAndView updateSearchWord(HttpServletRequest request, @ModelAttribute SearchWord searchWord) {
        try {
            searchWordService.updateSearchWordByTypeAndWord(searchWord);// 更新搜索词
        } catch (Exception e) {
            logger.error("LetterAction.updateSearchWord", e);
            setExceptionRequest(request, e);
        }
        return new ModelAndView("redirect:/admin/search/searchWord");// 重定向到列表页面
    }

    /**
     * 通过id删除搜索词
     */
    @RequestMapping(value = "/search/delSearchWord")
    @ResponseBody
    public Map<String, Object> delSearchWordById(HttpServletRequest request, @RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            searchWordService.deleteSearchWordById(id);// 根据id 删除搜索词
            map.put("message", SnsConstants.SUCCESS);
        } catch (Exception e) {
            logger.error("LetterAction.delSearchWordById", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 到搜索词修改页面
     *
     * @param request
     * @param word    搜索词
     * @param type    类型
     * @return
     */
    @RequestMapping(value = "/search/toaddsearchWord")
    public ModelAndView toAddSearchWord(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(toAddSearchWordList);
        try {
        } catch (Exception e) {
            logger.error("LetterAction.toAddSearchWord", e);
            setExceptionRequest(request, e);
            ModelAndView modelAndViewError = new ModelAndView(ERROR);
            return modelAndViewError;// 返回404页面
        }
        return modelAndView;
    }

    /**
     * 添加搜索词
     */
    @RequestMapping(value = "/search/addSearchWord")
    public ModelAndView addSearchWord(HttpServletRequest request, @ModelAttribute SearchWord searchWord) {
        try {
            searchWordService.updateSearchWordByTypeAndWord(searchWord);// 更新搜索词
        } catch (Exception e) {
            logger.error("LetterAction.addSearchWord", e);
            setExceptionRequest(request, e);
        }
        return new ModelAndView("redirect:/admin/search/searchWord");// 重定向到列表页面
    }
}
