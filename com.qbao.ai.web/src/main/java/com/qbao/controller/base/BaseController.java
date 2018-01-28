package com.qbao.controller.base;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.qbao.ai.util.HttpUtils;
import com.qbao.result.AjaxResult;
import com.qbao.security.CasUser;

/**
 * Created by zhangchanghong on 16/3/17.
 */
@Component
public class BaseController {
    private final static Logger LOG = LoggerFactory.getLogger(BaseController.class);

    public String getCurrentUsername() {
        String username = "";
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return username;
        }
        Authentication authentication = context.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
//        return "syy002";
    }

    public long getCurrentUserId(HttpServletRequest request) {
        long userId = 0L; //TODO 小登录ok后,修改userId默认值
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return userId;
        }
        Authentication authentication = context.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CasUser) {
                userId = ((CasUser) principal).getUserId();
            }
        }
//        if (userId == 0) {
//            if (isAjax(request)) {
//                throw new AjaxNotLoginException();
//            } else {
//                throw new NotLoginException();
//            }
//        }
        return userId;
  //    return 152259;
    }

    public String getCurrentUserMobile() {
        String mobile = null;
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return mobile;
        }
        Authentication authentication = context.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CasUser) {
                mobile = ((CasUser)principal).getMobile();
            }
        }
        return mobile;
//        return "15212341110";
    }

    public String getCurrentNickname() {
        String nickname = "宝粉";
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return nickname;
        }
        Authentication authentication = context.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CasUser) {
                nickname = ((CasUser) principal).getNickName();
            }
        }
        return nickname;
    }

    private boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }

    public Long getUserInfoCteateTime() {
        Long createTime = 0l;
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return createTime;
        }
        Authentication authentication = context.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CasUser) {
                createTime = ((CasUser) principal).getCreateTime();
            }
        }
        return createTime;
//        return DateUtil.parseDate("2015-02-01",DateUtil.YYYY_MM_DD).getTime();
    }

    public String getReflushFlag() {
        int[] array = {0,1,2,3,4,5,6,7,8,9};
        Random rand = new Random();
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        int result = 0;
        for(int i = 0; i < 9; i++)
            result = result * 10 + array[i];
        return String.valueOf(result);
    }
    /**
     * 发送请求
     *
     * @param url 请求的url get请求
     * @return
     */
    public AjaxResult sendRequestByGet(String url) {

        try {
            String response = HttpUtils.get(url);

            Map responseMap = JSON.parseObject(response, Map.class);

            String code = (String) responseMap.get("returnCode");

            if (code != null && code.equals("1000")) {
                return AjaxResult.success(responseMap.get("data"));
            } else if (code != null && code.equals("1001")) {
                LOG.error("BaseController-sendRequest response code = 1001 url = {}, response = {}", url, response);
                return AjaxResult.failed(responseMap.get("returnMsg").toString());
            }
            LOG.error("BaseController-sendRequestByGet no found returnCode response = {}", response);
        } catch (Exception e) {
            LOG.error("BaseController-sendRequestByGet error url = {},errorMessage = {}", url, e.getMessage());
        }

        return AjaxResult.failed("发送请求获取数据失败");
    }
    /**
     * 发送请求
     *
     * @param url 请求的url get请求
     * @return
     */
    public AjaxResult sendRequestByPost(String url, List<NameValuePair> params) {

        try {
            String response = HttpUtils.post(url,params);

            Map responseMap = JSON.parseObject(response, Map.class);

            String code = (String) responseMap.get("returnCode");

            if (code != null && code.equals("1000")) {
                return AjaxResult.success(responseMap.get("data"));
            } else if (code != null && code.equals("1001")) {
                LOG.error("BaseController-sendRequest response code = 1001 url = {}, response = {}", url, response);
                return AjaxResult.failed(responseMap.get("returnMsg").toString());
            }
            LOG.error("BaseController-sendRequestByPost no found returnCode response = {}", response);
        } catch (Exception e) {
            LOG.error("BaseController-sendRequestByPost error url = {},errorMessage = {}", url, e.getMessage());
        }

        return AjaxResult.failed("发送请求获取数据失败");
    }
}
