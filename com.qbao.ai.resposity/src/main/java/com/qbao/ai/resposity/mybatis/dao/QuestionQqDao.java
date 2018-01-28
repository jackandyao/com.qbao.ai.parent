package com.qbao.ai.resposity.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qbao.ai.resposity.mybatis.annotation.DataSource;
import com.qbao.ai.resposity.mybatis.model.QuestionQq;
import com.qbao.ai.resposity.redis.cache.annotation.CacheType;
import com.qbao.ai.resposity.redis.cache.annotation.RedisCache;

/**
 * Created by shuaizhihu on 2016/12/1.
 */
@Repository
public interface QuestionQqDao {

	@RedisCache(expire = 60 * 30, clazz = QuestionQq.class, cacheType = CacheType.LIST)
    @DataSource("aiDataSource")
    @Select("select * from question_qq a where status=1")
    @ResultMap("QuestionQqMap")
    List<QuestionQq> findAllQQ();



}
