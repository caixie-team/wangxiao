package com.atdld.os.sns.controller.discuss;

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
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.sns.constants.DisGroupConstans;
import com.atdld.os.sns.constants.PageConstans;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.controller.common.LogController;
import com.atdld.os.sns.controller.common.SnsBaseController;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.discuss.DisArticle;
import com.atdld.os.sns.entity.discuss.DisGroup;
import com.atdld.os.sns.entity.discuss.DisGroupClassify;
import com.atdld.os.sns.entity.discuss.DisMember;
import com.atdld.os.sns.entity.friend.Friend;
import com.atdld.os.sns.service.discuss.DisArticleService;
import com.atdld.os.sns.service.discuss.DisGroupService;
import com.atdld.os.sns.service.discuss.DisMemberService;
import com.atdld.os.sns.service.friend.FriendService;
import com.atdld.os.sns.service.letter.MsgReceiveService;
import com.atdld.os.sns.service.user.SnsUserService;
import com.atdld.os.sns.service.visitstat.VisitStatService;

/**
 * @author :
 * @ClassName com.atdld.os.core.action.discuss.DisGroupAction
 * @description 小组
 * @Create Date : 2013-12-11 下午4:23:42
 */
@Controller
public class DisGroupController extends SnsBaseController {
    /**
     * log对象
     */
    private Logger logger = Logger.getLogger(DisGroupController.class);
    @Autowired
    private DisGroupService disGroupService;
    @Autowired
    private DisMemberService disMemberService;
    @Autowired
    private DisArticleService disArticleService;
    @Autowired
    private MsgReceiveService msgReceiveService;
    @Autowired
    private VisitStatService visitStatService;
    @Autowired
    private SnsUserService snsUserService;
    @Autowired
    private FriendService friendService;

    /**
     * 返回的页面
     */
    private static final String query_disGroupList = getViewPath("/discuss/create_disgroup");// 查询小组分类返回到创建小组页面
    private static final String query_My_DisGroupList = getViewPath("/discuss/my_disgroup_list");// 查询我创建的小组返回到我创建小组列表页面
    private static final String query_MyJoin_DisGroupList = getViewPath("/discuss/my_join_disgroup_list");// 查询我加入的小组返回我加入小组列表页面
    private static final String query_ALL_DisGroupList = getViewPath("/discuss/all_disgroup_list");// 查找所有小组返回到所有小组列表页面
    private static final String query_DisGroupById = getViewPath("/discuss/disgroup_space");// 返回到个人小组空间页面
    private static final String query_DisMemberByGroupId = getViewPath("/discuss/disgroup_member");// 返回所有成员列表页面
    private static final String query_HomePage = getViewPath("/discuss/recommend_disgroup_list");// 小组首页
    private static final String query_Hot_DisGroupList = getViewPath("/discuss/hot_disgroup_list");// 返回热门小组页面
    private static final String query_DisGroupByCondition = getViewPath("/discuss/search_disgroup_list");// 返回搜索页
    private static final String edit_DisGroupById = getViewPath("/discuss/edit_disgroup");// 编辑小组信息页面
    private static final String query_ClassifyDisGroupList = getViewPath("/discuss/classify_disgroup_list");// 分类小组页面
    private static final String query_OtherJoinDisGroupList = getViewPath("/phome/p_join_disgroup");// 他人加入小组

    private static final String ajax_article_list = getViewPath("/discuss/ajax_article_list");

    /**
     * 查询小组分类
     *
     * @param request
     * @return
     */
    @RequestMapping("/dis/classify")
    public ModelAndView querydisGroupList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(query_disGroupList);
        try {
            // 获得小组分类列表
            List<DisGroupClassify> disGroupList = disGroupService.querydisGroupList();
            // 判断用户是否创建十个小组
            DisGroup disGroup = new DisGroup();
            disGroup.setCusId(getLoginUserId(request));
            int count = disGroupService.queryMyDisGroupByCusId(disGroup);
            // 把返回的结果放到modelAndview中
            modelAndView.addObject("disGroupList", disGroupList);
            modelAndView.addObject("count", count);
            modelAndView.addObject("loginUserId", getLoginUserId(request));
        } catch (Exception e) {
            logger.error("DisGroupAction.querydisGroupList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    // 创建小组 绑定变量名字和属性，把参数封装到类
    @InitBinder("disGroup")
    public void initBinderDisgroup(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("disGroup.");
    }

    /**
     * 添加小组
     *
     * @param request
     * @param disGroup 小组信息
     * @return
     */
    @RequestMapping("/dis/add")
    @ResponseBody
    public Map<String, Object> addDisGroup(HttpServletRequest request, @ModelAttribute("disGroup") DisGroup disGroup) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 判断用户是否创建二十个小组
            DisGroup disGroup1 = new DisGroup();
            disGroup1.setCusId(getLoginUserId(request));
            int count = disGroupService.queryMyDisGroupByCusId(disGroup1);
            if (ObjectUtils.isNotNull(disGroup) && count <= 20) {
                if (StringUtils.isEmpty(disGroup.getIntroduction()) || StringUtils.isEmpty(disGroup.getName())) {
                    map.put("message", SnsConstants.FALSE);
                    return map;
                }
                // 添加用户id
                disGroup.setCusId(getLoginUserId(request));
                disGroup.setStatus(DisGroupConstans.GROUP_STATUS_PASS);
                // 添加博文
                String flag = disGroupService.addDisGroup(disGroup);
                // 日志打印
                Map<String, Object> logMap = LogController.getlogMap(request);
                logMap.put(LogController.ACTION, "addDisGroup");
                logMap.put("disGroupId", "" + disGroup.getId());
                LogController.printLog(logMap); // 日志打印结束
                // 把返回结果放到map中
                map.put("message", flag);
            } else {
                map.put("message", SnsConstants.FALSE);
            }
        } catch (Exception e) {
            logger.error("DisGroupAction.addDisGroup", e);// 记录log
            map.put("message", SnsConstants.FALSE);
        }
        return map;
    }

    /**
     * 查询我创建的小组
     *
     * @param request
     * @return
     */
    @RequestMapping("/dis/my")
    public ModelAndView queryMyDisGroupList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(query_My_DisGroupList);
        try {
            // 获得我创建的小组的list
            List<DisGroup> disMyGroupList = disGroupService.queryMyDisGroupList(getLoginUserId(request));
            // 获得小组分类列表
            List<DisGroupClassify> disGroupList = disGroupService.querydisGroupList();
            // 获得该用户
            Long cusId = getLoginUserId(request);
            DisGroup disGroup = new DisGroup();
            disGroup.setCusId(cusId);
            // 获得用户创建小组数
            int count = disGroupService.queryMyDisGroupByCusId(disGroup);
            // 把返回的结果放到mdoelAndView中
            modelAndView.addObject("disMyGroupList", disMyGroupList);
            modelAndView.addObject("count", count);
            modelAndView.addObject("disGroupClassify", disGroupList);
        } catch (Exception e) {
            logger.error("DisGroupAction.queryMyDisGroupList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 申请加入小组
     *
     * @param request
     * @param groupId  小组id
     * @param letterId 站内信id
     * @param cusId    用户id
     * @return
     */
    @RequestMapping("/dis/apply")
    @ResponseBody
    public Map<String, Object> addMember(HttpServletRequest request, @RequestParam("groupId") Long groupId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (getLoginUserId(request) == 0L) {//用户未登陆处理
                return map;
            }
            if (disGroupService.queryDisGroupDetailById(groupId) != null) {

                // 加入小组
                DisMember disMember = new DisMember();
                disMember.setGroupId(groupId);
                disMember.setCusId(getLoginUserId(request));
                disMember.setAddTime(new Date());
                SnsUserExpandDto  userExpandDto = snsUserService.getUserExpandByCusId(getLoginUserId(request));
                disMember.setShowName(userExpandDto.getShowname());// 添加会员名
                disMember.setTransferId(DisGroupConstans.GROUP_MEMBER_TRANSFERID_COMMON);// 普通成员
                String flag = disMemberService.addDisMember(disMember);// 添加小组成员
                map.put("message", flag);
            } else {
                map.put("message", "notExsit");
            }
        } catch (Exception e) {
            logger.error("DisGroupAction.addMember", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 查询我加入的小组
     *
     * @param page 分页参数
     */
    @RequestMapping("/dis/join")
    public ModelAndView queryMyJoinDisGroupList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(query_MyJoin_DisGroupList);
        try {
            if (getLoginUserId(request) == 0L) {//用户未登陆处理
                return modelAndView;
            }
            // set成员id
            DisMember disMember = new DisMember();
            disMember.setCusId(getLoginUserId(request));
            // 页面传来的数据放到page中
            this.setPage(page);
            // 根据成员id，分页参数获得我加入的小组的list
            List<DisGroup> disMyJoinGroupList = disGroupService.queryMyJoinDisGroupList(disMember, this.getPage());
            // 获得小组分类列表
            List<DisGroupClassify> disGroupList = disGroupService.querydisGroupList();
            // 判断用户是否创建小组
            Integer IsCreate = disGroupService.queryIsCreateDisGroup(getLoginUserId(request));

            // 把返回的结果返回到modelAndView中
            modelAndView.addObject("disMyJoinGroupList", disMyJoinGroupList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("disGroupClassify", disGroupList);
            modelAndView.addObject("IsCreate", IsCreate);
            modelAndView.addObject("loginId", getLoginUserId(request));
        } catch (Exception e) {
            logger.error("DisGroupAction.queryMyJoinDisGroupList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 查询他人加入的小组
     *
     * @param page 分页参数
     */
    @RequestMapping("/p/{userid}/dis")
    public ModelAndView queryOtherJoinDisGroupList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @PathVariable Long userid) {
        ModelAndView modelAndView = new ModelAndView(query_OtherJoinDisGroupList);
        try {

            // set成员id
            DisMember disMember = new DisMember();
            disMember.setCusId(userid);
            // 页面传来的数据放到page中
            this.setPage(page);
            // 根据成员id，分页参数获得我加入的小组的list
            List<DisGroup> disMyJoinGroupList = disGroupService.queryMyJoinDisGroupList(disMember, this.getPage());
            // 获得小组分类列表
            List<DisGroupClassify> disGroupList = disGroupService.querydisGroupList();
            // 判断用户是否创建小组
            Integer IsCreate = disGroupService.queryIsCreateDisGroup(getLoginUserId(request));
            // 获得用户的信息
            SnsUserExpandDto  userExpandDto = snsUserService.getUserExpandByCusId(userid);

            // 把返回的结果返回到modelAndView中
            modelAndView.addObject("disMyJoinGroupList", disMyJoinGroupList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("disGroupList", disGroupList);
            modelAndView.addObject("IsCreate", IsCreate);
            modelAndView.addObject("loginId", getLoginUserId(request));
            modelAndView.addObject("userid", userid);
            modelAndView.addObject("showname", userExpandDto.getShowname());
            modelAndView.addObject("userExpandDto", userExpandDto);
        } catch (Exception e) {
            logger.error("DisGroupAction.queryMyJoinDisGroupList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 退出小组
     *
     * @param request
     * @param groupId 小组id
     * @return
     */
    @RequestMapping("/dis/exit/{groupId}")
    @ResponseBody
    public Map<String, Object> exitDisGroup(HttpServletRequest request, @PathVariable Long groupId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            DisMember disMember = new DisMember();
            disMember.setGroupId(groupId);
            disMember.setCusId(getLoginUserId(request));
            // 删除该用户信息
            disMemberService.deleteDisGroupExit(disMember);
            // 日志打印
            Map<String, Object> logMap = LogController.getlogMap(request);
            logMap.put(LogController.ACTION, "exitDisGroup");
            logMap.put("disGroupId", "" + groupId);
            LogController.printLog(logMap); // 日志打印结束
            // 返回删除成功信息
            map.put("message", SnsConstants.SUCCESS);
        } catch (Exception e) {
            logger.error("DisGroupAction.exitDisGroup", e);// 记录log
            map.put("message", SnsConstants.FALSE);// 报错提示错误信息
        }
        return map;
    }

    /**
     * 查询所有小组
     *
     * @param page 分页参数
     */
    @RequestMapping("/dis")
    public ModelAndView queryDisGroupALLList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(query_ALL_DisGroupList);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            // 获得所有小组的list
            List<DisGroup> disGroupAllList = disGroupService.queryDisGroupALLList(this.getPage());
            // 获得小组分类列表
            List<DisGroupClassify> disGroupList = disGroupService.querydisGroupList();
            // 判断用户是否创建小组
            Integer IsCreate = disGroupService.queryIsCreateDisGroup(getLoginUserId(request));
            // 把返回的结果放到mdoelAndView中
            modelAndView.addObject("disGroupAllList", disGroupAllList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("disGroupClassify", disGroupList);
            modelAndView.addObject("IsCreate", IsCreate);
            modelAndView.addObject("loginId", getLoginUserId(request));
        } catch (Exception e) {
            logger.error("DisGroupAction.queryDisGroupALLList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 查找热门小组
     *
     * @param page 分页
     */
    @RequestMapping("/dis/hot")
    public ModelAndView queryHotDisGroupList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(query_Hot_DisGroupList);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            // 查询热门小组
            List<DisGroup> disHotGroupList = disGroupService.queryHotDisGroupList(this.getPage());
            // 获得小组分类列表
            List<DisGroupClassify> disGroupList = disGroupService.querydisGroupList();
            // 判断用户是否创建小组
            Integer IsCreate = disGroupService.queryIsCreateDisGroup(getLoginUserId(request));
            // 把返回的结果放到modelAndView中
            modelAndView.addObject("disHotGroupList", disHotGroupList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("disGroupClassify", disGroupList);
            modelAndView.addObject("IsCreate", IsCreate);
            modelAndView.addObject("loginId", getLoginUserId(request));
        } catch (Exception e) {
            logger.error("DisGroupAction.queryHotDisGroupList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 根据小组id查询小组相关信息
     *
     * @param groupId小组id page 分页
     */
    @RequestMapping("/dis/info/{groupId}")
    public ModelAndView queryDisGroupById(HttpServletRequest request, @PathVariable Long groupId, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(query_DisGroupById);
        try {

            // 查询出小组详细信息
            DisGroup disGroup = disGroupService.queryDisGroupById(groupId);
            if (disGroup == null) {
                return new ModelAndView(setExceptionRequestExsit(request, "对不起，该小组不存在！"));
            }
            // 判断是否加入该小组
            DisMember disMember = new DisMember();
            disMember.setCusId(getLoginUserId(request));
            disMember.setGroupId(groupId);
            int isJoin = disGroupService.queryIsJoin(disMember);
            int isTransfer = 0;
            if (isJoin != 0) {
                // 查询是否有权限
                isTransfer = disMemberService.queryMemberTransferId(disMember);
            }
            // 根据小组id查找小组成员按照时间排序
            List<DisMember> disMemberList = disMemberService.queryDisMemberByaddTime(groupId);
            // 页面传来的数据放到page中
            this.setPage(page);

            // 把返回的值放到mdoelAndView中
            modelAndView.addObject("disGroup", disGroup);
            modelAndView.addObject("disMemberList", disMemberList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("isJoin", isJoin);
            modelAndView.addObject("isTransfer", isTransfer);
            modelAndView.addObject("loginId", getLoginUserId(request));
        } catch (Exception e) {
            logger.error("DisGroupAction.queryDisGroupById", e);// 记录log
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
    @RequestMapping("/dis/ajax/article")
    public ModelAndView querydisReplyList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @RequestParam("groupId") Long groupId) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获得回复列表
            this.setPage(page);
            // 根据小组id查出文章
            List<DisArticle> disArticleList = disArticleService.queryDisArticleList(groupId, this.getPage());
            // 判断是否加入该小组
            DisMember disMember = new DisMember();
            disMember.setCusId(getLoginUserId(request));
            disMember.setGroupId(groupId);
            int isJoin = disGroupService.queryIsJoin(disMember);
            int isTransfer = 0;
            if (isJoin != 0) {
                // 查询是否有权限
                isTransfer = disMemberService.queryMemberTransferId(disMember);
            }
            modelAndView.setViewName(ajax_article_list);
            modelAndView.addObject("disArticleList", disArticleList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("loginId", getLoginUserId(request));
            modelAndView.addObject("isJoin", isJoin);
            modelAndView.addObject("isTransfer", isTransfer);
        } catch (Exception e) {
            logger.error("DisGroupAction.querydisReplyList", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 根据小组号查询小组信息
     *
     * @param request
     * @param disNumber 小组号码
     * @param page
     * @return
     */
    @RequestMapping("/g/{disNumber}")
    public ModelAndView queryDisGroupByDisNumber(HttpServletRequest request, @PathVariable String disNumber, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(query_DisGroupById);
        try {
            // 查询出小组详细信息
            DisGroup disGroup = disGroupService.queryDisGroupByDisNumber(disNumber);
            // 判断是否加入该小组
            DisMember disMember = new DisMember();
            disMember.setCusId(getLoginUserId(request));
            disMember.setGroupId(disGroup.getId());
            int isJoin = disGroupService.queryIsJoin(disMember);
            int isTransfer = 0;
            if (isJoin != 0) {
                // 查询是否有权限
                isTransfer = disMemberService.queryMemberTransferId(disMember);
            }
            // 根据小组id查找小组成员按照时间排序
            List<DisMember> disMemberList = disMemberService.queryDisMemberByaddTime(disGroup.getId());
            // 页面传来的数据放到page中
            this.setPage(page);
            // 根据小组id查出文章
            List<DisArticle> disArticleList = disArticleService.queryDisArticleList(disGroup.getId(), this.getPage());
            // 把返回的值放到mdoelAndView中
            modelAndView.addObject("disGroup", disGroup);
            modelAndView.addObject("disMemberList", disMemberList);
            modelAndView.addObject("disArticleList", disArticleList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("isJoin", isJoin);
            modelAndView.addObject("isTransfer", isTransfer);
        } catch (Exception e) {
            logger.error("DisGroupAction.queryDisGroupById", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 查看小组成员，根据小组id
     *
     * @param groupId小组id page 分页
     */
    @RequestMapping("/dis/memb/{groupId}")
    public ModelAndView queryDisMemberByGroupId(HttpServletRequest request, @PathVariable Long groupId, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(query_DisMemberByGroupId);
        try {
            // 查询创建者信息
            DisMember disMemberf = new DisMember();
            disMemberf.setGroupId(groupId);
            disMemberf = disMemberService.queryDisMemebrDetail(disMemberf);
            // 查询群所有管理员
            List<DisMember> adminList = disMemberService.queryDisMemberAdministratorByGroup(groupId);

            // 页面传来的数据放到page中
            this.setPage(page);
            this.getPage().setPageSize(PageConstans.DisMember_Page);// 默认页10
            // 获得所有成员的list
            List<DisMember> disMemberALLList = disMemberService.queryDisMemberByGroupId(groupId, this.getPage());
            if (disMemberALLList == null || disMemberALLList.size() == 0) {
                return new ModelAndView(ERROR_404);
            }
            DisMember disMember = new DisMember();
            disMember.setCusId(getLoginUserId(request));
            disMember.setGroupId(groupId);
            // 判断该用户是否加入小组
            int isJoin = disGroupService.queryIsJoin(disMember);
            // 获得权限值
            int transfer = disMemberService.queryMemberTransferId(disMember);
            Friend fri = new Friend();
            fri.setCusId(getLoginUserId(request));
            Map<Long, Friend> friMap = friendService.queryMyFriendCustomer(fri);// 获得当前登陆用户的关注用户的map
            for (DisMember dis : disMemberALLList) {
                fri = friMap.get(dis.getCusId());// 通过发表动态的用户id查询是否关注过
                if (fri != null) {
                    dis.setCusAttentionId(fri.getId());
                }
            }
            for (DisMember admindis : adminList) {
                fri = friMap.get(admindis.getCusId());// 通过发表动态的用户id查询是否关注过
                if (fri != null) {
                    admindis.setCusAttentionId(fri.getId());
                }
            }
            // 查询
            //disMemberf.setCusAttentionId(friMap.get(getLoginUserId(request)).getId());
            // 把返回的list放到modelAndView中
            modelAndView.addObject("disMemberALLList", disMemberALLList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("groupId", groupId);
            modelAndView.addObject("disName", disMemberALLList.get(0).getDisName());
            modelAndView.addObject("disImageUrl", disMemberALLList.get(0).getDisImageUrl());
            modelAndView.addObject("isJoin", isJoin);
            modelAndView.addObject("transfer", transfer);
            modelAndView.addObject("loginId", getLoginUserId(request));
            modelAndView.addObject("disMember", disMemberf);
            modelAndView.addObject("adminList", adminList);
        } catch (Exception e) {
            logger.error("DisGroupAction.queryDisMemberByGroupId", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * homepage小组首页
     *
     * @param request
     * @return
     */
    @RequestMapping("/dis/home")
    public ModelAndView queryHomePageDisGroup(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(query_HomePage);
        try {
            // 查询小组分类
            List<DisGroupClassify> disGroupClassify = disGroupService.querydisGroupList();
            // 首页小组排行
            List<DisGroup> homePageDisGroup = disGroupService.queryHomePageDisGroup();

            for (int i = 0; i < disGroupClassify.size(); i++) {
                // 把改分类下的小组放到分类下
                List<DisGroup> list = new ArrayList<DisGroup>();
                DisGroupClassify classifyId = disGroupClassify.get(i);
                for (int j = 0; j < homePageDisGroup.size(); j++) {
                    DisGroup disGroup = homePageDisGroup.get(j);
                    if (classifyId.getId().longValue() == disGroup.getDisclassifyId().longValue()) {
                        list.add(disGroup);
                    }
                }
                classifyId.setDisGroupList(list);// set小组信息
            }
            // 把返回的结果放到modelAndView中
            modelAndView.addObject("disGroupClassify", disGroupClassify);
            modelAndView.addObject("homePageDisGroup", homePageDisGroup);
            modelAndView.addObject("loginId", getLoginUserId(request));
        } catch (Exception e) {
            logger.error("DisGroupAction.queryHomePageDisGroup", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// //报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 转让小组
     *
     * @param request
     * @param groupId 小组id
     * @return
     */
    @RequestMapping("/dis/assign")
    @ResponseBody
    public Map<String, Object> updateMemberTransferId(HttpServletRequest request, @ModelAttribute("disMember") DisMember disMember) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            if (ObjectUtils.isNotNull(disMember)) {
                if (disMemberService.queryDisMemebrDetail(disMember).getCusId().intValue() == disMember.getCusId().intValue()) {
                    map.put("message", "yourSelf");
                    return map;
                } else {
                    DisMember disMember1 = new DisMember();
                    disMember1.setCusId(getLoginUserId(request));// 获得当前用户
                    disMember1.setGroupId(disMember.getGroupId());// 获得小组id
                    // 获得权限值
                    int transfer = disMemberService.queryMemberTransferId(disMember1);
                    if (transfer == 0) {
                        SnsUserExpandDto  userExpandDto = snsUserService.getUserExpandByCusId(disMember.getCusId());
                        disMember.setShowName(userExpandDto.getShowname());
                        // 更新小组创建者
                        disGroupService.updateDisgroupCreater(disMember);
                        map.put("message", SnsConstants.SUCCESS);
                    } else {
                        map.put("message", transfer);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("DisGroupAction.updateMemberTransferId", e);// 记录log
            map.put("message", SnsConstants.FALSE);// 错误提示错误信息
        }
        return map;
    }

    /**
     * 小组搜索
     *
     * @param request
     * @param page
     * @param disGroup
     * @return
     */
    @RequestMapping("/dis/search")
    public ModelAndView queryDisGroupByCondition(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("disGroup") DisGroup disGroup) {
        ModelAndView modelAndView = new ModelAndView(query_DisGroupByCondition);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            // 获得搜索小组
            List<DisGroup> disGroupList = disGroupService.queryDisGroupByCondition(disGroup, this.getPage());
            // 获得小组分类列表
            List<DisGroupClassify> disGroupClassify = disGroupService.querydisGroupList();
            modelAndView.addObject("disGroupList", disGroupList);
            modelAndView.addObject("disGroupClassify", disGroupClassify);
        } catch (Exception e) {
            logger.error("DisGroupAction.queryDisGroupByCondition", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// //报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 小组成员
     *
     * @param binder
     */
    @InitBinder("disMember")
    public void initBinderdisMember(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("disMember.");
    }

    /**
     * 查询小组成员 转让用户的列表
     *
     * @param request
     * @param disMember
     * @return
     */
    @RequestMapping("/dis/member")
    @ResponseBody
    public Map<String, Object> querydisMemberByzr(HttpServletRequest request, @ModelAttribute("disMember") DisMember disMember) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获得小组成员
            List<DisMember> disMemberList = disMemberService.querydisMemberByzr(disMember);
            // 把小组成员放到map中
            map.put("disMemberList", disMemberList);
        } catch (Exception e) {
            logger.error("DisGroupAction.querydisMemberByzr", e);// 记录log
            map.put("message", SnsConstants.FALSE);// 报错返回提示信息
        }
        return map;

    }

    /**
     * 查询小组信息
     *
     * @param request
     * @param groupId
     * @return
     */
    @RequestMapping("/dis/disedit/{groupId}")
    public ModelAndView queryDisGroupById(HttpServletRequest request, @PathVariable Long groupId) {
        ModelAndView modelAndView = new ModelAndView(edit_DisGroupById);
        try {
            // 更新小组信息
            DisMember disMember = new DisMember();
            disMember.setCusId(getLoginUserId(request));
            disMember.setGroupId(Long.valueOf(groupId));
            int transfer = disMemberService.queryMemberTransferId(disMember);
            if (transfer == 1) {
                return new ModelAndView(ERROR_404);// 报错返回404界面
            }
            // 获得小组信息
            DisGroup disgroup = disGroupService.queryDisGroupDetailById(groupId);
            // 获得小组分类列表
            List<DisGroupClassify> disGroupClassify = disGroupService.querydisGroupList();
            // 把返回的数据放到modelAndView
            modelAndView.addObject("disgroup", disgroup);
            modelAndView.addObject("disGroupClassify", disGroupClassify);
        } catch (Exception e) {
            logger.error("DisGroupAction.queryDisGroupById", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 更新小组信息
     *
     * @param request
     * @param disGroup
     * @return
     */
    @RequestMapping("/dis/update")
    @ResponseBody
    public Map<String, Object> updateDisGroupDetailById(HttpServletRequest request, @ModelAttribute("disGroup") DisGroup disGroup) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获得小组信息
            //DisGroup disgroup1 = disGroupService.queryDisGroupDetailById(disGroup.getId().intValue());
            // 更新小组信息
            DisMember disMember = new DisMember();
            disMember.setCusId(getLoginUserId(request));
            disMember.setGroupId(disGroup.getId());
            int transfer = disMemberService.queryMemberTransferId(disMember);
            if (transfer == 1) {
                map.put("message", SnsConstants.FALSE);
                return map;
            }
            disGroup.setCusId(getLoginUserId(request));
            disGroup.setCreateTime(new Date());
            String flag = disGroupService.updateDisGroupDetailById(disGroup);
            /*
			 * if (!disGroup.g.equals(disgroup1.getImageOriginal())) { // 删除旧图片
			 * String[] arr = new String[] { disgroup1.getImageOriginal(),
			 * disgroup1.getImageUrl(), disgroup1.getImageLittle() };
			 * Map<String, String> map1 = new HashMap<String, String>();
			 * JSONArray jsonObject = JSONArray.fromObject(arr);
			 * map1.put("files", jsonObject.toString());
			 * HttpUtil.doPost(SnsConstants.imagesPath + "/del", map1); }
			 */
            // 日志打印
            Map<String, Object> logMap = LogController.getlogMap(request);
            logMap.put(LogController.ACTION, "updateDisGroupDetailById");
            logMap.put("disGroupId", "" + disGroup.getId());
            LogController.printLog(logMap);
            // 日志打印结束
            map.put("message", flag);
        } catch (Exception e) {
            logger.error("DisGroupAction.updateDisGroupDetailById", e);
            map.put("message", SnsConstants.FALSE);
        }
        return map;
    }

    /**
     * 查询分类下的小组
     *
     * @param request
     * @param page       分页参数
     * @param classifyId 分类id
     * @return
     */
    @RequestMapping("/dis/classifygroup/{classifyId}")
    public ModelAndView queryClassifyDisGroupList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @PathVariable Long classifyId) {
        ModelAndView modelAndView = new ModelAndView(query_ClassifyDisGroupList);
        try {
            if (classifyId != null) {
                // 页面传来的数据放到page中
                this.setPage(page);
                // 查询分类下的小组
                List<DisGroup> disGroupList = disGroupService.queryClassifyDisGroupList(classifyId, this.getPage());
                // 获得小组分类列表
                List<DisGroupClassify> disGroupClassifyList = disGroupService.querydisGroupList();
                modelAndView.addObject("disGroupList", disGroupList);// 把返回的小组放到modelAndView中
                modelAndView.addObject("classifyId", classifyId);// 把分类id放到modelAndView中
                modelAndView.addObject("disGroupClassifyList", disGroupClassifyList);// 把小组分类放到modelAndView中
                modelAndView.addObject("page", this.getPage());
            }
        } catch (Exception e) {
            logger.error("DisGroupAction.queryClassifyDisGroupList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 删除小组成员
     *
     * @param request
     * @param cusId   成员id
     * @return
     */
    @RequestMapping("/dis/delmember")
    @ResponseBody
    public Map<String, Object> deleteDisMember(HttpServletRequest request, @ModelAttribute("disMember") DisMember disMember) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获得权限值
            if (disMember != null) {
                DisMember dismem = new DisMember();
                dismem.setCusId(getLoginUserId(request));
                dismem.setGroupId(disMember.getGroupId());
                int transfer = disMemberService.queryMemberTransferId(dismem);
                if (transfer == 0) {
                    // 删除 小组成员退出小组公用一个方法
                    String flag = disMemberService.deleteDisGroupExit(disMember);
                    map.put("message", flag);
                }
            } else {
                map.put("message", "false");
            }
        } catch (Exception e) {
            logger.error("DisGroupAction.deleteDisMember", e);// 记录log
            map.put("message", "false");
        }
        return map;
    }

    /**
     * 提拔 免去管理员
     *
     * @param request
     * @param disMember
     * @param type
     * @return
     */
    @RequestMapping("/dis/promote")
    @ResponseBody
    public Map<String, Object> updatePromoteUserTransfer(HttpServletRequest request, @ModelAttribute("disMember") DisMember disMember, @RequestParam("type") Integer type) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String flag;
            if (ObjectUtils.isNotNull(disMember)) {// 判断为空
                if (type == 0) {
                    disMember.setTransferId(DisGroupConstans.GROUP_MEMBER_TRANSFERID_ADMINISTRATOR);// 提拔管理员
                    flag = disMemberService.updatePromoteUserTransfer(disMember);
                } else {
                    disMember.setTransferId(DisGroupConstans.GROUP_MEMBER_TRANSFERID_COMMON);// 免去管理员
                    flag = disMemberService.updatePromoteUserTransfer(disMember);
                }
                map.put("message", flag);
            } else {
                map.put("message", "false");
            }

        } catch (Exception e) {
            logger.error("DisGroupAction.updatePromoteUserTransfer", e);// 记录log
            map.put("message", "false");
        }
        return map;
    }
}
