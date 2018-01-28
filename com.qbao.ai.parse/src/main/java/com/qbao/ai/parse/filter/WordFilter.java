package com.qbao.ai.parse.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import org.apdplat.word.tagging.PartOfSpeechTagging;

import com.google.common.collect.Maps;
import com.qbao.ai.parse.utils.ConfigurationUtil;
import com.qbao.ai.parse.utils.JDBCUtil;
import com.qbao.ai.parse.utils.ParserStatus;
import com.qbao.ai.parse.utils.ParserUtil;

/**
 * 思路：	创建一个FilterSet，枚举了0~65535的所有char是否是某个敏感词开头的状态
 * 			
 * 			判断是否是 敏感词开头
 * 			|			|
 * 			是			不是
 * 		获取头节点			OK--下一个字
 * 	然后逐级遍历，DFA算法
 * 
 */
public class WordFilter {
	
//	private static WordNode[] nodes = new WordNode[65536];省6W个句柄的空间呗，测试发现，相比使用65536长度数组方式，过滤速度也提高了
	private static final FilterSet set = new FilterSet();
	private static Map<Integer, WordNode> nodes;
	private static Map<Integer, WordNode> nodesTmp;
	private static Logger logger = Logger.getLogger("WordFilter");
	private static String wordSetDir = "wordset";
	
	private static Timer timer;

	static {
		startTask();
	}
	private static void startTask() {
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				try {
					long a = System.nanoTime();
					init();
					a = System.nanoTime()-a;
					logger.info("加载时间 : "+a/1000000+"ms");
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("初始化过滤器失败");
				}
			}
		}, 0, 24 * 60 * 60 * 1000);

	}
	
	//词汇路径及标识设置
	private static void init(){
		nodesTmp = new HashMap<Integer, WordNode>(1024, 1);
		//获取敏感词
		getWordsAndTagFromDB(wordSetDir + "/kw.txt",ParserStatus.GOODS);//从stuff_promotion数据库获得商品关键词
//		getWordsAndTag(wordSetDir + "/kw.txt", ParserStatus.GOODS);//获取全站用户搜索有结果的关键词
		getWordsAndTag(wordSetDir + "/百度敏感词.txt", ParserStatus.MGC);
		getWordsAndTag(wordSetDir + "/word.txt", ParserStatus.MGC);
		getWordsAndTag(wordSetDir + "/weather.txt", ParserStatus.TQ);
		getWordsAndTag(wordSetDir + "/wuruci.txt", ParserStatus.WRC);
		getWordsAndTag(wordSetDir + "/qbxg.txt", ParserStatus.QBXG);
		getWordsAndTag(wordSetDir + "/city.txt", ParserStatus.CITY);
		//加载分词器
//		splitWord("init");
		nodes = nodesTmp;
		nodesTmp = null;
	}
	
	private static void getWordsAndTagFromDB(String path, ParserStatus parserStatus) {
		Set<String> totalPrdFilter = new HashSet<String>();
//		JDBCUtil jdbcAI = new JDBCUtil();
//		jdbcAI.createConnAI();
//		ResultSet rsAI = jdbcAI.getResultSet("select question from question_info where status = 1");
//		try {
//			while(rsAI.next()){
//				String question = ParserUtil.beforeParse(rsAI.getString(1));
//				if(StringUtils.isEmpty(question))
//					continue;
//				List<Word> wordsSeged = WordSegmenter.segWithStopWords(question);
//				PartOfSpeechTagging.process(wordsSeged);
//				for (Word word : wordsSeged) {
//					if(word.getPartOfSpeech().getPos().contains("n") &&word.getText().length() > 1){
//						questionFilter.add(word.getText());
//					}
//				}
//			}
//		} catch (SQLException e) {
//			logger.warning(e.getMessage());
//		}
//		jdbcAI.closeConn();
		
		
		BufferedReader br = null;
		InputStream fis;
		try {

			fis = ConfigurationUtil.class.getClassLoader().getResourceAsStream(path);
			br = new BufferedReader(new InputStreamReader(fis));
			for(String buf="";(buf = br.readLine())!=null;){
				buf = ParserUtil.beforeParse(buf);
				if(StringUtils.isEmpty(buf))
					continue;
				totalPrdFilter.add(buf);
			}
		} catch (Exception e) {
			logger.warning(e.getMessage());
		}finally{
			try {
				if(br != null)
					br.close();
			} catch (IOException e) {
			}
		}
		
		List<String> words = new ArrayList<String>(1200);
		JDBCUtil jdbcutil = new JDBCUtil();
		jdbcutil.createConnStuff();
		try {
			
			ResultSet rs = jdbcutil.getResultSet("select name from stuff_promotion where status = 1");
			while(rs.next()){
				String name = ParserUtil.beforeParse(rs.getString(1));
				if(StringUtils.isEmpty(name))
					continue;
				List<Word> wordsSeged = WordSegmenter.segWithStopWords(name);
				PartOfSpeechTagging.process(wordsSeged);
				for (Word word : wordsSeged) {
					if(word.getPartOfSpeech().getPos().contains("n") &&word.getText().length() > 1 && totalPrdFilter.contains(word.getText())){
						words.add(word.getText());
					}
				}
				
			}
		} catch (Exception e) {
			logger.warning(e.getMessage());
		}finally{
			jdbcutil.closeConn();
		}
		//获取敏感词
		addSensitiveWord(words, parserStatus);
	}

	private static void getWordsAndTag(String path, ParserStatus parserStatus){
		List<String> words = new ArrayList<String>(1200);;
		BufferedReader br = null;
		InputStream fis;
		try {

			fis = ConfigurationUtil.class.getClassLoader().getResourceAsStream(path);
			br = new BufferedReader(new InputStreamReader(fis));
			for(String buf="";(buf = br.readLine())!=null;){
				buf = ParserUtil.beforeParse(buf);
				if(StringUtils.isEmpty(buf))
					continue;
				words.add(buf);
			}
		} catch (Exception e) {
			logger.warning(e.getMessage());
		}finally{
				try {
					if(br != null)
						br.close();
				} catch (IOException e) {
				}
			}
		//获取敏感词
		addSensitiveWord(words, parserStatus);
	}
	
	private static void addSensitiveWord(final List<String> words, ParserStatus parserStatus){
		char[] chs;
		int fchar;
		int lastIndex;
		WordNode fnode;
		for(String curr : words){
			chs = curr.toCharArray();
			fchar = chs[0];
			if(!set.contains(fchar)){//没有首字定义
				set.add(fchar);//首字标志位	可重复add,反正判断了，不重复了
				fnode = new WordNode(fchar, chs.length==1, parserStatus, curr);
				nodesTmp.put(fchar, fnode);
			}else{
				fnode = nodesTmp.get(fchar);
				if(!fnode.isLast() && chs.length==1){
					fnode.setLast(true);
					fnode.setParserStatus(parserStatus);
				}
			}
			lastIndex = chs.length-1;
			for(int i=1; i<chs.length; i++){
				fnode = fnode.addIfNoExist(chs[i], i==lastIndex, parserStatus, curr);
			}
		}
	}
	
	private static final char SIGN = '*';
	public static final String doFilter(final String src){
		while(nodes==null)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		char[] chs = src.toCharArray();
		int length = chs.length;
		int currc;
		int k;
		WordNode node;
		for(int i=0;i<length;i++){
			currc = chs[i];
			if(!set.contains(currc)){
				continue;
			}
//			k=i;
			node = nodes.get(currc);//日	2
			if(node == null)//其实不会发生，习惯性写上了
				continue;
			boolean couldMark = false;
			int markNum = -1;
			if(node.isLast()){//单字匹配
				couldMark = true;
				markNum = 0;
			}
			//继续匹配，以长的优先
			k=i;
			for( ; ++k<length; ){
				
				node = node.querySub(chs[k]);
				if(node==null)//没有了
					break;
				if(node.isLast()){
					couldMark = true;
					markNum = k-i;//3-2
				}
			}
			if(couldMark){
				for(k=0;k<=markNum;k++){
					chs[k+i] = SIGN;
				}
				i = i+markNum;
			}
		}
		
		return new String(chs);
	}
	
	public static final Map<ParserStatus,Set<String>> doFilterReturnStatusMap(final String src){
		while(nodes==null)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		Map<ParserStatus,Set<String>> statusMap = Maps.newHashMap();
		char[] chs = src.toCharArray();
		int length = chs.length;
		int currc;
		int k;
		WordNode node;
		for(int i=0;i<length;i++){
			currc = chs[i];
			if(!set.contains(currc)){
				continue;
			}
//			k=i;
			node = nodes.get(currc);//日	2
			if(node == null)//其实不会发生，习惯性写上了
				continue;
			boolean couldMark = false;
			int markNum = -1;
			if(node.isLast()){//单字匹配
				couldMark = true;
				markNum = 0;
				addjustStatus(node,statusMap);
			}
			//继续匹配，以长的优先
			k=i;
			for( ; ++k<length; ){
				
				node = node.querySub(chs[k]);
				if(node==null)//没有了
					break;
				if(node.isLast()){
					couldMark = true;
					markNum = k-i;//3-2
					addjustStatus(node,statusMap);
				}
			}
			if(couldMark){
				for(k=0;k<=markNum;k++){
					chs[k+i] = SIGN;
				}
				i = i+markNum;
			}
		}
		
		return statusMap;
	}
	private static void addjustStatus(WordNode node, Map<ParserStatus, Set<String>> statusMap) {
		if(!node.isLast()) return;
		ParserStatus statu = node.getParserStatus();
		String word = node.getWord();
		if(statu != null && word != null){
			Set<String> set = statusMap.get(statu);
			if(set == null) set = new HashSet<String>();
			set.add(word);
			statusMap.put(statu, set);
		}
	}

	//返回过滤结果
	public static String filter(String line){
		if(line==null) return null;
		return WordFilter.doFilter(line);
	}
	//返回是否合法
	public static boolean valid(String line){
		if(line==null) return false;
		String filteredLine = WordFilter.doFilter(line);
		return line.equals(filteredLine);
	}
	
	//返回词性列表
	public static Map<ParserStatus,Set<String>> filterReturnStatus(String line){
		if(line==null) return null;
		return WordFilter.doFilterReturnStatusMap(line);
	}
	//返回分词结果
	public static List<Word> splitWord(String line){
		List<Word> tokens = new ArrayList<Word>();
		List<Word> words = WordSegmenter.segWithStopWords(line);
		for (Word word : words) {
			if(word.getText().length() > 1)
				tokens.add(word);
		}
		return tokens;
	}
	
	public static void main(String[] args) {
		
		
//		init();
		
		String s = "法轮功测试hujintao";
//		System.out.println("解析字数 : "+s.length());
//		String re;
//		long nano = System.nanoTime();
//		re=WordFilter.doFilter(s);
//		nano = (System.nanoTime()-nano);
//		System.out.println("解析时间 : " + nano + "ns");
//		System.out.println("解析时间 : " + nano/1000000 + "ms");
//		System.out.println(re);
//		System.out.println(re.length()==s.length());
//		System.out.println(s.equals(re));
		
		s = "天气";
		Map<ParserStatus, Set<String>> map = WordFilter.doFilterReturnStatusMap(s);
		for(Entry<ParserStatus, Set<String>> entry : map.entrySet()){
			System.out.println(entry.getKey());
			System.out.println(entry.getValue().size());
		}
	}
	
}
