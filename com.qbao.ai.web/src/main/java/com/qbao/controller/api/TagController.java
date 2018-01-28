package com.qbao.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.qbao.ai.dto.tag.TagItemDto;
import com.qbao.ai.dto.tag.TagTypeDto;
import com.qbao.ai.service.tag.ITagInfoService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qbao.ai.common.constant.Constant;
import com.qbao.ai.common.util.NotifierUtil;
import com.qbao.controller.base.BaseController;

//import com.qbao.aisr.app.service.facade.UserTagsFacade;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-05-11 15:22:25 +0800 (Thu, 11 May 2017) $$
 * $$LastChangedRevision: 216 $$
 * $$LastChangedBy: zhangjun $$
 */
@Controller
@RequestMapping("/ai/tag/")
public class TagController extends BaseController {

    public Logger logger = Logger.getLogger(getClass());

    @Autowired
    private ITagInfoService tagsInfoService;

    /**
     * 获取所有标签接口
     * 
     * @return
     */
    @RequestMapping("/allList")
    @ResponseBody
    public Map<String, Object> allList(HttpServletRequest request) {

        logger.info("fetching all tags list  ... ");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            List<TagTypeDto> data=tagsInfoService.findList();
            logger.info("获取所有标签接口调用成功！size: " + data.size() + " content: " + data);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            response.put("success", false);
            String notifyMsg = "fetching all tags list meet error";
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
        }
        logger.info("finish to all tags list  cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }

    /**
     * 获取问题标签接口
     * 
     * @param
     * @return
     */
    @RequestMapping("/items")
    @ResponseBody
    public Map<String, Object> items(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id) {
        //long userId = getCurrentUserId(request);
        logger.info("fetching items  ... ");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        //response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            //TagsDto data = userTagsFacde.fetchUserTags(userId, typeId);
            //response.put("data", processData("items"));
            TagItemDto data=tagsInfoService.findByMenuId(id);
            logger.info("获取问题标签接口调用成功！id: " + id + " content: " + data);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            response.put("success", false);
            String notifyMsg = "fetching items meet error";
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
        }
        logger.info("finish to items  cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }
    private  Object  processData(String flag){
        Gson gson = new GsonBuilder().create();
        Object res = null;
        /*if (flag.contains("allList")){
            List<TagInfo> tagInfosList = Lists.newArrayList();
            List<TagItemInfo> infos = Lists.newArrayList();
            String json ="{\"tagDetailId\":1,\"value\":\"返券规则\"}";
            infos.add(gson.fromJson(json,TagItemInfo.class));
            json ="{\"tagDetailId\":2,\"value\":\"什么是有好货\"}";
            infos.add(gson.fromJson(json,TagItemInfo.class));
            json ="{\"tagDetailId\":3,\"value\":\"好货推荐\"}";
            infos.add(gson.fromJson(json,TagItemInfo.class));
            tagInfosList.add(new TagInfo().withItems(infos).withId(1l).withTagName("有好货"));

            List<TagItemInfo> infos2 = Lists.newArrayList();
            String json2 ="{\"tagDetailId\":4,\"value\":\"参加雷拍条件\"}";
            infos2.add(gson.fromJson(json,TagItemInfo.class));
            json2 ="{\"tagDetailId\":5,\"value\":\"中奖概率\"}";
            infos2.add(gson.fromJson(json,TagItemInfo.class));
            tagInfosList.add(new TagInfo().withItems(infos2).withId(2l).withTagName("雷拍"));
            res = tagInfosList;
        }else if(flag.contains("items")){
            List<TagItemDto> tagItemDtoList = Lists.newArrayList();
            List<TagItemInfo> infos = Lists.newArrayList();
            String json ="{\"tagDetailId\":1,\"value\":\"注册帐号\"}";
            infos.add(gson.fromJson(json,TagItemInfo.class));
            json ="{\"tagDetailId\":2,\"value\":\"找回密码\"}";
            infos.add(gson.fromJson(json,TagItemInfo.class));
            json ="{\"tagDetailId\":3,\"value\":\"忘记帐号\"}";
            infos.add(gson.fromJson(json,TagItemInfo.class));
            tagItemDtoList.add(new TagItemDto().withItems(infos).withMenuId(2l));

            res = tagItemDtoList;
        }*/
        return res;
    }
}
