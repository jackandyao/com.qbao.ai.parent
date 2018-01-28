package com.qbao.ai.rest;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.alibaba.fastjson.JSON;
import com.qbao.ai.parse.service.IParseService;
import com.qbao.ai.parse.utils.ConfigurationUtil;
import com.qbao.ai.resposity.common.Page;
import com.qbao.ai.resposity.mybatis.model.QuestionDir;
import com.qbao.ai.resposity.mybatis.model.QuestionInfo;
import com.qbao.ai.resposity.mybatis.model.QuestionLink;
import com.qbao.ai.resposity.mybatis.model.QuestionMessage;
import com.qbao.ai.resposity.solr.document.Question;
import com.qbao.ai.rest.dto.*;
import com.qbao.ai.service.ai.IQuestionDirService;
import com.qbao.ai.service.ai.IQuestionInfoService;
import com.qbao.ai.service.ai.IQuestionLinkService;
import com.qbao.ai.service.ai.IQuestionMessageService;
import com.qbao.ai.util.BeanUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import java.util.*;

/**
 * Created by shuaizhihu on 2016/11/29.
 */
@Path("ai/question")
public class AiRestFacade  implements IAiRestFacade{

    Logger logger = LoggerFactory.getLogger(AiRestFacade.class);

    @Autowired
    IQuestionDirService questionDirService;

    @Autowired
    IQuestionInfoService questionInfoService;

    @Autowired
    IQuestionLinkService questionLinkService;

    @Autowired
    IQuestionMessageService questionMessageService;

    /**
     * 猜你遇到问题接口
     * @param userId
     * @return
     */
    @GET
    @Path("/guess")
    @Produces(ContentType.APPLICATION_JSON_UTF_8)
    @Override
    public Map<String, Object> getGuessQuestions(@QueryParam("userId") long userId) {
        logger.warn("question/guess?userId="+userId);
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            List<GuessQuestionDTO> dataDtoList = new ArrayList<GuessQuestionDTO>();
            Page<QuestionDir> dirPage = questionDirService.findByLev(1, 1, 20);
            for (QuestionDir dir : dirPage.getItems()) {
                GuessQuestionDTO dto = new GuessQuestionDTO();
                dto.setDirId(dir.getDirId());
                dto.setDirName(dir.getDirName());
                Page<QuestionInfo> questionInfoPage = questionInfoService.findPageByRootDirId(dir.getDirId(), 1, 10);
                List<QuestionSimDTO> simList = BeanUtils.mapList(questionInfoPage.getItems(), QuestionSimDTO.class);
                dto.setQuestions(simList);
                if(simList.size()>0) {
                    dataDtoList.add(dto);
                }
            }
            response.put("returnCode","1000");
            response.put("returnMsg","ok");
            response.put("data",dataDtoList);
            logger.info("question/guess?userId="+userId + " result:"+JSON.toJSONString(response));
        }catch(Exception e){
            response.put("returnCode","1001");
            response.put("returnMsg","查询数据出错了");
            logger.error(ExceptionUtils.getFullStackTrace(e));
        }
        return response;
    }

    /**
     * 大家都在问 标签接口
     * @param userId
     * @return
     */
    @GET
    @Path("/tags")
    @Produces(ContentType.APPLICATION_JSON_UTF_8)
    @Override
    public Map<String, Object> getHotQuestionDirs(@QueryParam("userId")long userId) {
        logger.warn("question/tags?userId="+userId);
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            Page<QuestionDir> page = questionDirService.findByLev(2, 1, 10);
            List<QuestionTagDTO> tags = new ArrayList<QuestionTagDTO>();
            for (QuestionDir dir : page.getItems()) {
                QuestionTagDTO dto = new QuestionTagDTO();
                dto.setTagName(dir.getDirName());
                tags.add(dto);
            }
            response.put("returnCode", "1000");
            response.put("returnMsg", "ok");
            response.put("data", tags);
            logger.info("question/tags?userId="+userId + " result:"+JSON.toJSONString(response));
        }catch(Exception e){
            response.put("returnCode","1001");
            response.put("returnMsg","查询数据出错了");
            logger.error(ExceptionUtils.getFullStackTrace(e));
        }
        return response;
    }

    /**
     * 问题详情页面
     * @param userId,id
     * @return
     */
    @GET
    @Path("/info")
    @Produces(ContentType.APPLICATION_JSON_UTF_8)
    @Override
    public Map<String, Object> getQuestionInfo(@QueryParam("userId") long userId,@QueryParam("id") long id) {
        logger.warn("question/info?userId="+userId+"&id="+id);
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            QuestionInfo info = questionInfoService.findById(id);
            QuestionInfoDTO dto = BeanUtils.map(info,QuestionInfoDTO.class);
            List<QuestionLink> links = questionLinkService.findList(info.getLinkIds());
            dto.setLinks(BeanUtils.mapList(links, QuestionLinkDTO.class));
            List<QuestionSimDTO> simList = new  ArrayList<QuestionSimDTO>();
            try {
                Page<Question> page = questionInfoService.search(info.getQuestion(), 1, 5);
                simList = BeanUtils.mapList(page.getItems(), QuestionSimDTO.class);
                for (QuestionSimDTO simdto : simList) {
                    if (simdto.getId() == info.getId()) {
                        simList.remove(simdto);
                        break;
                    }
                }
            }catch(Exception e){
                logger.error(ExceptionUtils.getFullStackTrace(e));
            }
            dto.setSimQustions(simList);
            response.put("returnCode", "1000");
            response.put("returnMsg", "ok");
            response.put("data", dto);
            logger.info("question/info?userId="+userId+"&id="+id + " result:"+JSON.toJSONString(response));
        }catch(Exception e){
            response.put("returnCode","1001");
            response.put("returnMsg","查询数据出错了");
            logger.error(ExceptionUtils.getFullStackTrace(e));
        }
        return response;
    }

    @GET
    @Path("/search")
    @Produces(ContentType.APPLICATION_JSON_UTF_8)
    @Override
    public Map<String, Object> search(@QueryParam("userId") long userId,@QueryParam("question") String question,@QueryParam("page") int page,@QueryParam("size") int size) {
        logger.warn("question/search?userId="+userId+"&question="+question+"&page="+page+"&size="+size);
        if(page == 0){
            page = 1;
        }
        if(size == 0){
            size = 10;
        }
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            IParseService parseService = ConfigurationUtil.getParseServiceInstance();

            if( !parseService.parse(question)){
                response.put("returnMsg","ok");
                response.put("returnCode","1000");
                Map<String,Object> data = new HashMap<String,Object>();
                data.put("total",0);
                data.put("questions",new ArrayList<QuestionSimDTO> ());
                data.put("reasonCode","102");
                data.put("reason","抱歉！搜索词中含有敏感词！");
                response.put("data",data);
                return response;
            }
            List<QuestionSimDTO> dataDtoList = new ArrayList<QuestionSimDTO>();
            Page<Question> result = questionInfoService.search(question,page,size);
            int total = result.getTotalNumber();
            dataDtoList = BeanUtils.mapList(result.getItems(),QuestionSimDTO.class);
            Map<String,Object> data = new HashMap<String,Object>();
            data.put("total",total);
            data.put("questions",dataDtoList);
            data.put("reasonCode","100");
            data.put("reason","搜索正常！");
            response.put("returnCode","1000");
            response.put("returnMsg","ok");
            response.put("data",data);
            logger.info("question/search?userId="+userId+"&question="+question+"&page="+page+"&size="+size+" result:"+JSON.toJSONString(response));
        }catch(Exception e){
            response.put("returnCode","1000");
            response.put("returnMsg","ok");
            Map<String,Object> data = new HashMap<String,Object>();
            data.put("total",0);
            data.put("questions",new ArrayList<QuestionSimDTO> ());
            data.put("reasonCode","101");
            data.put("reason","搜索异常！");
            response.put("data",data);
            logger.error(ExceptionUtils.getFullStackTrace(e));
        }
        return response;
    }

    @POST
    @Path("/save")
    @Produces(ContentType.APPLICATION_JSON_UTF_8)
    @Override
    public Map<String, Object> saveMessage(@FormParam("userId")  long userId,@FormParam("email")  String email, @FormParam("phone") String phone,@FormParam("question") String question) {
        logger.warn("question/save?userId="+userId+"&email="+email+"&phone="+phone+"&question="+question);
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            QuestionMessage message = new QuestionMessage();
            message.setUserId(userId);
            message.setEmail(email);
            message.setPhone(phone);
            message.setQuestion(question);
            message.setStatus(0);
            message.setCreateTime(new Date());
            message.setUpdateTime(new Date());
            questionMessageService.saveMessage(message);
            response.put("returnCode", "1000");
            response.put("returnMsg", "ok");
            logger.info("question/save?userId="+userId+"&email="+email+"&phone="+phone+"&question="+question+ " result:"+JSON.toJSONString(response));
        }catch(Exception e){
            response.put("returnCode","1001");
            response.put("returnMsg","数据添加出错了");
            logger.error(ExceptionUtils.getFullStackTrace(e));
        }
        return response;
    }



}
