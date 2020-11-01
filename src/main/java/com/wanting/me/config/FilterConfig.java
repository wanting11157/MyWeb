package com.wanting.me.config;

import com.wanting.me.filter.ContentFilter;
import com.wanting.me.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {


    @Bean
    public FilterRegistrationBean registFilter2() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new UserFilter());
//        registration.addUrlPatterns("/(user|course)/*");
        registration.addUrlPatterns("/*");
        registration.setName("UserFilter");
        registration.setOrder(2);
        return registration;
    }

    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ContentFilter());
        registration.addUrlPatterns("/*");
        registration.setName("ContentFilter");
        registration.setOrder(1);
        return registration;
    }







}
