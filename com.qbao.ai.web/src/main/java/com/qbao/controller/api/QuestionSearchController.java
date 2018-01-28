package com.qbao.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qbao.ai.common.constant.Constant;
import com.qbao.controller.base.BaseController;

@Controller
@RequestMapping("/ai/search/")
public class QuestionSearchController extends BaseController {

//    @Autowired
//    private IQuestionInfoService questionInfoService;

    Logger logger=Logger.getLogger(QuestionSearchController.class);
    @RequestMapping("/question")
    @ResponseBody
    public Map<String, Object> details(HttpServletRequest request,
            @RequestParam(value = "menuId", required = true) Long menuId,
            @RequestParam(value = "kw", required = true) String kw,
            @RequestParam(value = "page",required = false, defaultValue = "1") int page,
            @RequestParam(value = "size",required = false, defaultValue = "10") int size){

        logger.info("fetching ad details  ...  /details.do?menuId="+menuId+"&page="+page+"&size="+size);
        long start = System.currentTimeMillis();

        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            logger.info("/ai/search/question接口调用成功！menuId:"+menuId+",kw: " + kw);
            //Page<QuestionInfo> data=questionInfoService.findPageByRootDirId(typeId, page, size);
//            response.put("data", data.getItems());
//            response.put("total", data.getTotalNumber());
            //response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("success", false);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);

            logger.error(ex.getMessage(),ex);
        }
        logger.info("finish to fetch details. and cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }

    @RequestMapping("/answer")
    @ResponseBody
    public Map<String,Object> quesetionInfo(HttpServletRequest request,@RequestParam(value = "id", required = true) Long id){
        logger.info("fetching ad questionList  ... ");
        long start = System.currentTimeMillis();

        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
//            QuestionInfo data=questionInfoService.findById(id);
            String data="{\n" + "  \"data\": {\n" + "    \"id\": 121,\n" + "    \"question\": \"账户密码丢了怎么办\",\n" + "    \"answer\": \"您好，登录钱宝网页端 修改登录密码\",\n" + "    \"links\": [\n" + "      {\n" + "        \"name\": \"修改登录密码\",\n" + "        \"link\": \"http://www.baidu.com\"\n" + "      },\n" + "      {\n" + "        \"name\": \"修改交易密码\",\n" + "        \"link\": \"http://www.baidu.com\"\n" + "      }\n" + "    ]\n" + "  },\n" + "  \"success\": true,\n" + "  \"message\": \"Ok\",\n" + "  \"responseCode\": 1000\n" + "}";
            response.put("data", data);
            logger.info("/ai/search/answer接口调用成功！userId: " + userId + " id: " + id + " content: " + data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("success", false);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);

            logger.error(ex.getMessage(),ex);
        }
        logger.info("finish to fetch questionList. and cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }
}
