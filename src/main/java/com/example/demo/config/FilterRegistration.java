package com.example.demo.config;

import com.example.demo.filter.AnotherFilter;
import com.example.demo.filter.CustomFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistration {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setName("customFilter");
        registration.setFilter(new CustomFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(10);
        return registration;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean2() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setName("anotherFilter");
        registration.setFilter(new AnotherFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(9);
        return registration;
    }
}
