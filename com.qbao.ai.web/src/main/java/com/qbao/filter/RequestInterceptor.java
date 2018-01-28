package com.qbao.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Created by wangmao on 2016/7/4.
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {
    private final static Logger LOG = LoggerFactory.getLogger(RequestInterceptor.class);

    /**
     * 限制请求
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return true;

    }
}
