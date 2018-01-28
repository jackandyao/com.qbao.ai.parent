package com.qbao.ai.parse.main;

import com.qbao.ai.parse.service.IParseService;
import com.qbao.ai.parse.utils.ConfigurationUtil;

public class ParseMain {

	public static void main(String[] args) {
		IParseService parseService = ConfigurationUtil.getParseServiceInstance();
		//有好货v1智能语义解析测试样例
//		System.out.print(parseService.parse("账户密码忘记了"));
//
//		System.out.print(parseService.parse("周末去旅游"));
//		parseService.parse("hujintao爱旅游");
//		parseService.parse("张小雷");
//		System.out.println(parseService.parse("A"));
		
		//有好货v2智能语义解析测试样例
//		System.out.println(parseService.parseReturnStatus("天气").getValue());
//		System.out.println(parseService.parseReturnStatus("三星手机").getValue());
//		System.out.println(parseService.parseReturnStatus("手机西瓜").getValue());
//		System.out.println(parseService.parseReturnStatus("苹果手机").getValue());
//		System.out.println(parseService.parseReturnStatus("西瓜").getValue());
//		System.out.println(parseService.parseReturnStatus("上海天气怎么样").getValue());
//		System.out.println(parseService.parseReturnStatus("sb").getValue());
//		System.out.println(parseService.parseReturnStatus("习近平").getValue());
//		System.out.println(parseService.parseReturnStatus("钱宝要跑路了").getKey());
//		System.out.println(parseService.parseReturnStatus("钱宝要跑路了").getValue());
//		System.out.println(parseService.parseReturnStatus("钱旺是骗子").getKey());
//		System.out.println(parseService.parseReturnStatus("钱旺是骗子").getValue());
//		System.out.println(parseService.parseReturnStatus("QBAO是骗子").getKey());
//		System.out.println(parseService.parseReturnStatus("QBAO是骗子").getValue());
//		System.out.println(parseService.parseReturnStatus("我要找MM").getValue());
		
		System.out.println(parseService.parseReturnStatus("李小龙").getKey());
		System.out.println(parseService.parseReturnStatus("李小龙").getValue());
		System.out.println(parseService.parseReturnStatus("天气").getKey());
		System.out.println(parseService.parseReturnStatus("天气").getValue());
	}
}
