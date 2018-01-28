package com.qbao.ai.parse.filter;


import java.util.LinkedList;
import java.util.List;

import com.qbao.ai.parse.utils.ParserStatus;

public class WordNode {

	private int value;
	
	private List<WordNode> subNodes;
	
	private boolean isLast;//默认false
	private String word;

	private ParserStatus parserStatus;//词的类型
//	public WordNode(int value){
//		this.value = value;
//	}
	
	public WordNode(int value, boolean isLast){
		this.value = value;
//		if(!this.isLast){//如果已经是某一个敏感词的last，那就是last
		this.isLast = isLast;
		
//		}
		
	}
	
	public WordNode(int value, boolean isLast,ParserStatus parserStatus, String word){
		this.value = value;
//		if(!this.isLast){//如果已经是某一个敏感词的last，那就是last
		this.isLast = isLast;
		if(isLast) {
			this.parserStatus = parserStatus;
			this.word = word;
		}
//		}
		
	}
	
	/**
	 * 
	 * @param subNode
	 * @return 就是传入的subNode
	 */
	private WordNode addSubNode(final WordNode subNode){
		if(subNodes==null)
			subNodes = new LinkedList<WordNode>();
		subNodes.add(subNode);
		return subNode;
	}
	
	/**
	 * 有就直接返回该子节点， 没有就创建添加并返回该子节点
	 * @param value
	 * @return
	 */
	public WordNode addIfNoExist(final int value, final boolean isLast, final ParserStatus parserStatus, String word){
		if(subNodes==null){
			return addSubNode(new WordNode(value, isLast,parserStatus, word));
		}
		for(WordNode subNode : subNodes){
			if(subNode.value==value){
				if(!subNode.isLast && isLast){
					subNode.isLast = true;
				}
				if(isLast) {
					subNode.parserStatus = parserStatus;//增加词的类型
					subNode.word = word;
				}
				return subNode;
			}
		}
		return addSubNode(new WordNode(value, isLast,parserStatus, word));
	}
	
	public WordNode querySub(final int value){
		if(subNodes==null){
			return null;
		}
		for(WordNode subNode : subNodes){
			if(subNode.value==value)
				return subNode;
		}
		return null;
	}
	
	public boolean isLast(){
		return isLast;
	}
	
	public void setLast(boolean isLast){
		this.isLast = isLast;
	}
	
	public ParserStatus getParserStatus() {
		return parserStatus;
	}

	public void setParserStatus(ParserStatus parserStatus) {
		this.parserStatus = parserStatus;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public int hashCode() {
		return value;
	}
	
}
