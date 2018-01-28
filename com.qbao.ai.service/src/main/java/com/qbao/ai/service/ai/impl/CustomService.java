package com.qbao.ai.service.ai.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.ai.common.constant.Constant;
import com.qbao.ai.model.dto.QuestionQqDto;
import com.qbao.ai.resposity.mybatis.dao.QuestionQqDao;
import com.qbao.ai.resposity.mybatis.model.QuestionQq;
import com.qbao.ai.service.ai.IBaseAnswerService;
import com.qbao.ai.util.BeanUtils;

/**
 * @author liaijun
 * @createTime 17/4/14 下午2:28
 * $$LastChangedDate: 2017-05-23 11:01:30 +0800 (Tue, 23 May 2017) $$
 * $$LastChangedRevision: 233 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class CustomService implements IBaseAnswerService{
    Logger logger= Logger.getLogger(CustomService.class);
    @Autowired
    private QuestionQqDao qqDao;



    @Override
    public Map<String,Object> answerInfo(String question, int device, String lat, String lon, Long userId, int page, int size,Set<String> set) {
        List<QuestionQq> qq=qqDao.findAllQQ();
        List<QuestionQqDto>  data=BeanUtils.mapList(qq,QuestionQqDto.class);

        Map<String,Object> response=new HashMap<String,Object>();
        response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
        response.put("data",data);
        response.put("questionType","customService");
        response.put("title","亲,小智不明白您的问题哦,您可以联系客服~");
        response.put("success", true);
        response.put("message", "Ok");
        return response;
    }
}
