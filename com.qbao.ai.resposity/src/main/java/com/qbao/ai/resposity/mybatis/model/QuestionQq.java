package com.qbao.ai.resposity.mybatis.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shuaizhihu on 2016/12/1.
 */
public class QuestionQq implements Serializable{
	
	private static final long serialVersionUID = "$Id: QuestionQq.java 239 2017-06-01 03:45:59Z zhangjun $".hashCode();
	private long id;
    private String qq;
    private int status;

    //创建时间
    private Date createTime;

    //更新时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    private Date updateTime;
}
