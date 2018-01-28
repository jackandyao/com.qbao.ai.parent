package com.qbao.ai.service.indexpage.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.qbao.ai.dto.indexpage.MenuDto;
import com.qbao.ai.model.dto.Question;
import com.qbao.ai.model.dto.QuestionDto;
import com.qbao.ai.resposity.mybatis.dao.IndexPageDao;
import com.qbao.ai.resposity.mybatis.dao.QuestionDirDao;
import com.qbao.ai.resposity.mybatis.dao.QuestionInfoDao;
import com.qbao.ai.resposity.mybatis.model.Menu;
import com.qbao.ai.resposity.mybatis.model.QuestionDir;
import com.qbao.ai.resposity.mybatis.model.QuestionInfo;
import com.qbao.ai.resposity.redis.cache.annotation.CacheType;
import com.qbao.ai.resposity.redis.cache.annotation.RedisCache;
import com.qbao.ai.service.indexpage.IIndexPageService;

/**
 * Created by shuaizhihu on 2016/12/1.
 */
@Service
public class IndexPageServiceImpl implements IIndexPageService {

    @Autowired
    IndexPageDao indexPageDao;

    @Autowired
    QuestionInfoDao questionInfoDao;

    @Autowired
    QuestionDirDao questionDirDao;


    @Override
    @RedisCache(expire = 60 * 30, clazz = MenuDto.class, cacheType = CacheType.LIST)
    public List<MenuDto> findList() {
        List<Menu> list = indexPageDao.findList();
        List<MenuDto> menuDtos = new LinkedList<MenuDto>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Menu menu : list) {
                menuDtos.add(new MenuDto().withId(menu.getId()).withDirId(menu.getDirId()).withName(menu.getName()).withIconUrl(menu.getIconUrl()).withLinkUrl(menu.getLinkUrl()));
            }
        }
        return menuDtos;
    }


    @Override
    @RedisCache(expire = 60 * 30, clazz = QuestionDto.class, cacheType = CacheType.LIST)
    public List<QuestionDto> menuDetail(Long dirId,Integer limit) {
        List<QuestionDto> dtos = Lists.newArrayList();

        try {
            if(limit==null){
                limit = 3;
            }
            List<QuestionDir> questionDirList = questionDirDao.findChildrenByPid(dirId,limit);
            if(CollectionUtils.isNotEmpty(questionDirList)){
                for(QuestionDir questionDir:questionDirList){
                    List<Question> questions = Lists.newArrayList();
                    List<QuestionInfo> questionInfoList = questionInfoDao.findListByDirIdNoPage(questionDir.getDirId());
                    if(CollectionUtils.isNotEmpty(questionInfoList)){
                        for(QuestionInfo questionInfo:questionInfoList){
                            questions.add(new Question().withId(questionInfo.getId()).withQuestion(questionInfo.getQuestion()));
                        }
                    }
                    //BeanUtils.copy(questionInfoList,questions);
                    dtos.add(new QuestionDto().withQuestions(questions).withDirId(questionDir.getDirId()).withDirName(questionDir.getDirName()));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dtos;
    }
    @Override
    @RedisCache(expire = 60 * 30, clazz = QuestionDto.class, cacheType = CacheType.LIST)
    public List<QuestionDto> getQuestionByDirId(Long dirId) {
        List<QuestionDto> dtos = Lists.newArrayList();
        try {
                    List<Question> questions = Lists.newArrayList();
                    List<QuestionInfo> questionInfoList = questionInfoDao.findListByDirIdNoPage(dirId);
                    if(CollectionUtils.isNotEmpty(questionInfoList)){
                        for(QuestionInfo questionInfo:questionInfoList){
                            questions.add(new Question().withId(questionInfo.getId()).withQuestion(questionInfo.getQuestion()));
                        }
                    }
                    QuestionDir questionDir = questionDirDao.findByDirId(dirId);
                    if(questionDir!=null){
                        dtos.add(new QuestionDto().withQuestions(questions).withDirId(dirId).withDirName(questionDir.getDirName()));
                    }else{
                        dtos.add(new QuestionDto().withQuestions(questions).withDirId(dirId));
                    }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dtos;
    }
}
