/**
 *
 */
package com.qbao.ai.resposity.mybatis.datasource;

import com.qbao.ai.resposity.mybatis.annotation.DataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author shuaizhihu
 *
 * $LastChangedDate: 2016-08-12 10:24:49 +0800 (周五, 12 八月 2016) $
 * $LastChangedRevision: 84 $
 * $LastChangedBy: shuaizhihu $
 */
@Component
@Aspect
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {
    private  Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);
   // @Before("execution(* com.qbao.ai.resposity.mybatis.dao..*.*(..))")
    @Before("execution(* com.qbao.ai.resposity.mybatis.dao..*.*(..))")
    public void before(JoinPoint point) {
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?>[] classz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        logger.debug("aop before() ...");

        try {
            Method m = classz[0].getMethod(method, parameterTypes);
            logger.debug("AOP Method source =" + m.getName());
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource data = m.getAnnotation(DataSource.class);
                MultipleDataSource.setDataSourceKey(data.value());
                logger.debug("data source=" + data.value());
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @After("execution(* com.qbao.ai.resposity.mybatis.dao..*.*(..))")
    public void after(JoinPoint joinPoint) {
        logger.debug("aop after() ...");
        MultipleDataSource.removeDataSourceKey();
    }
}