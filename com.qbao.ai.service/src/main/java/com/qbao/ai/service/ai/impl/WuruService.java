package com.qbao.ai.service.ai.impl;

import com.qbao.ai.common.constant.Constant;
import com.qbao.ai.model.dto.QuestionLinkDto;
import com.qbao.ai.resposity.mybatis.dao.QuestionLinkDao;
import com.qbao.ai.resposity.mybatis.model.QuestionLink;
import com.qbao.ai.service.ai.IBaseAnswerService;
import com.qbao.ai.util.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @author liaijun
 * @createTime 17/4/14 下午2:28
 * $$LastChangedDate: 2017-05-23 11:01:30 +0800 (Tue, 23 May 2017) $$
 * $$LastChangedRevision: 233 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class WuruService implements IBaseAnswerService{
    Logger logger= Logger.getLogger(WuruService.class);
    @Autowired
    private QuestionLinkDao linkDao;

    @Override
    public Map<String,Object> answerInfo(String question, int device, String lat, String lon, Long userId, int page, int size,Set<String> set) {
        QuestionLink link=linkDao.findQuestionLinkById(30L);
        QuestionLinkDto dto=new QuestionLinkDto();
        BeanUtils.copy(link,dto);
        dto.setValue(getAnswer()+",去有好货逛逛吧!!!");
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
        response.put("questionType","normal");
        response.put("title","小智给您找到的答案");
        response.put("success", true);
        response.put("message", "Ok");
        response.put("userId",userId);
        response.put("data",dto);
        return response;
    }

    public static void main(String[] args) {
        int max=20;
        int min=10;
        Random random = new Random();

        int s = random.nextInt(2);
        System.out.println(s);
    }
    public String  getAnswer(){
        Map<Integer,String> map=new HashMap<Integer,String>();
        map.put(1,"说的不是小智吧");
        map.put(2,"讲文明，小智和你一起愉快的玩耍");
        map.put(3,"忘记不愉快，去有好货逛逛。立即前去");
        map.put(4,"别气别气，气坏身子不划算");
        map.put(5,"小智很文明哟，客官也要文明哟");
        map.put(6,"客官请息怒，喝口茶慢慢跟小智聊");
        map.put(7,"要注意文明用语哟");
        map.put(8,"在骂我，我就跟你拼命");
        map.put(9,"亲爱的，干吗骂小智呢，我很乖的~~");
        map.put(10,"要是你心情不好，你就骂呗，反正你又咬不到我 ");
        map.put(11,"小智是文明人，不跟你一般见识~~ ");
        map.put(0,"一大早火气那么大，来罐凉茶降降火吧");
        Random random = new Random();
        int s = random.nextInt(12);
        return map.get(s);
    }
}
