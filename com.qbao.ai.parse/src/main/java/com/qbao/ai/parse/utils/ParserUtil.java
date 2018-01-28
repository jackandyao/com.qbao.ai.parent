package com.qbao.ai.parse.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserUtil {

	private static String regEx="[\\s`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
	private static Pattern pattern = Pattern.compile(regEx, Pattern.DOTALL);
	public static String beforeParse(String line){
		if(line == null) return line;
		Matcher m = pattern.matcher(line);
		return m.replaceAll("").trim().toLowerCase();
	}
	
}
