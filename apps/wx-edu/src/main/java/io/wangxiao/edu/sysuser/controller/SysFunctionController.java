package io.wangxiao.edu.sysuser.controller;

import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.home.entity.user.User;
import io.wangxiao.edu.sysuser.constants.SysUserConstants;
import io.wangxiao.edu.sysuser.entity.SysFunction;
import io.wangxiao.edu.sysuser.service.FunctionService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description 具体权限管理
 */
@Controller
@RequestMapping("/admin")
public class SysFunctionController extends SysBaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysFunctionController.class);

    String showFunctionList = getViewPath("/sysuser/function_list");
    String toAddFunction = getViewPath("/sysuser/function_add");
    String webStatistics = getViewPath("/sysuser/web_statistics");
    String toUpdateFunction = getViewPath("/sysuser/function_update");
    CacheKit cacheKit = CacheKit.getInstance();
    @Autowired
    private FunctionService functionService;
    @Getter
    @Setter
    private SysFunction function;


    @RequestMapping("/func/statisics")
    public ModelAndView getManagerStatistics(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(webStatistics);
        return modelAndView;
    }

    /**
     * 跳转到添加权限页面
     *
     * @return
     */
    @RequestMapping("/func/toAddFunction")
    public ModelAndView toAddFunction(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(toAddFunction);
        // 一级组
        try {
            //查询所有的权限
            List<SysFunction> functionList = functionService.getUsableFunctionList();
            modelAndView.addObject("functionList", gson.toJson(functionList));
        } catch (Exception e) {
            logger.error("FunctionAction.toAddFunction", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    @InitBinder("function")
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("function.");
    }

    /**
     * 添加权限
     *
     * @return
     */
    @RequestMapping("/func/addFunction")
    public ModelAndView addFunction(@ModelAttribute("function") SysFunction function, HttpServletRequest request) {
        try {
            function.setCreateTime(new Date());
            function.setUpdateTime(new Date());
            //添加新的权限
            functionService.addFunction(function);
            cacheKit.remove(MemConstans.SYS_USER_ALL_FUNCTION);
        } catch (Exception e) {
            logger.error("FunctionAction.addFunction", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return new ModelAndView("redirect:/admin/func/showFunctionList");
    }

    /**
     * 权限管理，显示权限树
     *
     * @return String
     */
    @RequestMapping("/func/showFunctionList")
    public ModelAndView showFunctionList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(showFunctionList);
        try {
            // 查询所有的function
            List<SysFunction> functionList = functionService.getUsableFunctionList();
            // log
            if (functionList != null && functionList.size() > 0) {
                logger.info(" +++ showFunctionList functionList size:"
                        + functionList.size());
            }
            modelAndView.addObject("functionList", gson.toJson(functionList));
        } catch (Exception e) {
            logger.error("FunctionAction.showFunctionList error", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 修改权限初始化页面
     *
     * @return
     */
    @RequestMapping("/func/toUpdateFunction")
    public ModelAndView toUpdateFunction(@ModelAttribute("function") SysFunction function, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(toUpdateFunction);
        try {
            //根据id本次修改的权限
            function = functionService.getFunctionById(function.getFunctionId());

            SysFunction parenFunction = new SysFunction();
            if (function.getParentFunctionId() == null || function.getParentFunctionId() == 0) {
                parenFunction = new SysFunction();
                parenFunction.setFunctionId(0L);
                parenFunction.setFunctionName("系统权限根");
            } else {
                parenFunction = functionService.getFunctionById(function.getParentFunctionId());
            }
            //所有权限
            List<SysFunction> functionList = functionService.getUsableFunctionList();
            modelAndView.addObject("parenFunction", parenFunction);
            modelAndView.addObject("functionList", gson.toJson(functionList));
            modelAndView.addObject("function", function);
        } catch (Exception e) {
            logger.error("FunctionAction.toUpdateFunction", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 修改权限
     *
     * @return
     */
    @RequestMapping("/func/updateFunction")
    public String updateFunction(@ModelAttribute("function") SysFunction function, HttpServletRequest request) {
        try {
            //修改权限
            functionService.updateFunction(function);
            cacheKit.remove(MemConstans.SYS_USER_ALL_FUNCTION);
        } catch (Exception e) {
            logger.error("FunctionAction.updateFunction", e);
            return setExceptionRequest(request, e);
        }
        return ADMIN_SUCCESS;
    }

    /**
     * 删除权限及子权限
     *
     * @return
     */
    @RequestMapping("/func/delFunctions")
    public ModelAndView delFunctions(@RequestParam("ids") List<Long> ids, HttpServletRequest request) {
        try {
            //删除权限及子权限,页面传递选中的权限数组
            //System.out.println(ids.size());
            //System.out.println(ids.get(0));
            functionService.delFunctions(ids);
            cacheKit.remove(MemConstans.SYS_USER_ALL_FUNCTION);
        } catch (Exception e) {
            logger.error("FunctionAction.delFunctions", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return new ModelAndView("redirect:/admin/func/showFunctionList");
    }

    /**
     * 页面top位置点击获取子权限列表
     *
     * @param parentId
     */
    @RequestMapping("/func/showchild")
    public String showFunctionListByParEntId(HttpServletRequest request, @RequestParam Long parentId) {
        String returnUrl = "";//用户回跳地址
        Long sonId = 0L;
        try {
            User user = getSysLoginedUser(request);

            String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
            @SuppressWarnings("unchecked")
            List<SysFunction> tabUserFunctionList = (List<SysFunction>) cacheKit.get(MemConstans.SYS_USER_FUNCTION + sid + user.getId());
            for (SysFunction sysFunction : tabUserFunctionList) {
                if (sysFunction.getParentFunctionId().equals(parentId)) {
                    sonId = sysFunction.getFunctionId();
                    break;
                }
            }
            //取出第三级返回路径地址 
            for (SysFunction sysFunction : tabUserFunctionList) {
                if (sysFunction.getParentFunctionId().equals(sonId)) {
                    returnUrl = sysFunction.getFunctionUrl();
                    break;
                }
            }
            request.getSession().setAttribute(SysUserConstants.leftTabList, processChildList(new ArrayList<SysFunction>(tabUserFunctionList), parentId));
        } catch (Exception e) {
            return setExceptionRequest(request, e);
        }
        return "redirect:" + returnUrl;
    }

    /**
     * 获取本级下的子权限
     *
     * @param funcList
     * @return
     */
    private List<List<SysFunction>> processChildList(List<SysFunction> funcList, Long parentId) {
        List<List<SysFunction>> tabList = new ArrayList<List<SysFunction>>();
        //把第一级的筛选出来（为parentId的）
        for (int i = 0; i < funcList.size(); i++) {
            SysFunction func = funcList.get(i);
            if (func.getParentFunctionId().longValue() == parentId.longValue()) {
                List<SysFunction> list = new ArrayList<SysFunction>();
                list.add(func);
                tabList.add(list);
            }
        }
        int count = funcList.size() + 1;
        while (count > funcList.size()) {
            count = funcList.size();
            for (int i = 0; i < funcList.size(); i++) {
                SysFunction func = funcList.get(i);
                for (List<SysFunction> list : tabList) {
                    for (int k = 0; k < list.size(); k++) {
                        if (list.get(k).getFunctionId().longValue() == func.getParentFunctionId().longValue()) {
                            list.add(func);
                            funcList.remove(func);
                        }
                    }
                }
            }
        }
        return tabList;
    }
}
