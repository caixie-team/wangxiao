package com.atdld.os.core.common.intercepter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Administrator on 2014/11/19.
 */
public class XssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("doFilter");
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
                (HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }

    @Override
    public void destroy() {
    }
}
