package com.qbao.ai.parse.factory;

import com.qbao.ai.parse.service.IParseService;

public class ParseFactory {

	private static String parseServiceClazz;
	private static IParseService instance;
	public static IParseService getInstance(String clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		if(instance==null||parseServiceClazz==null||!parseServiceClazz.equals(clazz)) {
			instance = (IParseService) Class.forName(clazz).newInstance();
			parseServiceClazz = clazz;
		}
		return instance;
	}
	
}
