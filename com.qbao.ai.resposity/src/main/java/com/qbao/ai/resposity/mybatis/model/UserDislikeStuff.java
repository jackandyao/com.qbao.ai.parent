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
public class UserDislikeStuff implements Serializable {
    private static final long serialVersionUID = "$Id: UserDislikeStuff.java 156 2017-04-19 01:52:16Z liaijun $".hashCode();
    private long id;
    private long userId;
    private String stuffIds;
    private Date createTime;
    private Date updateTime;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
