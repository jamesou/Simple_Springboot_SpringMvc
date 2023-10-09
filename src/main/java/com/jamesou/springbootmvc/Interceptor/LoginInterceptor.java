package com.jamesou.springbootmvc.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jamesou
 * @create 2020-04-06 14:11
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginuser = request.getSession().getAttribute("loginuser");
        if(loginuser==null){
            request.setAttribute("msg","please login in first");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }else{
            return true;
        }
        // return true;
    }
}
