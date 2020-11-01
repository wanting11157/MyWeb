package com.wanting.me.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
        * preHandle是请求执行前执行的，
        * postHandler是请求结束执行的，
        * 但只有preHandle方法返回true的时候才会执行，
        * afterCompletion是视图渲染完成后才执行，同样需要preHandle返回true，
        * 该方法通常用于清理资源等工作。
        * */
        log.info("我是UserInterceptor的preHandle.....");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("我是UserInterceptor的postHandle。。。");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("我是UserInterceptor的afterCompletion。。。");
    }
}
