package com.qbao.filter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbao.security.CasUser;

/**
 * Created by liuwang on 2016/7/5.
 */
public class ControllerMethodInterceptor implements MethodInterceptor {
    private final static Logger LOG = LoggerFactory.getLogger(ControllerMethodInterceptor.class);
    private final static ObjectMapper jsonMapper = new ObjectMapper();

    /**
     * Controller返回为AjaxResult时，只有这里拦截并打印
     * @param methodInvocation
     * @return
     * @throws Throwable
     */
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        LOG.info("ControllerMethodInterceptor-invoke():methodInvocation:{}", methodInvocation);
        Object result=methodInvocation.proceed();
        LOG.info("ControllerMethodInterceptor-invoke():result:{}", result);
        String clazzName = methodInvocation.getMethod().getDeclaringClass().getName();
        String indexInfo = clazzName.substring(clazzName.lastIndexOf(".") + 1) + "." + methodInvocation.getMethod().getName();
        LOG.info("ControllerMethodInterceptor-invoke():indexInfo:{}", indexInfo);
        SecurityContext context = SecurityContextHolder.getContext();
        LOG.info("ControllerMethodInterceptor-invoke():context:{}", context);
        if (context != null){
            Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof CasUser) {
                    CasUser cu=(CasUser) principal;
                    LOG.info("[{}]- userId: {}, userName: {}, mobile: {}, ResultSet:{}", indexInfo,cu.getUserId(),cu.getUserName(),cu.getMobile(),jsonMapper.writeValueAsString(result));
                }
            }
        }else {
            LOG.info("[{}]- userId: {}, userName: {}, mobile: {},ResultSet:{}", indexInfo,0,"","",jsonMapper.writeValueAsString(result));
        }
        return result;
    }
}
