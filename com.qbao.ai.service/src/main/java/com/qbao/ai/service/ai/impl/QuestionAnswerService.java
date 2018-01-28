package com.qbao.ai.service.ai.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.ai.common.constant.Constant;
import com.qbao.ai.model.dto.QuestionDto;
import com.qbao.ai.resposity.common.Page;
import com.qbao.ai.resposity.redis.cache.annotation.CacheType;
import com.qbao.ai.resposity.redis.cache.annotation.RedisCache;
import com.qbao.ai.resposity.solr.document.Question;
import com.qbao.ai.service.ai.IBaseAnswerService;
import com.qbao.ai.service.ai.IQuestionInfoService;

/**
 * @author liaijun
 * @createTime 17/4/14 下午2:28
 * $$LastChangedDate: 2017-06-01 11:28:22 +0800 (Thu, 01 Jun 2017) $$
 * $$LastChangedRevision: 238 $$
 * $$LastChangedBy: zhangjun $$
 */
@Service
public class QuestionAnswerService implements IBaseAnswerService{
    Logger logger= Logger.getLogger(QuestionAnswerService.class);
    @Autowired
    private IQuestionInfoService questionInfoService;
    @Autowired
    private BaiduListService baiduListService;
    @Autowired
    private CustomService customService;

    @Override
    public Map<String,Object> answerInfo(String question, int device, String lat, String lon, Long userId, int page, int size,Set<String> set) {
        List<QuestionDto> dto=search(question,page,size);
        if(CollectionUtils.isNotEmpty(dto)) {
            Map<String, Object> response = new HashMap<String, Object>();
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("questionType", "q&a");
            response.put("title", "小智给您找到的相关问题");
            response.put("success", true);
            response.put("message", "Ok");
            response.put("userId", userId);
            response.put("data", dto);
            return response;
        }else{
            return baiduListService.answerInfo(question,userId,page,size);
            //return customService.answerInfo( question,  device,  lat,  lon,  userId,  page,  size,null);
        }
    }



    @RedisCache(expire = 60 * 30, clazz = QuestionDto.class, cacheType = CacheType.LIST)
    public List<QuestionDto> search(String question,int page,int size){
        List<QuestionDto> dataDtoList = new ArrayList<QuestionDto>();
        Page<Question> result = questionInfoService.search(question,page,size);
        Map<Long,QuestionDto> map=  new HashMap<Long,QuestionDto>();
        List<Question> list=result.getItems();
        if(CollectionUtils.isNotEmpty(list)){
            for(Question q:list){
                if(map.get(q.getDirId())==null){
                    com.qbao.ai.model.dto.Question tion=new com.qbao.ai.model.dto.Question();
                    List<com.qbao.ai.model.dto.Question> qlist=new ArrayList<>();
                    tion.setId(q.getId());
                    tion.setQuestion(q.getQuestion());
                    qlist.add(tion);
                    QuestionDto dto=new QuestionDto();
                    dto.setDirName(q.getDirName());
                    dto.setDirId(q.getDirId());
                    dto.setQuestions(qlist);
                    map.put(q.getDirId(),dto);
                    dataDtoList.add(dto);
                }else{
                    QuestionDto dto=map.get(q.getDirId());
                    List<com.qbao.ai.model.dto.Question> qlist=dto.getQuestions();
                    com.qbao.ai.model.dto.Question tion=new com.qbao.ai.model.dto.Question();
                    tion.setId(q.getId());
                    tion.setQuestion(q.getQuestion());
                    qlist.add(tion);
                }

            }
        }
        return dataDtoList;
    }
}
