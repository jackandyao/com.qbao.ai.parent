package com.qbao.ai.rest;

import java.util.Map;

/**
 * Created by shuaizhihu on 2016/12/2.
 */
public interface IAiRestFacade {

    public Map<String, Object> getGuessQuestions(long userId);

    public Map<String, Object> getHotQuestionDirs(long userId);

    public Map<String,Object>  getQuestionInfo(long userId,long questionId);

    public Map<String,Object>  search(long userId,String question,int page,int size);

    public Map<String,Object>  saveMessage(long userId,String email,String phone,String question);


}
