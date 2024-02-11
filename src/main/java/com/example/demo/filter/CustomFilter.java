package com.example.demo.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("CustomFilter init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("CustomFilter doFilter pre---------------");
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
        servletResponse.setCharacterEncoding("UTF-8");
        log.info("CustomFilter doFilter after---------------");
    }

    @Override
    public void destroy() {
        log.info("CustomFilter destroy");
        Filter.super.destroy();
    }
}
