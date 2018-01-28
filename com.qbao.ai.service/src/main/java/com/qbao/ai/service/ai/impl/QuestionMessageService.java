package com.qbao.ai.service.ai.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.ai.resposity.mybatis.dao.QuestionMessageDao;
import com.qbao.ai.resposity.mybatis.model.QuestionMessage;
import com.qbao.ai.service.ai.IQuestionMessageService;

/**
 * Created by shuaizhihu on 2016/12/2.
 */
@Service
public class QuestionMessageService implements IQuestionMessageService{

    @Autowired
    QuestionMessageDao questionMessageDao;

    @Override
    public boolean saveMessage(QuestionMessage message) {
        questionMessageDao.add(message);
        return true;
    }
}
