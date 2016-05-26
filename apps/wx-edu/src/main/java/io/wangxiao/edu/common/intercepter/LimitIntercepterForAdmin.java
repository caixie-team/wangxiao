package io.wangxiao.edu.common.intercepter;

import com.google.gson.JsonObject;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.sysuser.constants.SysUserConstants;
import io.wangxiao.edu.sysuser.entity.QueryFunctionCondition;
import io.wangxiao.edu.sysuser.entity.SysFunction;
import io.wangxiao.edu.sysuser.service.FunctionService;
import io.wangxiao.edu.sysuser.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LimitIntercepterForAdmin
 * @description 后台用户拦截器
 */
public class LimitIntercepterForAdmin extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(LimitIntercepterForAdmin.class);
    CacheKit cacheKit = CacheKit.getInstance();

    @Autowired
    private FunctionService functionService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean flag = super.preHandle(request, response, handler);
        // 访问的路径
        String invokeUrl = request.getContextPath() + request.getServletPath();
        if (!"1".equals(CommonConstants.l)) {
            return false;
        }
        boolean handflag = false;
        boolean checkUrl = false;
        Long parentId = 0L;
        JsonObject user = SingletonLoginUtils.getLoginUser(request);
        if (ObjectUtils.isNotNull(user)) {//已经登录了验证权限
            flag = true;
            // 取网站的所有权限
            List<SysFunction> functionList = (List<SysFunction>) cacheKit.get(MemConstans.SYS_USER_ALL_FUNCTION);
            if (ObjectUtils.isNull(functionList)) {
                functionList = functionService.getFunctionList(new QueryFunctionCondition());
                cacheKit.set(MemConstans.SYS_USER_ALL_FUNCTION, functionList, MemConstans.SYS_USER_ALL_FUNCTION_TIME);
            }
            String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
            // 访问路径在权限管理中时再做验证,取当前登录用户的权限
            List<SysFunction> tabUserFunctionList = (List<SysFunction>) cacheKit.get(MemConstans.SYS_USER_FUNCTION + sid + user.get("id").getAsString());
            if (ObjectUtils.isNull(tabUserFunctionList)) {
                tabUserFunctionList = sysUserService.getUserFunction(user.get("id").getAsLong());
                cacheKit.set(MemConstans.SYS_USER_FUNCTION + sid + user.get("id").getAsString(), tabUserFunctionList, MemConstans.SYS_USER_TIME);
            }
            //判断当前访问的权限，是否在限制中
            boolean hasFunction = false;
            if (ObjectUtils.isNotNull(tabUserFunctionList)) {
                for (SysFunction function : functionList) {
                    if (function.getFunctionUrl() != null && function.getFunctionUrl().indexOf(invokeUrl) != -1) {
                        hasFunction = true;
                        break;
                    }
                }
                List<SysFunction> topTabList = processTopTabUser(new ArrayList<SysFunction>(tabUserFunctionList));
                //拦截存储（头部权限）
                request.setAttribute(SysUserConstants.topTabList, topTabList);
                if (!hasFunction) {// 如果权限中不包含此url,代表不对此url限制，返回成功
                    return true;
                }

                //获取二级权限
                for (SysFunction function : functionList) {
                    if (function.getFunctionUrl().equals(invokeUrl)) {
                        parentId = function.getParentFunctionId();
                        break;
                    }
                }
                //获取一级权限
                for (SysFunction function : functionList) {
                    if (parentId == function.getFunctionId().longValue()) {
                        parentId = function.getParentFunctionId();
                        checkUrl = true;
                        break;
                    }
                }

                if (checkUrl) {
                    //存储二级左侧权集
                    request.setAttribute(SysUserConstants.leftTabList, processChildList(new ArrayList<SysFunction>(tabUserFunctionList), parentId));
                }
                if (ObjectUtils.isNotNull(tabUserFunctionList)) {
                    for (SysFunction function : tabUserFunctionList) {
                        if (function != null && function.getFunctionUrl().contains(invokeUrl)) {
                            handflag = true;
                            break;
                        }
                    }
                }
            }
            if (handflag) {
                logger.info("++ user action pass  userName:" + user + ",invokeUrl:" + invokeUrl);
                flag = true;
            } else {
                logger.info("++ user want access limit url userName:" + user + ",invokeUrl:" + invokeUrl);
                response.sendRedirect("/limitVerifyError");// 跳转权限受限制页面
                flag = false;
            }
        } else {
            logger.info("++ admin intercpter user not login:");
            response.sendRedirect("/admin/sys/login");// 未登录状态跳转到登录页面
            flag = false;
        }

        return flag;
    }

    /**
     * 获取第一级的权限
     *
     * @param funcList
     * @return
     */
    private List<SysFunction> processTopTabUser(List<SysFunction> funcList) {
        List<SysFunction> tabList = new ArrayList<SysFunction>();
        // 把第一级的筛选出来（parentId=0的）
        for (int i = 0; i < funcList.size(); i++) {
            SysFunction func = funcList.get(i);
            if (func.getParentFunctionId().longValue() == 0) {
                tabList.add(func);
            }
        }
        return tabList;
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
                ArrayList<SysFunction> list = new ArrayList<SysFunction>();
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

