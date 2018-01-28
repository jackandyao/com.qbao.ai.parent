package com.qbao.ai.service.ai;

import java.util.List;

import com.qbao.ai.resposity.mybatis.model.QuestionLink;

/**
 * Created by shuaizhihu on 2016/12/2.
 */
public interface IQuestionLinkService {
    public List<QuestionLink> findList(String linkIds);
}
