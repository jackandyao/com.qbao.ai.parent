package com.qbao.ai.service.ai.impl;

import com.qbao.ai.common.constant.Constant;
import com.qbao.ai.model.dto.QuestionLinkDto;
import com.qbao.ai.resposity.mybatis.dao.QuestionLinkDao;
import com.qbao.ai.resposity.mybatis.model.QuestionLink;
import com.qbao.ai.service.ai.IBaseAnswerService;
import com.qbao.ai.util.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author liaijun
 * @createTime 17/4/14 下午2:28
 * $$LastChangedDate: 2017-05-23 11:01:30 +0800 (Tue, 23 May 2017) $$
 * $$LastChangedRevision: 233 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class GoodsListService implements IBaseAnswerService{
    Logger logger= Logger.getLogger(GoodsListService.class);
    @Autowired
    private QuestionLinkDao linkDao;

    @Override
    public Map<String,Object> answerInfo(String question, int device, String lat, String lon, Long userId, int page, int size,Set<String> set) {
        QuestionLink link=linkDao.findQuestionLinkById(30L);
        QuestionLinkDto dto=new QuestionLinkDto();
        BeanUtils.copy(link,dto);
        dto.setValue(link.getName());
//        List<QuestionLinkDto> dtos=new ArrayList<>();
//        dtos.add(dto);
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
        response.put("questionType","normal");
        response.put("title","小智给您找到的答案");
        response.put("success", true);
        response.put("message", "Ok");
        response.put("userId",userId);
        response.put("data",dto);
        return response;
    }
}
