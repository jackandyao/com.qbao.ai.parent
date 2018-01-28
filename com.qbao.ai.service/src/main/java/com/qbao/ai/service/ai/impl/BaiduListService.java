package com.qbao.ai.service.ai.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.qbao.ai.common.constant.Constant;
import com.qbao.ai.model.dto.QuestionSearchDto;
import com.qbao.ai.resposity.redis.cache.annotation.CacheType;
import com.qbao.ai.resposity.redis.cache.annotation.RedisCache;
import com.qbao.ai.service.ai.IBaseAnswerService;


/**
 * @author liaijun
 * @createTime 17/4/14 下午2:28
 * $$LastChangedDate: 2017-06-01 11:28:22 +0800 (Thu, 01 Jun 2017) $$
 * $$LastChangedRevision: 238 $$
 * $$LastChangedBy: zhangjun $$
 */
@Service
public class BaiduListService implements IBaseAnswerService{
    Logger logger= Logger.getLogger(BaiduListService.class);


    @Override
    public Map<String,Object> answerInfo(String question, int device, String lat, String lon, Long userId, int page, int size,Set<String> set) {
        List<QuestionSearchDto> webpages =search(question,page,10);
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
        response.put("questionType","search");
        response.put("title","小智给您找到如下如下答案");
        response.put("success", true);
        response.put("message", "Ok");
        response.put("userId",userId);
        response.put("data",webpages);
        return response;
    }


    public Map<String,Object> answerInfo(String question,Long userId, int page, int size) {
        List<QuestionSearchDto> webpages =search(question,page,10);
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
        response.put("questionType","search");
        response.put("title","小智给您找到如下如下答案");
        response.put("success", true);
        response.put("message", "Ok");
        response.put("userId",userId);
        response.put("data",webpages);
        return response;
    }




    public List<QuestionSearchDto>  search(String keyword, int page,int pageSize) {

        //百度搜索结果每页大小为10，pn参数代表的不是页数，而是返回结果的开始数
        //如获取第一页则pn=0，第二页则pn=10，第三页则pn=20，以此类推，抽象出模式：(page-1)*pageSize
        String url = Constant.BAIDU_URL+(page-1)*pageSize+"&wd="+keyword;


        List<QuestionSearchDto> webpages = new ArrayList<QuestionSearchDto>();
        try {
            Document document = Jsoup.connect(url).get();

            //获取搜索结果数目
            int total = getBaiduSearchResultCount(document);

            int len = 10;
            if (total < 1) {
                return null;
            }
            //如果搜索到的结果不足一页
            if (total < 10) {
                len = total;
            }
            for (int i = 0; i < len; i++) {
                String titleCssQuery = "html body div div div div#content_left div#" + (i + 1 + (page-1)*pageSize) + ".result.c-container h3.t a";
                String summaryCssQuery = "html body div div div div#content_left div#" + (i + 1 + (page-1)*pageSize) + ".result.c-container div.c-abstract";

                Element titleElement = document.select(titleCssQuery).first();
                String href = "";
                String titleText = "";
                if(titleElement != null){
                    titleText = titleElement.text();
                    href = titleElement.attr("href");
                }else{
                    //处理百度百科
                    titleCssQuery = "html body div#out div#in div#wrapper div#container div#content_left div#1.result-op h3.t a";
                    summaryCssQuery = "html body div#out div#in div#wrapper div#container div#content_left div#1.result-op div p";
                    logger.debug("处理百度百科 titleCssQuery:" + titleCssQuery);
                    logger.debug("处理百度百科 summaryCssQuery:" + summaryCssQuery);
                    titleElement = document.select(titleCssQuery).first();
                    if(titleElement != null){
                        titleText = titleElement.text();
                        href = titleElement.attr("href");
                    }
                }
                baiduSearch( document, titleText, summaryCssQuery, href,webpages );
            }


        } catch (Exception ex) {
            logger.error("搜索出错",ex);
        }
        return webpages;
    }
    @RedisCache(expire = 60 * 30, clazz = QuestionSearchDto.class, cacheType = CacheType.LIST)
    public List<QuestionSearchDto>  baiduSearch(Document document,String titleText,String summaryCssQuery,String href,List<QuestionSearchDto> webpages ){
        logger.debug(titleText);
        Element summaryElement = document.select(summaryCssQuery).first();
        //处理百度知道
        if(summaryElement == null){
            summaryCssQuery = summaryCssQuery.replace("div.c-abstract","font");
            logger.debug("处理百度知道 summaryCssQuery:" + summaryCssQuery);
            summaryElement = document.select(summaryCssQuery).first();
        }
        String summaryText = "";
        if(summaryElement != null){
            summaryText = summaryElement.text();
        }
        logger.debug(summaryText);

        if (titleText != null && !"".equals(titleText.trim()) && summaryText != null && !"".equals(summaryText.trim())) {
            QuestionSearchDto webpage = new QuestionSearchDto();
            webpage.setSummary(titleText);
            webpage.setLink(href);
            webpage.setContent(summaryText);
            webpages.add(webpage);
        } else {
            logger.error("获取搜索结果列表项出错:" + titleText + " - " + summaryText);
        }
        return webpages;
    }

    /**
     * 获取百度搜索结果数
     * 获取如下文本并解析数字：
     * 百度为您找到相关结果约13,200个
     * @param document 文档
     * @return 结果数
     */
    @RedisCache(expire = 60 * 30, clazz = Integer.class, cacheType = CacheType.OBJECT)
    private int getBaiduSearchResultCount(Document document){
        String cssQuery = "html body div div div div.nums";
        logger.debug("total cssQuery: " + cssQuery);
        Element totalElement = document.select(cssQuery).first();
        String totalText = totalElement.text();
        logger.info("搜索结果文本：" + totalText);

        String regEx="[^0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(totalText);
        totalText = matcher.replaceAll("");
        int total = Integer.parseInt(totalText);
        logger.info("搜索结果数：" + total);
        return total;
    }

    public static void main(String[] args) {
        BaiduListService searcher = new BaiduListService();
        List<QuestionSearchDto> webpages = searcher.search("钱宝",1,10);

        System.out.println(webpages);
    }


}
