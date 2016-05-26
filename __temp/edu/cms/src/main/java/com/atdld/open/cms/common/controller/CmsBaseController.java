package com.atdld.open.cms.common.controller;

import com.atdld.open.common.util.DateEditor;
import com.atdld.open.core.controller.BaseController;
import com.atdld.open.core.service.cache.MemCache;
import com.atdld.open.core.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;

/**
 * @ClassName com.atdld.open.line.common.LineBaseController
 * @description
 */
public class CmsBaseController extends BaseController {
    protected static final String LINE_VIEW_PATH = "cms";// line的view路径
    static MemCache memCache = MemCache.getInstance();
    protected static final String msgjsp = ("/common/frontmsg");

    /**
     * 返回edu的view路径
     */
    public static String getViewPath(String path) {
        if (StringUtils.isNotEmpty(path)) {
            return "/" + LINE_VIEW_PATH + path;
        }
        return "";
    }

    
    /** spring接受date类型转换
     */
    @InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

    
    /***
     * 获取请求路径
     * @param request
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
	public String getRequestUrl(HttpServletRequest request) throws Exception{
    	StringBuffer value = new StringBuffer();
    	String uri = request.getRequestURI();
    	value.append(uri).append("?");
    	Enumeration<String> emun = request.getParameterNames();
    	while(emun.hasMoreElements()){
    		String paramName = emun.nextElement();
    		String paramValue = request.getParameter(paramName);
    		paramValue = URLEncoder.encode(paramValue, "UTF-8");
    		value.append(paramName).append("=").append(paramValue).append("^");
    	}
    	return value.toString();
    }
    
    /**
     * 处理返回路径 用于删除和修改图片时返回当前所在的页面及查询条件
     * @param path
     * @return 返回处理过后的路径
     * @throws Exception
     */
    public String handleUrl(String path) throws Exception{
    	String newPath="";
    	if(path!=null){
    		if(path.indexOf("^")!=-1){
    			path = path.replaceAll("\\^", "\\&");
    		}
    		String[] arr = path.split("\\?");
    		newPath+=arr[0];
    		if(arr.length>1){
    			String[] val = arr[1].split("\\&");
    			if(val.length > 0){
    				newPath+="?";
    				for(String str : val){
    					String[] kv = str.split("=");
    					if(kv.length>1){
    						newPath+=(kv[0]+"="+URLEncoder.encode(kv[1], "UTF-8")+"&");
    					}else{
    						newPath+=kv[0]+"=&";
    					}
    				}
    			}
    		}
    	}
    	return newPath;
    }
}
