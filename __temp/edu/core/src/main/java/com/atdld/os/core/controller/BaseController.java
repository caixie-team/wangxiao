package com.atdld.os.core.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.Getter;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.web.WebUtils;

/**
 * 
 * @ClassName com.atdld.os.core.controller.BaseController
 * @description 通用的action.所有的Controller继承，公用的写到此方法中
 * @author :
 * @Create Date : 2013-12-13 下午2:30:00
 */
public class BaseController {

    // log对象
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    // gson日期默认格式设置
    public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    public static JsonParser jsonParser = new JsonParser();
    private PageEntity page;// 分页数据单独保存

    public Map<String, Object> json = new HashMap<String, Object>(4);

    // 公用返回路径
    public String changeSuccess = "/admin/common/success";// 修改成功提示页面

    /**
     * 封装Json返回信息
     * 
     * @param success
     *            是否操作成功
     * @param message
     *            返回辅助信息
     * @param entity
     *            返回数据时实体
     */
    public void setJson(boolean success, String message, Object entity) {
        json.put("success", success);
        json.put("message", message);
        json.put("entity", entity);
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("page")
    public void initBinderPage(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("page.");
    }

    @Getter
    @Setter
    protected String errmsg;// 错误信息

    protected static final String ERROR = "/common/error";// 前台错误信息
    protected static String ADMIN_SUCCESS = "redirect:/admin/sys/success";// 后台提交成功

    public String getUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 将错误信息放到页面中
     * 
     * @param request
     * @param e
     * @return
     */
    public String setExceptionRequest(HttpServletRequest request, Exception e) {
        logger.error(request.getContextPath(),e);
        StackTraceElement[] messages = e.getStackTrace();
        if (!ObjectUtils.isNull(messages)) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(e.toString()).append("<br/>");
            for (int i = 0; i < messages.length; i++) {
                buffer.append(messages[i].toString()).append("<br/>");
            }
            request.setAttribute("myexception", buffer.toString());
        }
        return ERROR;
    }

    /**
     * session中设置值
     * 
     * @param request
     * @param name
     * @param v
     */
    public void setSessionAttribute(HttpServletRequest request, String name, Object v) {
        request.getSession().setAttribute(name, v);
    }

    /**
     * session中取值
     * 
     * @param request
     * @param name
     * @return
     */
    public Object getSessionAttribute(HttpServletRequest request, String name) {
        HttpSession session = getSession(request, false);
        return (session != null ? session.getAttribute(name) : null);
    }

    /**
     * 取得HttpSession的简化函数.
     * 
     * @param request
     * @return HttpSession
     */
    public static HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    /**
     * 取得HttpSession的简化函数.
     * 
     * @param request
     * @return HttpSession
     */
    public static HttpSession getSession(HttpServletRequest request, boolean isNew) {
        return request.getSession(isNew);
    }

    /**
     * 发送内容到response中
     * 
     * @param request
     * @param response
     * @param content
     * @throws IOException
     */
    public void sendMessage(HttpServletRequest request, HttpServletResponse response,
            String content) throws IOException {
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(content);
        } catch (Exception e) {
            logger.error("sendMessage", e);
        }
    }

    /**
     * @return the page
     */
    public PageEntity getPage() {
        if (page == null) {
            page = new PageEntity();
        }
        return page;
    }

    /**
     * @param page
     *            the page to set
     */
    public void setPage(PageEntity page) {
        if (page != null) {
            this.page = page;
        }
    }

    /**
     * 过滤 script 脚本攻击
     * 
     * @param resquest
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    protected void replaceScript(HttpServletRequest resquest) throws Exception {
        Enumeration<String> e = resquest.getParameterNames();
        while (e.hasMoreElements()) {
            String str = (String) e.nextElement();
            String value = resquest.getParameter(str);
            if (value.indexOf("script") != -1 || value.indexOf("javascript") != -1
                    || value.indexOf("eval") != -1) {
                String ip = WebUtils.getIpAddr(resquest);
                logger.info("[疑似攻击],refer url:" + str + " 用户ip:" + ip);
                throw new Exception();
            }
        }
    }
    
    /**
     * 获取物理路径
     * @param request
     * @param path
     * @return
     */
    public  String getRealPath(HttpServletRequest request,String path){
        return request.getSession().getServletContext().getRealPath(path);
    }
    
            
}
