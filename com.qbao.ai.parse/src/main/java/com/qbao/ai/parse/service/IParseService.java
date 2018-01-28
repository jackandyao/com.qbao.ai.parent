package com.qbao.ai.parse.service;

import java.util.List;
import java.util.Set;

import com.qbao.ai.parse.utils.Pair;
import com.qbao.ai.parse.utils.ParserStatus;

public interface IParseService {
	List<String> parseReturnWords(String line);
	
	boolean parse(String line);
	
	Pair<ParserStatus, Set<String>> parseReturnStatus(String line);
}
