package com.qbao.controller.api;

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
import com.qbao.ai.resposity.mybatis.model.ServiceSwitch;
import com.qbao.ai.service.ai.IServiceswitchService;

/**
 * @author zhangjun
 * @createTime 17/3/30 下午5:41
 * $$LastChangedDate: 2017-03-17 11:27:06 +0800 (周五, 2017-03-17) $$
 * $$LastChangedRevision: 258 $$
 * $$LastChangedBy:  $$
 */
@Controller
@RequestMapping("/service/")
public class ServiceSwitchController  {

    public Logger logger = Logger.getLogger(getClass());

    @Autowired
    private IServiceswitchService serviceswitchService;

    /**
     * 获取服务开关
     * 
     * @return
     */
    @RequestMapping("/switch")
    @ResponseBody
    public Map<String, Object> fetchUserTags(HttpServletRequest request, @RequestParam(value = "key", required = true) String key) {

        logger.info("fetching service switch :  key  =[" + key + "]  ... ");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            ServiceSwitch data = serviceswitchService.findServiceSwitchByKey(key);
            logger.info("获取服务开关接口调用成功！ content: " + data);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            response.put("success", false);
            logger.error("fetching service switch :  key  =[" + key + "] ", ex);
            //TODO
            //异常短消息报警
        }
        logger.info("finish to fetch service switch :  key  =[" + key + "]! and cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }

}
