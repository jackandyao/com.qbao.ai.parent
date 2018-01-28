package com.qbao.ai.resposity.redis.common;

/**
 * @author by linhanye on 16/11/7.
 */
public enum RedisConst {

    // 用户
    SEARCH_FACETS("search:facets:map", "商品类目表", 60 * 10), //10分钟
    SEARCH_FILTER("search:filter:list", "过滤选项", 60 * 60 * 24); //1小时


    public final String key;
    public final String desc;
    public final int expired;

    RedisConst(String key, String desc, int expired) {
        this.key = "v2.3:" + key;
        this.desc = desc;
        this.expired = expired;
    }
}
