package co.bluepx.edu.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 记录每个请求的运行时长
 */
public class RequestExcutionTimeFilter implements Filter {
    public FilterConfig filterConfig = null;
    private static final Logger log = LoggerFactory.getLogger(RequestExcutionTimeFilter.class);
    /**
     * 最大执行时间，秒为单位
     */
    private static final int MAX_TIME = 15;

    public void destroy() {
        filterConfig = null;
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
        long now = System.currentTimeMillis();
        arg2.doFilter(arg0, arg1);
        if (log.isDebugEnabled()) {
            log.debug("********************** " + ((HttpServletRequest) arg0).getRequestURL() + "  " + (System.currentTimeMillis() - now));
        }
        //处理执行时间过长的controller
        longController(System.currentTimeMillis() - now, ((HttpServletRequest) arg0).getRequestURL().toString());
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    /**
     * 处理执行时间过长的控制层
     *
     * @param time
     * @param url
     */
    private void longController(long time, String url) {
        if (time > MAX_TIME * 1000) {
            log.error("execution long time : url is " + url + ", time is " + time + " millisecond");
        }
    }

}
