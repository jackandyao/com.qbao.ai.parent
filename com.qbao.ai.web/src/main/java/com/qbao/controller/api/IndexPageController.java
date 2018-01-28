package com.qbao.controller.api;

import com.qbao.ai.common.constant.Constant;
import com.qbao.ai.common.util.NotifierUtil;
import com.qbao.ai.dto.indexpage.MenuDto;
import com.qbao.ai.model.dto.QuestionDto;
import com.qbao.ai.service.indexpage.IIndexPageService;
import com.qbao.controller.base.BaseController;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.qbao.aisr.app.service.facade.UserTagsFacade;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-05-23 17:16:13 +0800 (Tue, 23 May 2017) $$
 * $$LastChangedRevision: 235 $$
 * $$LastChangedBy: wangping $$
 */
@Controller
@RequestMapping("/ai/indexPage/")
public class IndexPageController extends BaseController {

    public Logger logger = Logger.getLogger(getClass());


    @Autowired
    private IIndexPageService indexPageService;

    /**
     * 小智首页菜单接口
     * 
     * @return
     */
    @RequestMapping("/menus")
    @ResponseBody
    public Map<String, Object> menus(HttpServletRequest request) {

        logger.info("fetching menus list  ... ");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            List<MenuDto> data=indexPageService.findList();
            logger.info("小智首页菜单接口调用成功！size: " + data.size() + " content: " + data);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            response.put("success", false);
            String notifyMsg = "fetching menus meet error";
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
        }
        logger.info("finish to menus  cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }

    /**
     * 首页菜单跳转到具体菜单接口
     *
     * @return
     */
    @RequestMapping("/menus/details")
    @ResponseBody
    public Map<String, Object> details(HttpServletRequest request, @RequestParam(value = "dirId", required = true) Long dirId, @RequestParam(value = "limit", required = false) Integer limit) {

        logger.info("fetching menus details   ... ");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            List<QuestionDto> data=indexPageService.menuDetail(dirId,limit);
            logger.info("首页菜单跳转到具体菜单接口调用成功！dirId:"+dirId+", limit:"+ limit + "size: " + data.size() + " content: " + data);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
            response.put("questionType", "q&a");
            response.put("title", "小智给您找到的相关问题");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            response.put("success", false);
            String notifyMsg = "fetching menus details meet error";
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
        }
        logger.info("finish to menus details cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }
    /**
     * 首页菜单跳转到具体菜单接口
     *
     * @return
     */
    @RequestMapping("/menus/getQuestionByDirId")
    @ResponseBody
    public Map<String, Object> getQuestionByDirId(HttpServletRequest request, @RequestParam(value = "dirId", required = true) Long dirId) {

        logger.info("fetching menus details   ... ");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            List<QuestionDto> data=indexPageService.getQuestionByDirId(dirId);
            logger.info("首页菜单跳转到具体菜单接口调用成功！dirId:"+dirId+",size: " + data.size() + " content: " + data);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
            response.put("questionType", "q&a");
            response.put("title", "小智给您找到的相关问题");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            response.put("success", false);
            String notifyMsg = "fetching menus details meet error";
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
        }
        logger.info("finish to menus details cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }
  /*  private  Object  processData(String flag){
        Gson gson = new GsonBuilder().create();
        Object res = null;
        if (flag.contains("menus")){
           // List<MenuInfo> menuInfoList = Lists.newArrayList();
            List<MenuInfo> infos = Lists.newArrayList();
            String json ="{\"id\":1,\"iconUrl\":\"http://app.newstartsoft.com/Images/Template/gn6.png\",\"linkUrl\":\"https://www.qbao.com/\",\"name\":\"密码相关\"}";
            infos.add(gson.fromJson(json,MenuInfo.class));
            json ="{\"id\":2,\"iconUrl\":\"http://app.newstartsoft.com/Images/Template/gn6.png\",\"linkUrl\":\"https://www.qbao.com/\",\"name\":\"有好货\"}";
            infos.add(gson.fromJson(json,MenuInfo.class));
            json ="{\"id\":3,\"iconUrl\":\"http://app.newstartsoft.com/Images/Template/gn6.png\",\"linkUrl\":\"https://www.qbao.com/\",\"name\":\"宝约操作\"}";
            infos.add(gson.fromJson(json,MenuInfo.class));
            json ="{\"id\":4,\"iconUrl\":\"http://app.newstartsoft.com/Images/Template/gn6.png\",\"linkUrl\":\"https://www.qbao.com/\",\"name\":\"雷拍规则\"}";
            infos.add(gson.fromJson(json,MenuInfo.class));


            res = infos;
        }else if(flag.contains("details")){
            List<QuestionDto> dtos = Lists.newArrayList();
            List<Question> questions = Lists.newArrayList();
            String json="{\"id\":7768,\"question\":\"如何修改登录密码\"}";
            questions.add(gson.fromJson(json,Question.class));
            json="{\"id\":7769,\"question\":\"忘记登录密码如何找回\"}";
            questions.add(gson.fromJson(json,Question.class));
            json="{\"id\":7770,\"question\":\"为什么不能设置登录密码\"}";
            questions.add(gson.fromJson(json,Question.class));
            dtos.add(new QuestionDto().withQuestions(questions).withDirId(1002L).withDirName("密码"));

            questions = Lists.newArrayList();
            json="{\"id\":7798,\"question\":\"为什么签到时提示80%浮动收益\"}";
            questions.add(gson.fromJson(json,Question.class));
            json="{\"id\":7799,\"question\":\"签到工资发放调整包含哪些内容\"}";
            questions.add(gson.fromJson(json,Question.class));
            dtos.add(new QuestionDto().withQuestions(questions).withDirId(1006L).withDirName("签到"));
            res = dtos;
        }
        return res;
    }*/


}
