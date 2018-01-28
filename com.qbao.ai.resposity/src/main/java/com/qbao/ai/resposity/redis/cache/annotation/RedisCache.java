/**
 * 
 */
package com.qbao.ai.resposity.redis.cache.annotation;

import com.qbao.ai.resposity.redis.cache.annotation.CacheType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shuaizhihu
 *
 * $LastChangedDate: 2016-09-05 18:26:32 +0800 (Mon, 05 Sep 2016) $
 * $LastChangedRevision: 896 $
 * $LastChangedBy: jiahongping $
 */
@Documented  
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME) 
public @interface RedisCache {
    //缓存时间，默认60秒  
    int expire() default 60;  
    
    //缓存实体类型  
    Class<?> clazz();   
    
    //缓存中存放的具体类型  
    CacheType cacheType();
}
