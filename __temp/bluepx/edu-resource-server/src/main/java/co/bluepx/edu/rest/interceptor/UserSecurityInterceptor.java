package io.wangxiao.api.rest.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bison on 1/8/16.
 *
 */
public class UserSecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getHeader("tocken");
//        JWTVerifier verifier = new JWTVerifier(BluepxConstants.SECRET);
//        try {
//            验证token是否有效
//            verifier.verify(token);
//        } catch (Exception e) {
//            throw new WebException(HttpStatus.UNAUTHORIZED);
//        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
