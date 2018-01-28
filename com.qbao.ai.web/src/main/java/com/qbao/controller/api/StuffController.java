package com.qbao.controller.api;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qbao.ai.common.constant.Constant;
import com.qbao.ai.common.util.ExceptionAlarmUtils;
import com.qbao.ai.model.dto.StuffDto;
import com.qbao.ai.model.dto.ZwStuffDto;
import com.qbao.ai.resposity.common.Page;
import com.qbao.ai.service.ai.impl.GoodsService;
import com.qbao.ai.util.HttpProcessException;
import com.qbao.controller.base.BaseController;

/**
 * @author wangping
 * @createTime 2017/4/11 下午5:59
 * $$LastChangedDate: 2017-05-11 15:22:25 +0800 (Thu, 11 May 2017) $$
 * $$LastChangedRevision: 216 $$
 * $$LastChangedBy: zhangjun $$
 */
@Controller
@RequestMapping("/ai/stuff/")
public class StuffController extends BaseController {
    public Logger logger = Logger.getLogger(StuffController.class);
    @Autowired
    private GoodsService goodsService;



    @RequestMapping("/similar")
    @ResponseBody
    public Map<String, Object> similar(
            HttpServletRequest request,
            @RequestParam(value = "stuffId", required = false, defaultValue = "") long stuffId,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "device", required = true) int device) {
        long userId = this.getCurrentUserId(request);
        logger.info("similar start >>> stuffId=" + stuffId + " userId=" + userId + "&page=" + page + " size=" + pageSize);
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("userId", userId);
        try {
            int newSize = pageSize + 1;
            Page<ZwStuffDto> pageResult = goodsService.searchZwSim(userId, stuffId, page, newSize,device);
            logger.info("小智similar接口调用成功！size: " + pageResult.getItems().size() + " content: " + pageResult.getItems());
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", pageResult.getItems());
            response.put("total", pageResult.getTotalNumber());
            response.put("questionType","goods");
            response.put("title","很开心等到你,小智很想你,来看看小智为你精选推荐");
            response.put("success", true);
            response.put("message", "Ok");
            response.put("userId",userId);
        } catch (UnsupportedEncodingException e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data", null);
            response.put("total", 0);
            response.put("success", true);
            response.put("message", "url不合法");
        } catch (HttpProcessException e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data", null);
            response.put("total", 0);
            response.put("success", true);
            response.put("message", "请求搜索服务出错！");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data", null);
            response.put("total", 0);
            response.put("success", true);
            response.put("message", "系统错误！");
        }
        logger.info("similar end >>> count time:" + (System.currentTimeMillis() - start));
        return response;
    }

    /**
     * 商品推荐
     *
     * @param
     * @return
     */
    @RequestMapping("/recommend")
    @ResponseBody
    public Map<String, Object> stuffRecommend(HttpServletRequest request, @RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "size", required = false, defaultValue = "10") int size,@RequestParam(value = "device", required = true) int device) {
        long userId = getCurrentUserId(request);
        //long userId = 5434613l;
        logger.info("stuffRecommend start >>> userId="  + userId + ", page=" + page + ", size=" + size + ", device=" + device);
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            StuffDto data = goodsService.stuffRecommend(userId, page, size,device);
            logger.info("小智商品推荐接口调用成功！ content: " + data +"userId :"+userId+", page:"+page+", size:"+size+",device:"+device);
            response.put("data", data);
            response.put("questionType","goods");
            response.put("title","很开心等到你,小智很想你,来看看小智为你精选推荐");
            response.put("success", true);
            response.put("message", "Ok");
            response.put("userId",userId);
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("success", false);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            logger.error("stuffRecommend ", ex);
            //异常短消息报警
            ExceptionAlarmUtils.sendAlarm("/stuff/search/recommend", "page:" + page + ",size:" + size, "Exception", ex.getMessage());
        }
        logger.info("stuffRecommend end >>> count time:" + (System.currentTimeMillis() - startTime));
        return response;
    }
}
