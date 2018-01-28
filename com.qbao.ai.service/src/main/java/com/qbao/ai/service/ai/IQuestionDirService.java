package com.qbao.ai.service.ai;

import com.qbao.ai.resposity.common.Page;
import com.qbao.ai.resposity.mybatis.model.QuestionDir;

/**
 * Created by shuaizhihu on 2016/12/1.
 */
public interface IQuestionDirService {

    Page<QuestionDir> findByLev(int lev, int page, int size);
}
