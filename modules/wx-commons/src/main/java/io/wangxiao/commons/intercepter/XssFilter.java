package io.wangxiao.commons.intercepter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
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
