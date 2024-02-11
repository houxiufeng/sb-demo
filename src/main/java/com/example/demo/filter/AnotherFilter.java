package com.example.demo.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class AnotherFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("AnotherFilter init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("AnotherFilter doFilter pre---------------");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("AnotherFilter doFilter after---------------");
    }

    @Override
    public void destroy() {
        log.info("AnotherFilter destroy");
        Filter.super.destroy();
    }
}
