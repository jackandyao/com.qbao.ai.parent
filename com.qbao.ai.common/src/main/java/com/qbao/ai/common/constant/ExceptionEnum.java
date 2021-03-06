package com.qbao.ai.common.constant;

public enum ExceptionEnum {
	// 其他基本公用异常key范围：1~999
	USER_QUERY_THTOUGH_UC_EXCEPTION(500,"通过用户中心查询用户错误"),
	USER_QUERY_BLANCE_PAY_EXCEPTION(600,"通过支付中心查询用户余额错误"),
	// 异常key范围：1000~1999

	// 异常key范围：2000~2999
	PARMETER_WRONG_LACK(2001,"参数错误"),
	IMG_MAX_SIZE(2002,"图片大小不能超过5MB"),
	IMG_TYPE(2003,"不支持的文件格式"),
	PlAN_DB_EXCEPTION(2006, "数据库异常"),
	PlAN_IMAGE_EXCEPTION(2008, "图片上传异常"),

	// 异常key范围：4000~4999
	PARAMETER_WRONG_EXCEPTION(4000,"参数错误"),
	USER_NOT_AGREE_PROTOCOL_EXCEPTION(4001,"未同意协议"),
	BLANCE_NOT_ENOUGH_EXCEPTION(4012,"余额不足"),
	RECEIVED_PAY_FAILED_EXCEPTION(4013,"支付失败"),
	PAY_CENTER_IS_BUSY_EXCEPTION(4014,"支付超时，请5分钟后查询流水记录查看领取情况或拨打客服电话：400-155-8899。"),


	AGENT_CANNEL_CLOSE_DUPLICATE_EXCEPTION(4060,"请不要重复结束"),
	AGENT_CANNEL_PAY_CENTER_IS_BUSY_EXCEPTION(4061,"支付系统繁忙，请稍后再查看结果"),
	//100000-100010
	ASS_SESSION_TIMEOUT_ERROR_EXCEPTION(100001,"登录超时或Session丢失"),
	AGENT_USER_TASK_LIST_PARAM_ERROR_EXCEPTION(100002,"参数错误"),
	ASS_ALLOW_IP_ERROR_EXCEPTION(100003,"IP不在白名单之内"),
	ASS_ALLOW_USER_PASS_EXCEPTION(100005,"白名单0元订阅"),
	ASS_IS_USER_LOGIN_EXCEPTION(100006,"外部判断是否登录"),
	ASS_IS_SUBSCRIBED_EXCEPTION(100000,"首页"),
	ASS_NOT_SUBSCRIBED_EXCEPTION(100004,"用户未订阅"),
	ASS_GO_LOGIN_EXCEPTION(100001,"跳转大登陆"),
	ASS_NOT_SUBSCRIBED_NEW_USER_EXCEPTION(100007,"新用户未订阅"),
	ASS_NOT_AUTHORIZE_USER_EXCEPTION(100008,"未授权用户登录");






	public int key;
	public String desc;

	private ExceptionEnum(int key, String desc) {
		this.key = key;
		this.desc = desc;
	}
}
