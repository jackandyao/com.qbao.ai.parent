package com.qbao.ai.service.ai.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.ai.resposity.mybatis.dao.QuestionLinkDao;
import com.qbao.ai.resposity.mybatis.model.QuestionLink;
import com.qbao.ai.resposity.redis.cache.annotation.CacheType;
import com.qbao.ai.resposity.redis.cache.annotation.RedisCache;
import com.qbao.ai.service.ai.IQuestionLinkService;

/**
 * Created by shuaizhihu on 2016/12/2.
 */
@Service
public class QuestionLinkService implements IQuestionLinkService{

    @Autowired
    QuestionLinkDao questionLinkDao;

    @Override
    @RedisCache(expire = 60 * 30, clazz = QuestionLink.class, cacheType = CacheType.LIST)
    public List<QuestionLink> findList(String linkIds) {
        if(StringUtils.isEmpty(linkIds)){
            return new ArrayList<QuestionLink>();
        }
         return  questionLinkDao.findByLinkIds(linkIds);
    }
}
