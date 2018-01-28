package com.qbao.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qbao.ai.util.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author by zhangchanghong on 15/12/6.
 */

@Component
public class UserInterfaceApi {
    private final static Logger log = LoggerFactory.getLogger(UserInterfaceApi.class);

    public static final String default_nickname = "宝粉";

    public static final String USER_CAS_ID_KEY = "id";
    public static final String USER_NAME_KEY = "username";
    public static final String NICK_NAME_KEY = "nickName";
    public static final String USER_HYID_ID_KEY = "hyipUserId";
    public static final String USER_ENABLE_KEY = "enabled";
    public static final String USER_MOBILEPHONE_KEY = "mobile";
    public static final String USER_EMAIL_KEY = "email";
    public static final String USER_CREATE_TIME = "createTime";
    public static final String MAN = "M";
    public static final String WOMEN = "F";

    public List<String> getUserRoleByUserName(final String username) {
        final String userUrl = "http://api.user.qbao.com/api/get/userRole/%s/new";
        try {
            List<String> list = HttpUtils.get(String.format(userUrl, username), String.class);
            if (null != list) {
                // log.info("根据用户名:{}, 获取用户角色接口");
                return list;
            }
        } catch (Exception e) {
            log.error("根据用户名:{}, 获取用户角色接口,错误:", username, e);
        }
        return Collections.emptyList();
    }

    public Map<String, Object> getUserBaseInfo(final String username) {
        final String userUrl = "http://api.user.qbao.com/api/get/user/%s/%s.html";
        final String securityKey = "dd44db9bf8d04a3eb9b2837fb44e2333";
        String sign = "username=" + username + "&securityKey=" + securityKey;
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        sign = encoder.encodePassword(sign, securityKey);
        try {
            String json = HttpUtils.get(String.format(userUrl, username, sign));
            log.info("UserInterfaceApi-getUserBaseInfo():用户基本信息接口返回:{}", json);
            if (StringUtils.isNotEmpty(json)) {
                JSONObject userInfo = JSON.parseObject(json);
                String code = userInfo.get("code").toString();
                if ("1".equals(code)) {
                    json = userInfo.get("data").toString();
                    Map<String, Object> map2 = (Map<String, Object>) JSON.parse(json);
                    String jsonString = JSON.toJSONString(map2);
                    log.info("UserInterfaceApi-getUserBaseInfo():用户信息{}", jsonString);
                    return map2;
                }
            }
        } catch (Exception e) {
            log.error("根据用户名:{}, 获取用户信息接口,错误:", username, e);
        }

        return null;
    }
}
