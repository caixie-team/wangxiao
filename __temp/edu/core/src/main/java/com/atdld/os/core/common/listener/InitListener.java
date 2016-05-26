package com.atdld.os.core.common.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.atdld.os.core.util.PropertyUtil;

/**
 * @ClassName com.atdld.os.ssicore.common.InitListener
 * @description
 * @author :
 * @Create Date : 2014-4-15 下午2:29:08
 */
public class InitListener extends ContextLoaderListener {

    public InitListener() {
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try{
            PropertyUtil propertyUtil = PropertyUtil.getInstance("project");
            String contextPath = propertyUtil.getProperty("contextPath");
            if(contextPath.indexOf("127.0.")>-1 ||contextPath.indexOf("192.168.")>-1  ){
                super.contextInitialized(servletContextEvent);
                return;
            }
        }catch (Exception e1){
        }

    }

}