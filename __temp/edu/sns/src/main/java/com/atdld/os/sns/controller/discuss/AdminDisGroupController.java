package com.atdld.os.sns.controller.discuss;

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

import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.controller.common.SnsBaseController;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.discuss.DisGroup;
import com.atdld.os.sns.entity.discuss.DisGroupClassify;
import com.atdld.os.sns.service.discuss.DisGroupService;
import com.atdld.os.sns.service.discuss.DisMemberService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.controller.discuss.AdminDisGroupAction
 * @description 小组管理后台
 * @Create Date : 2013-12-20 上午9:03:48
 */
@Controller
@RequestMapping("/admin")
public class AdminDisGroupController extends SnsBaseController {
    /**
     * log对象
     */
    private Logger logger = Logger.getLogger(AdminDisGroupController.class);

    @Autowired
    private DisGroupService disGroupService;
    @Autowired
    private DisMemberService disMemberService;

    private static final String query_AllDisGroupList = getViewPath("/admin/discuss/disgroup_list");// 返回后台小组列表
    private static final String querydisGroupClassify = getViewPath("/admin/discuss/disgroup_classify");// 返回后台小组分类
    private static final String query_DisGroupClassifyById = getViewPath("/admin/discuss/update_disgroup_classity");// 分类修改页面
    private static final String query_DisGroupById = getViewPath("/admin/discuss/update_disgroup_list");// 返回到更新小组页面
    private static final String to_addDisGroupClassify = getViewPath("/admin/discuss/add_disgroup_classity");// 返回到添加小组页面

    /**
     * 后台查询所有小组
     *
     * @param request
     * @param page    分页参数
     * @return
     */
    @RequestMapping("/disgroup/queryDisGroupList")
    public ModelAndView queryAdminDisGroupList(HttpServletRequest request, @ModelAttribute("disGroup") DisGroup disGroup, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(query_AllDisGroupList);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            // 获得所有小组的list
            List<DisGroup> disGroupAllList = disGroupService.queryAdminDisGroupList(disGroup, this.getPage());
            // 获得分类的list
            List<DisGroupClassify> disGroupClassify = disGroupService.queryAdminDisGroupClassify();
            // 把返回的数据放到mdoelAndView中
            modelAndView.addObject("disGroupClassify", disGroupClassify);
            modelAndView.addObject("disGroupAllList", disGroupAllList);
            modelAndView.addObject("page", this.getPage());
        } catch (Exception e) {
            logger.error("AdminDisGroupAction.queryAdminDisGroupList", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 后台查询小组分类
     *
     * @param request
     * @return
     */
    @RequestMapping("/disgroup/querydisGroupClassify")
    public ModelAndView querydisGroupList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(querydisGroupClassify);
        try {
            // 获得小组分类列表
            List<DisGroupClassify> disGroupList = disGroupService.queryAdminDisGroupClassify();
            // 把返回的数据放到mdoelAndView中
            modelAndView.addObject("disGroupList", disGroupList);
        } catch (Exception e) {
            logger.error("AdminDisGroupAction.querydisGroupList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 创建小组 绑定变量名字和属性，把参数封装到类
     *
     * @param binder
     */
    @InitBinder("disGroupClassify")
    public void initBinderDisGroupClassify(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("disGroupClassify.");
    }
    /**
     * 跳转到添加小组分类页
     * @return
     */
    @RequestMapping("/disgroup/toadd")
    public String toAddDisGroupClassify(){
    	return to_addDisGroupClassify;
    }
    /**
     * 添加小组分类
     *
     * @param request
     * @param disGroupClassify
     * @return
     */
    @RequestMapping("/disgroup/addclassify")
    public ModelAndView addDisGroupClassify(HttpServletRequest request, @ModelAttribute("disGroupClassify") DisGroupClassify disGroupClassify) {
        try {
            // 添加小组分类
            String flag = disGroupService.queryDisGroupClassifyIsExist(disGroupClassify);
            if (disGroupClassify != null && "false".equals(flag)) {// 该分类不存在才可添加
            	disGroupClassify.setCode("");
                disGroupService.addDisGroupClassify(disGroupClassify);
            }
        } catch (Exception e) {
            logger.error("AdminDisGroupAction.addDisGroupClassify");
            return new ModelAndView(setExceptionRequest(request, e));// 返回到404界面
        }
        return new ModelAndView("redirect:/admin/disgroup/querydisGroupClassify");
    }

    /**
     * 删除小组分类
     *
     * @param request
     * @param classifyId
     * @return
     */
    @RequestMapping("/disgroup/deleteclassify")
    @ResponseBody
    public Map<String, Object> deleteDisGroupClassify(HttpServletRequest request, @RequestParam("classifyId") Long classifyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 根据小组分类id删除
            String flag = disGroupService.deleteDisGroupClassify(classifyId);
            map.put("message", flag);
        } catch (Exception e) {
            logger.error("AdminDisGroupAction.deleteDisGroupClassify", e);
            map.put("message", "false");// 报错返回提示信息
        }
        return map;
    }

    /**
     * 查询小组分类
     *
     * @param request
     * @param classifyId 分类id
     * @return
     */
    @RequestMapping("/disgroup/queryclassify/{classifyId}")
    public ModelAndView queryDisGroupClassifyById(HttpServletRequest request, @PathVariable Long classifyId) {
        ModelAndView modelAndView = new ModelAndView(query_DisGroupClassifyById);
        try {
            // 查询小组分类根据分类id
            DisGroupClassify disGroupClassify = disGroupService.queryDisGroupClassifyById(classifyId);
            modelAndView.addObject("disGroupClassify", disGroupClassify);
        } catch (Exception e) {
            logger.error("AdminDisGroupAction.queryDisGroupClassifyById", e);
            return new ModelAndView(setExceptionRequest(request, e));// 返回到404界面
        }
        return modelAndView;
    }

    /**
     * 修改小组分类
     *
     * @param request
     * @param disGroupClassify
     * @return
     */
    @RequestMapping("/disgroup/updateDisGroupClassify")
    public ModelAndView updateDisGroupClassify(HttpServletRequest request, @ModelAttribute("disGroupClassify") DisGroupClassify disGroupClassify) {
        try {
            // 修改小组分类
            disGroupService.updateDisGroupClassify(disGroupClassify);
        } catch (Exception e) {
            logger.error("AdminDisGroupAction.updateDisGroupClassify", e);
            return new ModelAndView(setExceptionRequest(request, e));// 返回到404界面
        }
        return new ModelAndView("redirect:/admin/disgroup/querydisGroupClassify");
    }

    /**
     * 根据名字查询
     *
     * @param request
     * @param name    小组名称
     * @return
     */
    @RequestMapping("/disgroup/queryDisGroupListByName")
    public ModelAndView queryDisGroupListByName(HttpServletRequest request, @RequestParam("name") String name) {
        ModelAndView modelAndView = new ModelAndView(query_AllDisGroupList);
        try {
            // 根据名字查询
            List<DisGroup> disGroupAllList = disGroupService.queryDisGroupListByName(name);
            modelAndView.addObject("disGroupAllList", disGroupAllList);
        } catch (Exception e) {
            logger.error("AdminDisGroupAction.queryDisGroupListByName", e);
            return new ModelAndView(setExceptionRequest(request, e));// 返回到404界面
        }
        return modelAndView;
    }

    /**
     * 根据小组id删除小组
     *
     * @param request
     * @param groupId 小组id
     * @return
     */
    @RequestMapping("/disgroup/deleteDisGroupById")
    @ResponseBody
    public Map<String, Object> deleteDisGroupById(HttpServletRequest request, @RequestParam("groupId") Long groupId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 根据小组id删除小组
            if (groupId != null) {
                disGroupService.deleteDisGroupById(groupId);
                disMemberService.deleteAllMemberByGroupId(groupId);
                map.put("message", SnsConstants.SUCCESS);// 删除成功
            } else {
                map.put("message", SnsConstants.FALSE);
            }
        } catch (Exception e) {
            logger.error("AdminDisGroupAction.deleteDisGroupById", e);// 记录log
            map.put("message", SnsConstants.FALSE);// 报错返回提示错误信息
        }
        return map;
    }

    /**
     * 审核小组
     *
     * @param request
     * @param groupId 小组id
     * @return
     */
    @RequestMapping("/disgroup/updateDisGroupStatus")
    @ResponseBody
    public Map<String, Object> updateDisGroupStatus(HttpServletRequest request, @RequestParam("groupId") Long groupId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 审核小组通过
            String flag = disGroupService.updateDisGroupStatus(groupId);
            map.put("message", flag);
        } catch (Exception e) {
            logger.error("AdminDisGroupAction.updateDisGroupStatus", e);
            map.put("message", "false");
        }
        return map;
    }

    /**
     * 根据小组id查询小组信息
     *
     * @param request
     * @param groupId
     * @return
     */
    @RequestMapping("/disgroup/queryDisGroupById/{groupId}")
    public ModelAndView queryDisGroupById(HttpServletRequest request, @PathVariable Long groupId) {
        ModelAndView modelAndView = new ModelAndView(query_DisGroupById);
        try {
            // 获得小组分类列表
            List<DisGroupClassify> disGroupList = disGroupService.querydisGroupList();
            // 根据小组id查询小组信息
            DisGroup disGroup = disGroupService.queryDisGroupById(groupId);
            modelAndView.addObject("disGroup", disGroup);
            modelAndView.addObject("disGroupList", disGroupList);
        } catch (Exception e) {
            logger.error("AdminDisGroupAction.queryDisGroupById", e);
            return new ModelAndView(setExceptionRequest(request, e));// 返回到404界面
        }
        return modelAndView;
    }

    /**
     * 创建小组 绑定变量名字和属性，把参数封装到类
     *
     * @param binder
     */
    @InitBinder("disGroup")
    public void initBinderDisGroup(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("disGroup.");
    }

    /**
     * 修改小组组
     *
     * @param request
     * @param disGroup
     * @return
     */
    @RequestMapping("/disgroup/updateDisGroupById")
    public ModelAndView updateDisGroupById(HttpServletRequest request, @ModelAttribute("disGroup") DisGroup disGroup) {
        try {
            // 修改小组
            disGroupService.updateDisGroupById(disGroup);
        } catch (Exception e) {
            logger.error("AdminDisGroupAction", e);
            setExceptionRequest(request, e);
            return new ModelAndView(setExceptionRequest(request, e));// 返回到404界面
        }
        return new ModelAndView("redirect:/admin/disgroup/queryDisGroupList");// 重定向
    }

    /**
     * 小组开启关闭
     *
     * @param request
     * @param groupId
     * @return
     */
    @RequestMapping("/disgroup/updateOpenDisGroupStatus")
    @ResponseBody
    public Map<String, Object> updateOpenDisGroupStatus(HttpServletRequest request, @RequestParam("groupId") Long groupId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 关闭开启小组
            String flag = disGroupService.updateOpenDisGroupStatus(groupId);
            map.put("message", flag);
        } catch (Exception e) {
            logger.error("AdminDisGroupAction.updateOpenDisGroupStatus", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 小组开启
     *
     * @param request
     * @param groupId
     * @return
     */
    @RequestMapping("/disgroup/updateCloseDisGroupStatus")
    @ResponseBody
    public Map<String, Object> updateCloseDisGroupStatus(HttpServletRequest request, @RequestParam("groupId") Long groupId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 开启小组
            String flag = disGroupService.updateCloseDisGroupStatus(groupId);
            map.put("message", flag);
        } catch (Exception e) {
            logger.error("AdminDisGroupAction.updateCloseDisGroupStatus", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 小组拒绝
     *
     * @param request
     * @param groupId
     * @return
     */
    @RequestMapping("/disgroup/updaterefuseDisGroupStatus")
    @ResponseBody
    public Map<String, Object> updaterefuseDisGroupStatus(HttpServletRequest request, @RequestParam("groupId") Long groupId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 拒绝小组
            String flag = disGroupService.updaterefuseDisGroupStatus(groupId);
            map.put("message", flag);
        } catch (Exception e) {
            logger.error("AdminDisGroupAction.updateCloseDisGroupStatus", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 查询小组分类名称和分类代码是否存在
     *
     * @param request
     * @param disGroupClassify 小组分类
     * @return
     */
    @RequestMapping("/disgroup/queryDisGroupClassifyIsExist")
    @ResponseBody
    public Map<String, Object> queryDisGroupClassifyIsExist(HttpServletRequest request, @ModelAttribute("disGroupClassify") DisGroupClassify disGroupClassify) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 查询是否存在返回结果
            String flag = disGroupService.queryDisGroupClassifyIsExist(disGroupClassify);
            map.put("message", flag);// 返回success标示成功
        } catch (Exception e) {
            logger.error("AdminDisGroupAction.queryDisGroupClassifyIsExist", e);// 记录log
            map.put("message", "false");// 报错返回提示信息
        }
        return map;
    }
}
