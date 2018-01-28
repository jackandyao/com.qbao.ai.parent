package com.qbao.ai.common.util;

/**
 * Created by xueming on 2017/3/27.
 */
public class ExceptionAlarmUtils {
    private ExceptionAlarmUtils() {
    }
  //  private static ErrorPhoneAlaramService errorPhoneAlaramService = new ErrorPhoneAlaramService();
    public static  void sendAlarm(String requestPath,String requestParam,String exceptionName,String exceptionDetail){
        synchronized (ExceptionAlarmUtils.class) {
            StringBuffer sb = new StringBuffer();
            sb.append("路径："+requestPath);
            sb.append("参数："+requestParam);
            sb.append("异常名称："+exceptionName);
            sb.append("异常详情："+exceptionDetail);
//            if(sb.length()>100){
//                errorPhoneAlaramService.sendPhoneAlaramMessage(sb.substring(0,100).toString());
//            }else{
//                errorPhoneAlaramService.sendPhoneAlaramMessage(sb.toString());
//            }
        }



    }
}
