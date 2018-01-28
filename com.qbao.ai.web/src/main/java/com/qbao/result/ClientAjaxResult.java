package com.qbao.result;

/**
 * 客户端报文
 *
 * @author by zhangchanghong on 15/12/7.
 */
public class ClientAjaxResult {
    private String errorMsg;
    private Object data;
    private Integer responseCode = 1000;//1000 :  访问正常  ,1001：当前接口弃用需要客户端强制升级 ,1002：维护中 (提示消息放入errorMsg 字段中),1003：当前访问的接口有新版本可使用, 1004： jsession失效 ,1005：接口异常或错误
    private Integer errorCode =0;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public ClientAjaxResult(Object data, String errorMsg){
        this.errorMsg=errorMsg;
        this.data=data;
    }

    public ClientAjaxResult(Object data, String errorMsg, Integer responseCode, Integer errorCode){
        this.errorMsg=errorMsg;
        this.data=data;
        this.responseCode = responseCode;
        this.errorCode = errorCode;
    }



    public static final ClientAjaxResult success() {
        return new ClientAjaxResult( null, "");
    }

    public static final ClientAjaxResult success(Object data) {
        return new ClientAjaxResult( data, "");
    }

    public static final ClientAjaxResult success(Object data, String msg) {
        return new ClientAjaxResult(data,msg);
    }


    public static final ClientAjaxResult failed() {
        return new ClientAjaxResult(null, null, 1005,null);
    }

    public static final ClientAjaxResult failed(String errorMsg) {
        return new ClientAjaxResult(null, errorMsg, 1005,null);
    }

    public static final ClientAjaxResult failed(String errorMsg, Integer responseCode) {
        return new ClientAjaxResult(null, errorMsg, responseCode,null);
    }
    public static final ClientAjaxResult failed(String errorMsg, Integer responseCode, Integer errorCode) {
        return new ClientAjaxResult(null, errorMsg, responseCode,errorCode);
    }

    public static final ClientAjaxResult failed(Object data, String errorMsg) {
        return new ClientAjaxResult(data, errorMsg, 1005,null);
    }

    public static final ClientAjaxResult success(Object data, Integer errorCode){
        return new ClientAjaxResult(data, null, 1000,errorCode);
    }
    public String getErrorMsg() {
        return errorMsg;
    }

    public Object getData() {
        return data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
