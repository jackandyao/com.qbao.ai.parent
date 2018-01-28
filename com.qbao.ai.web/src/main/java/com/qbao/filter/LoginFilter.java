package com.qbao.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Created by DN0806 on 2016/5/10.
 */
public class LoginFilter extends OncePerRequestFilter {

    private final static Logger LOG = LoggerFactory.getLogger(LoginFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LOG.info("*********LoginFilter.doFilterInternal begin**********");
        LOG.info("*********getRequestURI**********" + request.getRequestURI());
        if ("/api/account4Client/login".equals(request.getRequestURI())) {
            request.getRequestDispatcher("/api/account4Client/login").forward(request, response);
        } else if(request.getRequestURI().contains("/stuff/service/switch.do")){
            request.getRequestDispatcher("/stuff/service/switch.do").forward(request, response);
        }
        else {
            filterChain.doFilter(request, response);
        }
    }
}
