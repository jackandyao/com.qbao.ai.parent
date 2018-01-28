package com.qbao.ai.parse.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.apdplat.word.segmentation.Word;

import com.qbao.ai.parse.filter.WordFilter;
import com.qbao.ai.parse.service.IParseService;
import com.qbao.ai.parse.utils.Pair;
import com.qbao.ai.parse.utils.ParserStatus;
import com.qbao.ai.parse.utils.ParserUtil;

public class TextParseService implements IParseService {

	private Logger logger = Logger.getLogger("TextParseService");
	public List<String> parseReturnWords(String line) {
		line = ParserUtil.beforeParse(line);
		List<String> list = new ArrayList<String>();
		if(WordFilter.valid(line)){
			List<Word> wordList = WordFilter.splitWord(line);
			if(wordList!=null){
				for(Word word : wordList) list.add(word.getText());
			}
		}
		logger.info(line +" 的解析结果是： "+list);
		return list;
	}
	@Override
	public boolean parse(String line) {
		line = ParserUtil.beforeParse(line);
		return WordFilter.valid(line);
	}
	
	@Override
	public Pair<ParserStatus, Set<String>> parseReturnStatus(String line) {
		Pair<ParserStatus, Set<String>> pair = this.getParseStatus(line);
		logger.info(line +" 的解析结果,status: "+pair.getKey() + " , keywords:" + pair.getValue());
		return pair;
	}
	
	public Pair<ParserStatus, Set<String>> getParseStatus(String line) {
		line = ParserUtil.beforeParse(line);
		Map<ParserStatus, Set<String>> statusMap = WordFilter.doFilterReturnStatusMap(line);
		if(statusMap.get(ParserStatus.MGC) != null){
			if(statusMap.get(ParserStatus.QBXG) != null) {
				Set<String> set = new HashSet<String>();
				set.addAll(statusMap.get(ParserStatus.QBXG));
				set.addAll(statusMap.get(ParserStatus.MGC));
				return new Pair<ParserStatus, Set<String>>(ParserStatus.MGC_QBXG, set );
			}
			return new Pair<ParserStatus, Set<String>>(ParserStatus.MGC_QBWG,statusMap.get(ParserStatus.MGC));
		} else if(statusMap.get(ParserStatus.WRC) != null) {
			return new Pair<ParserStatus, Set<String>>(ParserStatus.WRC,statusMap.get(ParserStatus.WRC));
		} else if(statusMap.get(ParserStatus.TQ)!=null) {
			return new Pair<ParserStatus, Set<String>>(ParserStatus.TQ,statusMap.get(ParserStatus.CITY));
		} else if(statusMap.get(ParserStatus.GOODS) != null){
			if(statusMap.get(ParserStatus.GOODS).size()>1) return new Pair<ParserStatus, Set<String>>(ParserStatus.GOODS_MANY,statusMap.get(ParserStatus.GOODS));
			else return new Pair<ParserStatus, Set<String>>(ParserStatus.GOODS_ONE,statusMap.get(ParserStatus.GOODS));
		} else return new Pair<ParserStatus, Set<String>>(ParserStatus.NORNAL,null);
		
	}

}
