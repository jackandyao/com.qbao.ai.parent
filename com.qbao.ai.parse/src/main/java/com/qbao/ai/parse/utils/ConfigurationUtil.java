package com.qbao.ai.parse.utils;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import com.qbao.ai.parse.factory.ParseFactory;
import com.qbao.ai.parse.service.IParseService;   
  
  
public class ConfigurationUtil {
	private static Logger logger = Logger.getLogger("ConfigurationUtil");
	private static String FILENAME = "parse.properties";
	private static IParseService instance;
	public static IParseService getParseServiceInstance(){
		if(instance!=null) return instance;
		Properties prop = new Properties();//属性集合对象      
		InputStream fis;
		try {

			fis = ConfigurationUtil.class.getClassLoader().getResourceAsStream(FILENAME);
			prop.load(fis);//将属性文件流装载到Properties对象中
			String clazz = prop.getProperty("parseService");
			if(clazz==null) {
				throw new Exception("实例化解析类出错");
			}
			instance = ParseFactory.getInstance(clazz);
			logger.info("已实例化生成解析类：" + clazz);
		} catch (Exception e) {
			logger.warning(e.getMessage());
		}
		return instance;
	}
	
} 
