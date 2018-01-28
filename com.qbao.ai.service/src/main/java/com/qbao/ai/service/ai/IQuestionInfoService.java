package com.qbao.ai.service.ai;

import com.qbao.ai.model.dto.QuestionDirDto;
import com.qbao.ai.model.dto.QuestionInfoIndexDto;
import com.qbao.ai.model.dto.QuestionLinkDto;
import com.qbao.ai.resposity.common.Page;
import com.qbao.ai.resposity.mybatis.model.QuestionInfo;
import com.qbao.ai.resposity.solr.document.Question;

/**
 * Created by shuaizhihu on 2016/12/1.
 */
public interface IQuestionInfoService {

    public Page<QuestionInfo> findPageByDirId(long dirId, int page, int size);

    public QuestionDirDto findQuestionInfoByRootDirId(long rootDirId, int page, int size);

    public QuestionInfo findById(long id);

    public Page<Question> search(String kw, int page, int size);

    public Page<QuestionInfo> findPageByRootDirId(long rootDirId, int page, int size);

    public QuestionInfoIndexDto getQuestionInfo( long userId,long id) ;

    public QuestionLinkDto getQuestionInfoAnswer( long userId,long id);
}
