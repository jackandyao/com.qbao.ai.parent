package com.qbao.ai.resposity.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qbao.ai.resposity.mybatis.annotation.DataSource;
import com.qbao.ai.resposity.mybatis.model.QuestionLink;
import com.qbao.ai.resposity.redis.cache.annotation.CacheType;
import com.qbao.ai.resposity.redis.cache.annotation.RedisCache;

/**
 * Created by shuaizhihu on 2016/12/1.
 */
@Repository
public interface QuestionLinkDao {

	@RedisCache(expire = 60 * 30, clazz = QuestionLink.class, cacheType = CacheType.LIST)
    @DataSource("aiDataSource")
    @Select("select * from question_link where id  in ( ${linkIds} )")
    @ResultMap("QuestionLinkMap")
    List<QuestionLink> findByLinkIds(@Param("linkIds") String linkIds);


    @RedisCache(expire = 60 * 30, clazz = QuestionLink.class, cacheType = CacheType.OBJECT)
    @DataSource("aiDataSource")
    @Select("select * from question_link where id  =  ${id} ")
    @ResultMap("QuestionLinkMap")
    QuestionLink findQuestionLinkById(@Param("id") Long id);
}
