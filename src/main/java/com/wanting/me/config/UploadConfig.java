package com.wanting.me.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class UploadConfig {

    @Value("${upload.temp}")
    private String uploadTemp;

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setLocation("/upload/temp/");
        factory.setLocation(uploadTemp);
        return factory.createMultipartConfig();
    }



}
