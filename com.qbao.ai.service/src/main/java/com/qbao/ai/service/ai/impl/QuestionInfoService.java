package com.qbao.ai.service.ai.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qbao.ai.model.dto.QuestionDirDto;
import com.qbao.ai.model.dto.QuestionInfoDto;
import com.qbao.ai.model.dto.QuestionInfoIndexDto;
import com.qbao.ai.model.dto.QuestionLinkDto;
import com.qbao.ai.model.dto.QuestionLinkIndexDto;
import com.qbao.ai.model.dto.QuestionSimIndexDto;
import com.qbao.ai.resposity.common.Page;
import com.qbao.ai.resposity.mybatis.dao.QuestionDirDao;
import com.qbao.ai.resposity.mybatis.dao.QuestionInfoDao;
import com.qbao.ai.resposity.mybatis.dao.QuestionLinkDao;
import com.qbao.ai.resposity.mybatis.model.QuestionDir;
import com.qbao.ai.resposity.mybatis.model.QuestionInfo;
import com.qbao.ai.resposity.mybatis.model.QuestionLink;
import com.qbao.ai.resposity.redis.cache.annotation.CacheType;
import com.qbao.ai.resposity.redis.cache.annotation.RedisCache;
import com.qbao.ai.resposity.solr.biz.QuestionSearchBiz;
import com.qbao.ai.resposity.solr.document.Question;
import com.qbao.ai.service.ai.IQuestionInfoService;
import com.qbao.ai.util.BeanUtils;

/**
 * Created by shuaizhihu on 2016/12/1.
 */
@Service
public class QuestionInfoService implements IQuestionInfoService{

    @Autowired
    QuestionInfoDao questionInfoDao;

    @Autowired
    QuestionSearchBiz questionSearchBiz;
    @Autowired
    QuestionDirDao questionDirDao;
    @Autowired
    QuestionLinkDao questionLinkDao;
    @Override
    @RedisCache(expire = 60 * 30, clazz = QuestionInfo.class, cacheType = CacheType.PAGE)
    public Page<QuestionInfo> findPageByDirId(long dirId, int page, int size) {
        int start = (page-1)*size;
        int rows = size;
        List<QuestionInfo> list = questionInfoDao.findListByDirId(dirId,start,rows);
        int total = questionInfoDao.findTotalByDirId(dirId);
        return new Page<QuestionInfo>(total,page,size,list);
    }

    @Override
    @RedisCache(expire = 60 * 30, clazz = QuestionInfo.class, cacheType = CacheType.PAGE)
    public Page<QuestionInfo> findPageByRootDirId(long rootDirId, int page, int size) {
        int start = (page-1)*size;
        int rows = size;
        List<QuestionInfo> list = questionInfoDao.findListByRootDirId(rootDirId,start,rows);
        int total = questionInfoDao.findTotalByRootDirId(rootDirId);
        return new Page<QuestionInfo>(total,page,size,list);
    }
    @Override
    @RedisCache(expire = 60 * 30, clazz = QuestionDirDto.class, cacheType = CacheType.OBJECT)
    public QuestionDirDto findQuestionInfoByRootDirId(long rootDirId, int page, int size) {
        int start = (page-1)*size;
        int rows = size;
        List<QuestionInfo> list = questionInfoDao.findListByRootDirId(rootDirId,start,rows);
        //int total = questionInfoDao.findTotalByRootDirId(rootDirId);
        List<QuestionDir> questionDirList=questionDirDao.findChildren(rootDirId);
        QuestionDirDto questionDirDto=new QuestionDirDto();
        if(!CollectionUtils.isEmpty(questionDirList)){
            QuestionDir questionDir=questionDirList.get(0);
            questionDirDto.setDirId(questionDir.getDirId());
            questionDirDto.setDirName(questionDir.getDirName());
        }
        List<QuestionInfoDto> infoDtos=new ArrayList<QuestionInfoDto>();
        for(QuestionInfo info:list){
            QuestionInfoDto infoDto=new QuestionInfoDto();
            BeanUtils.copy(info,infoDto);
            infoDtos.add(infoDto);
        }
        questionDirDto.setQuestions(infoDtos);
        return questionDirDto;
    }
    @Override
    @RedisCache(expire = 60 * 30, clazz = QuestionInfo.class, cacheType = CacheType.OBJECT)
    public QuestionInfo findById(long id) {
        return  questionInfoDao.findById(id);
    }

    @Override
    @RedisCache(expire = 60 * 30, clazz = Question.class, cacheType = CacheType.PAGE)
    public Page<Question> search(String kw, int page, int size) {
        return  questionSearchBiz.searchQuestions(kw,page,size);
    }
    //获取具体问题
    @RedisCache(expire = 60 * 30, clazz = QuestionInfoIndexDto.class, cacheType = CacheType.OBJECT)
    public QuestionInfoIndexDto getQuestionInfo( long userId,long id) {

            QuestionInfo info = findById(id);
            QuestionInfoIndexDto dto = BeanUtils.map(info,QuestionInfoIndexDto.class);
            List<QuestionLink> links =new ArrayList<QuestionLink>();
            String linkIds=info.getLinkIds();
            if(StringUtils.isNotBlank(linkIds)) {
                links = questionLinkDao.findByLinkIds(linkIds);
            }
            dto.setLinks(BeanUtils.mapList(links, QuestionLinkIndexDto.class));

            List<QuestionSimIndexDto> simList = new  ArrayList<QuestionSimIndexDto>();
            Page<Question> page = search(info.getQuestion(), 1, 5);
            simList = BeanUtils.mapList(page.getItems(), QuestionSimIndexDto.class);
            for (QuestionSimIndexDto simdto : simList) {
                if (simdto.getId() == info.getId()) {
                    simList.remove(simdto);
                    break;
                }
            }
            dto.setSimQustions(simList);

        return dto;
    }

    //获取具体问题
    public QuestionLinkDto getQuestionInfoAnswer( long userId,long id) {

        QuestionInfo info = findById(id);
        QuestionLinkDto dto =new QuestionLinkDto();
        dto.setValue(info.getAnswer());
        return  dto;
    }
}
