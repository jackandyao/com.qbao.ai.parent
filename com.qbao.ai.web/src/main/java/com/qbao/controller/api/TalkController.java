package com.qbao.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.qbao.ai.service.ai.impl.StrategyService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qbao.ai.common.constant.Constant;
import com.qbao.ai.common.util.NotifierUtil;
import com.qbao.ai.model.dto.AnswerDto;
import com.qbao.ai.model.dto.Banner;
import com.qbao.ai.model.dto.ContactDto;
import com.qbao.ai.model.dto.GoodsDto;
import com.qbao.ai.model.dto.Question;
import com.qbao.ai.model.dto.QuestionDto;
import com.qbao.ai.model.dto.Stuff;
import com.qbao.ai.model.dto.WeatherDto;
import com.qbao.ai.model.dto.WeatherInfo;
import com.qbao.controller.base.BaseController;

/**
 * @author wangping
 * @createTime 2017/4/11 下午5:59
 * $$LastChangedDate: 2017-05-11 15:22:25 +0800 (Thu, 11 May 2017) $$
 * $$LastChangedRevision: 216 $$
 * $$LastChangedBy: zhangjun $$
 */
@Controller
@RequestMapping("/ai/talk/")
public class TalkController extends BaseController {
    public Logger logger = Logger.getLogger(getClass());
    @Autowired
    private StrategyService strategyService;

    @RequestMapping("/question")
    @ResponseBody
    public Map<String, Object> talk(HttpServletRequest request,
            @RequestParam(value = "q",required = false) String message,
            @RequestParam(value = "device",required = true ) int device,
            @RequestParam(value = "lat",required = true ) String lat,
            @RequestParam(value = "lon",required = true ) String lon,
            @RequestParam(value = "page",required = false, defaultValue = "1") int page,
            @RequestParam(value = "size",required = false, defaultValue = "10") int size) {

        long userId = getCurrentUserId(request);
        logger.warn("userId =" + userId + " talk message="+message);
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response=strategyService.answerInfo(message,device,lat,lon,userId,page,size,null);
            logger.info("/ai/talk/question接口调用成功！: q:"+message+"device:"+device+"lat:"+lat+",lon:"+lon+",page:"+page+",size:"+size+", content: " + response);
        } catch (Exception ex) {
            String msg = ExceptionUtils.getFullStackTrace(ex);
            String notifyMsg = "fetching all tags list meet error:  user id =[" + userId + "] ";
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
            response=strategyService.getCustomerService();
            response.put("userId",userId);
        }
        logger.info("answer userId=[" + userId + "] message=["+message+"] cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }

    @RequestMapping("/qqList")
    @ResponseBody
    public Map<String, Object> qqList(HttpServletRequest request,
            @RequestParam(value = "page",required = false, defaultValue = "1") int page,
            @RequestParam(value = "size",required = false, defaultValue = "10") int size) {

        long userId = getCurrentUserId(request);
        logger.warn("qqList start =====================");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response=strategyService.getCustomerService();
            logger.info("/ai/talk/qqList接口调用成功！: userId:"+userId+",page:"+page+",size:"+size+", content: " + response);
            response.put("userId",userId);
        } catch (Exception ex) {
            String msg = ExceptionUtils.getFullStackTrace(ex);
            String notifyMsg = "fetching all tags list meet error:  user id =[" + userId + "] ";
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
            response=strategyService.getCustomerService();
            response.put("userId",userId);
        }
        logger.info("qqList end cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }
}
