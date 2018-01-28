package com.qbao.ai.service.ai.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.ai.resposity.common.Page;
import com.qbao.ai.resposity.mybatis.dao.QuestionDirDao;
import com.qbao.ai.resposity.mybatis.model.QuestionDir;
import com.qbao.ai.resposity.redis.cache.annotation.CacheType;
import com.qbao.ai.resposity.redis.cache.annotation.RedisCache;
import com.qbao.ai.service.ai.IQuestionDirService;

/**
 * Created by shuaizhihu on 2016/12/1.
 */
@Service
public class QuestionDirService  implements IQuestionDirService{
    @Autowired
    QuestionDirDao questionDirDao;
    @Override
    @RedisCache(expire = 60 * 30, clazz = QuestionDir.class, cacheType = CacheType.PAGE)
    public Page<QuestionDir> findByLev(int lev, int page, int size) {
        int start = (page-1)*size;
        int rows = size;
        List<QuestionDir> list = questionDirDao.findListByLev(lev,start,rows);
        int total = questionDirDao.findTotalByLev(lev);
        return new Page<QuestionDir>(total,page,size,list);
    }
}
