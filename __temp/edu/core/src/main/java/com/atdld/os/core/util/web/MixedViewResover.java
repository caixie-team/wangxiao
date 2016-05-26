package com.atdld.os.core.util.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 2014/11/27.
 */
public class MixedViewResover implements ViewResolver{

    @Getter
    @Setter
    private Map<String, ViewResolver> resolvers;

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        int redirectIndex=viewName.indexOf("redirect:");
        String redirecStr="";
        if(redirectIndex>-1){
            redirecStr="redirect:";
            viewName=viewName.replaceAll("redirect:","");
        }
        int n = viewName.lastIndexOf(":");
        String suffix="jsp";//默认的根据自己项目修改
        if (n >-1){
            String [] ars=viewName.split(":");
            suffix = ars[0];
            viewName= ars[1];
        }
        viewName=redirecStr+viewName;
        ViewResolver resolver = resolvers.get(suffix);
        if (resolver != null){
            return resolver.resolveViewName(viewName, locale);
        }
        return null;
    }
}
