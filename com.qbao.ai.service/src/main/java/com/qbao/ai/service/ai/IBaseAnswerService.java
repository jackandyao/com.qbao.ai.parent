package com.qbao.ai.service.ai;

import java.util.Map;
import java.util.Set;

/**
 * @author liaijun
 * @createTime 17/4/13 下午3:18
 * $$LastChangedDate: 2017-04-21 16:14:16 +0800 (Fri, 21 Apr 2017) $$
 * $$LastChangedRevision: 185 $$
 * $$LastChangedBy: liaijun $$
 */
public interface IBaseAnswerService {

    /**
     *
     * @param question 问题
     * @param device  设备 ios  安卓
     * @param lat  纬度
     * @param lon  经度
     * @return
     */
    Map<String,Object> answerInfo(String question, int device, String lat, String lon, Long userId, int page, int size,Set<String> set);
}
