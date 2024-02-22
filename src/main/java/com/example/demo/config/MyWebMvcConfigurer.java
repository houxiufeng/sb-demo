package com.example.demo.config;

import com.example.demo.interceptor.CustomHadlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * https://cloud.tencent.com/developer/article/1818338
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private CustomHadlerInterceptor customHadlerInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customHadlerInterceptor).addPathPatterns("/*");
    }
}
