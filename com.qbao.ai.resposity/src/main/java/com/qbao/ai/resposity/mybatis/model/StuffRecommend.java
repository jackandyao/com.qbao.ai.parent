package com.qbao.ai.resposity.mybatis.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author xueming
 * @email louxueming@qbao.com
 * @date 2017-03-06 15:12:58
 */
public class StuffRecommend implements Serializable {
    private static final long serialVersionUID = "$Id: StuffRecommend.java 156 2017-04-19 01:52:16Z liaijun $".hashCode();
    private long userId;
    private String stuffIds;
    private Date createTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getStuffIds() {
        return stuffIds;
    }

    public void setStuffIds(String stuffIds) {
        this.stuffIds = stuffIds;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
