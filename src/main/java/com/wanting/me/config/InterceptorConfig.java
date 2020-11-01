package com.wanting.me.config;

import com.wanting.me.interceptor.CourseInterceptor;
import com.wanting.me.interceptor.ScoreInterceptor;
import com.wanting.me.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/user/*");
        registry.addInterceptor(new CourseInterceptor()).addPathPatterns("/course/*");
        registry.addInterceptor(new ScoreInterceptor()).addPathPatterns("/score/*");
        //super.addInterceptors(registry);
    }
}
