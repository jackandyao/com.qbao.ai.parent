package com.qbao.ai.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liaijun
 * @createTime 17/3/7 下午7:36
 * $$LastChangedDate: 2017-03-31 14:16:40 +0800 (周五, 31 三月 2017) $$
 * $$LastChangedRevision: 606 $$
 * $$LastChangedBy: wangping $$
 */
public class Constant {
    //1已返券
    public static Integer RETURN=1;
    //0: 未返券
    public static Integer UNRETURN=0;

    // 服务器返回成功
    public  static final int   RESPONSE_CODE_SCUESS = 1000;

    // 有错误
    public  static final int   RESPONSE_CODE_HAS_ERROR = 1005;
    //调用接口成功返回0
    public static final String SUCCESSCODE="0";

    public static String FAN = "返";

    public static String BAO = "宝券";

    public static String BAOQUAN = "%宝券";

    public static Map<String, String> map = new HashMap<String, String>();

    public static int IOS = 1;
    public static int ANDROID = 2;

    public static Long  DEFAULT_USER_ID = 0L;

    public static String AI_CITY="http://gc.ditu.aliyun.com/regeocoding?type=100&l=";

    public static String AI_WEATHER="http://wthrcdn.etouch.cn/weather_mini?city=";

    public static String WEATHER_STATUS="1000";

    public static String BAIDU_URL="http://www.baidu.com/s?pn=";



}
