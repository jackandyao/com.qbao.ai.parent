package com.qbao.controller.api;

import com.qbao.ai.common.constant.Constant;
import com.qbao.ai.model.dto.QuestionDirDto;
import com.qbao.ai.model.dto.QuestionLinkDto;
import com.qbao.ai.service.ai.IQuestionInfoService;
import com.qbao.ai.service.ai.impl.StrategyService;
import com.qbao.controller.base.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping("/ai/menus/")
public class QuestionController extends BaseController {

    @Autowired
    private IQuestionInfoService questionInfoService;

    @Autowired
    private StrategyService strategyService;
    Logger logger=Logger.getLogger(QuestionController.class);

    /**
     * 首页菜单跳转到具体菜单接口描述
     * @param request
     * @param menuId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/details")
    @ResponseBody
    public Map<String, Object> details(HttpServletRequest request,
            @RequestParam(value = "menuId", required = true) Long menuId,
            @RequestParam(value = "page",required = false, defaultValue = "1") int page,
            @RequestParam(value = "size",required = false, defaultValue = "10") int size){

        logger.info("fetching ad details  ...  /details.do?menuId="+menuId+"&page="+page+"&size="+size);
        long start = System.currentTimeMillis();

        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            QuestionDirDto data=questionInfoService.findQuestionInfoByRootDirId(menuId, page, size);
            logger.info("首页菜单跳转到具体菜单接口描述调用成功！menuId:"+menuId+"page:"+page+" size:"+size+" content: " + data);
            response.put("data", data);
            response.put("questionType", "q&a");
            response.put("title","小智给您找到的相关问题");
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response=strategyService.getCustomerService();
            response.put("userId",userId);
            logger.error(ex.getMessage(),ex);
        }
        logger.info("finish to fetch details. and cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }

    /**
     * 问题具体答案接口描述
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/answer")
    @ResponseBody
    public Map<String,Object> quesetionInfo(HttpServletRequest request,@RequestParam(value = "id", required = true) Long id){
        logger.info("fetching ad answer  .../answer.do?id="+id);
        long start = System.currentTimeMillis();

        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            QuestionLinkDto data=questionInfoService.getQuestionInfoAnswer(userId,id);
            logger.info("小智首页菜单接口调用成功！userId: " + userId + " id: " + id + " content: " + data);
            response.put("data", data);
            response.put("questionType","normal");
            response.put("title","小智给您找到的答案");
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response=strategyService.getCustomerService();
            response.put("userId",userId);
            logger.error(ex.getMessage(),ex);
        }
        logger.info("finish to fetch answer. and cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }
}
