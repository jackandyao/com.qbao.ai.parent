package com.qbao.ai.parse.utils;


public enum ParserStatus {
	MGC("敏感词", "M"),MGC_QBXG("钱宝相关敏感问题","M_X"),MGC_QBWG("钱宝无关敏感问题","M_W"),
	WRC("侮辱词", "W"),TQ("天气", "TQ"),CITY("城市","CITY"),
	GOODS("商品", "G"),GOODS_ONE("单一商品","G_1"),GOODS_MANY("多个商品","G_M"),
	NORNAL("正常问题","N"),QBXG("钱宝相关","QBXG");
	private String desc;
	private String value;
	private ParserStatus(String desc,String value){
		this.desc = desc;
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return value;
	}
	
}
