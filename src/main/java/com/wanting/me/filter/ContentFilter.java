package com.wanting.me.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ContentFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(" ContentFilter 初始化....");
        //项目必须有哪些目录，images ---   file---

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //    /user/update
        log.info(" ContentFilter doFilter  ....");
        log.info(" ContentFilter执行前....");
        //走下一个过虑器（如果后面没有过虑器了，就到拦截器，如果没有拦截器就直接到Controller
        filterChain.doFilter(request, response);
        log.info(" ContentFilter执行完成....");

    }

    @Override
    public void destroy() {
        log.info(" ContentFilter 销毁....");
    }
}
