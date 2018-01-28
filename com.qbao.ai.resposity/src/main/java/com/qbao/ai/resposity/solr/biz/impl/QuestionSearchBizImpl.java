package com.qbao.ai.resposity.solr.biz.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qbao.ai.resposity.common.Page;
import com.qbao.ai.resposity.redis.cache.annotation.CacheType;
import com.qbao.ai.resposity.redis.cache.annotation.RedisCache;
import com.qbao.ai.resposity.solr.biz.QuestionSearchBiz;
import com.qbao.ai.resposity.solr.document.Question;

import com.qbao.ai.util.http.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by shuaizhihu on 2016/12/6.
 */
@Component
public class QuestionSearchBizImpl implements QuestionSearchBiz {
    @Value("${solr.url}")
    String solrUrl ;


    @Override
    @RedisCache(expire = 60 * 3, clazz = Question.class, cacheType = CacheType.PAGE)
    public Page<Question> searchQuestions(String kw, int page, int size) {
        String kwEnc =kw;
        try {
            kwEnc =URLEncoder.encode(kw,"utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encode 出错！");
        }
        String url = solrUrl+"?kw="+kwEnc+"&page="+page+"&size="+size;
        HttpClientUtil util =  HttpClientUtil.getHttpClientInstance();
        String json =util.sendHttpGet(url);
        JSONObject reponse = JSON.parseObject(json);
        String returnCode = reponse.getString("returnCode");
        if(returnCode.equals("1000")) {
            JSONObject dataObject = reponse.getJSONObject("data");
            int total = dataObject.getInteger("totalCount");
            JSONArray array = dataObject.getJSONArray("dataList");
            List<Question> list= JSON.parseArray(array.toJSONString(),Question.class);
            return new Page<Question>(total,page,size,list);
        }else{
            throw new RuntimeException("请求接口出错！ url："+url+" response:"+json);
        }
    }

    @Override
    @RedisCache(expire = 60 * 3, clazz = Question.class, cacheType = CacheType.PAGE)
    public Page<Question> searchQuestions(String kw,Long menuId, int page, int size) {
        String kwEnc =kw;
        try {
            kwEnc =URLEncoder.encode(kw,"utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encode 出错！");
        }
        String url = solrUrl+"Ai?kw="+kwEnc+"&page="+page+"&size="+size+"&type="+menuId;
        HttpClientUtil util =  HttpClientUtil.getHttpClientInstance();
        String json =util.sendHttpGet(url);
        JSONObject reponse = JSON.parseObject(json);
        String returnCode = reponse.getString("returnCode");
        if(returnCode.equals("1000")) {
            JSONObject dataObject = reponse.getJSONObject("data");
            int total = dataObject.getInteger("totalCount");
            JSONArray array = dataObject.getJSONArray("dataList");
            List<Question> list= JSON.parseArray(array.toJSONString(),Question.class);
            return new Page<Question>(total,page,size,list);
        }else{
            throw new RuntimeException("请求接口出错！ url："+url+" response:"+json);
        }
    }
}
