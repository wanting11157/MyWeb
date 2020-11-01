package com.wanting.me.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(" UserFilter 初始化....");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info(" UserFilter....");

        Date date = new Date();
        int year = date.getYear();
        if(year == 2030) {
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);//走下一个过虑器（如果后面没有过虑器了，就到拦截器，如果没有拦截器就直接到Controller
            log.info(" UserFilter....");
        }
    }

    @Override
    public void destroy() {
        log.info(" UserFilter 销毁....");
    }
}
