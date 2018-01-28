package com.qbao.ai.resposity.solr.biz;

import com.qbao.ai.resposity.common.Page;
import com.qbao.ai.resposity.solr.document.Question;

/**
 * Created by shuaizhihu on 2016/12/6.
 */
public interface QuestionSearchBiz {
    Page<Question>  searchQuestions(String kw,int page,int size);
    public Page<Question> searchQuestions(String kw,Long menuId, int page, int size);
}
