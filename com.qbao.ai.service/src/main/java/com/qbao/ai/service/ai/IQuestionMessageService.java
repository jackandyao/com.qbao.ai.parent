package com.qbao.ai.service.ai;

import com.qbao.ai.resposity.mybatis.model.QuestionMessage;

/**
 * Created by shuaizhihu on 2016/12/1.
 */
public interface IQuestionMessageService {
    public boolean saveMessage(QuestionMessage message);
}
