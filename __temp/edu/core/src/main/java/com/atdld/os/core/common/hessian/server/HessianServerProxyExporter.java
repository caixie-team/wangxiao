package com.atdld.os.core.common.hessian.server;

import com.atdld.os.core.util.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.caucho.HessianServiceExporter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName HessianExporter
 * @package com.fairyhawk.service.hessian
 * @description 服务端代理。判断请求是否合法
 * @author
 * @Create Date: 2013-3-14 下午5:17:27
 * 
 */
public class HessianServerProxyExporter extends HessianServiceExporter {

    private static Logger logger = LoggerFactory.getLogger(HessianServerProxyExporter.class);

    // 获取server端配置文件
    PropertyUtil propertyUtil = PropertyUtil.getInstance("project");
    public String hessianAuth = propertyUtil.getProperty("hessianAuth");

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //response.getWriter().print("Welcome Hessian!!!");
        logger.info("********************Welcome Hessian!!!");
        logger.info("++++ hessian request clientIp:" + request.getRemoteAddr()// 请求IP
                + "++++requestData:" + request.getRequestURL());// 目标应用路径
        String auth = request.getHeader("hessianAuth");
        if (auth == null || !auth.equalsIgnoreCase(hessianAuth)) {
            // 记录异常 非法请求 日志
            logger.info("+++++hessianAuth->fail :" + request.getRemoteAddr() + ","
                    + request.getRequestURL());
            return;
        }
        super.handleRequest(request, response);

    }

}
