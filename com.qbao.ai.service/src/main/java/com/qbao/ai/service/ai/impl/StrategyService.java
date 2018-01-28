package com.qbao.ai.service.ai.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.qbao.ai.parse.utils.ConfigurationUtil;
import com.qbao.ai.parse.utils.Pair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.qbao.ai.parse.service.IParseService;
import com.qbao.ai.parse.utils.ParserStatus;
import com.qbao.ai.service.ai.IBaseAnswerService;
import org.springframework.stereotype.Service;

/**
 * @author liaijun
 * @createTime 17/4/14 下午2:28
 * $$LastChangedDate: 2017-04-22 17:33:23 +0800 (Sat, 22 Apr 2017) $$
 * $$LastChangedRevision: 190 $$
 * $$LastChangedBy: liaijun $$
 */
@Service
public class StrategyService implements IBaseAnswerService{
    Logger logger= Logger.getLogger(StrategyService.class);
    private static Map<String,IBaseAnswerService> map=new HashMap<>();
    @Autowired
    private GoodsListService goodsListService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    QuestionAnswerService questionAnswerService;
    @Autowired
    WeatherAnswerService weatherAnswerService;
    @Autowired
    WuruService wuruService;
    @Autowired
    CustomService customService;
    @Autowired
    BaiduListService baiduListService;
    @Override
    public Map<String,Object> answerInfo(String question, int device, String lat, String lon, Long userId, int page, int size,Set<String> set) {
        logger.info("开始调用分词接口>>>>>>>>>>question="+question);
        IParseService parseService = ConfigurationUtil.getParseServiceInstance();
        Pair<ParserStatus, Set<String>> pair=parseService.parseReturnStatus(question);

        logger.info("Question="+question+"对应的分词接口返回数据>>>>>>>>>>>pair="+pair);
        if(pair!=null) {
            ParserStatus parserStatus=pair.getKey();
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>parserStatus="+parserStatus);
            IBaseAnswerService service = getBean(parserStatus.getValue());
            set=pair.getValue();
            return service.answerInfo(question,device,lat,lon,userId,page,size,set);
        }else{
            return wuruService.answerInfo(question,device,lat,lon,userId,page,size,set);
        }
    }

    public IBaseAnswerService  getBean(String bean){
        map.put("TQ",weatherAnswerService);
        map.put("G_1",goodsService);
        map.put("G_M",goodsListService);
        map.put("M_X",questionAnswerService);
        map.put("M_W",baiduListService);
        map.put("N",questionAnswerService);
        map.put("W",wuruService);
        return map.get(bean);
    }

    public Map<String,Object> getCustomerService(){
        return customService.answerInfo("",0,"","",null,0,0,null);
    }


    static {

        IParseService parseService = ConfigurationUtil.getParseServiceInstance();
        System.out.println(parseService.parseReturnStatus("天气"));
    }
}
