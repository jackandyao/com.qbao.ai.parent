package com.qbao.ai.resposity.mybatis.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shuaizhihu on 2016/11/30.
 */
public class QuestionInfo implements Serializable{
	
	private static final long serialVersionUID = "$Id$".hashCode();
	
    private long id;
    private String question;
    private String answer;
    private String linkIds;
    private long dirId;
    private long rootDirId;
    private int status;
    private Date createTime;
    private Date updateTime;

    public String getLinkIds() {
        return linkIds;
    }

    public void setLinkIds(String linkIds) {
        this.linkIds = linkIds;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer.replaceAll("\n","<br>");
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getDirId() {
        return dirId;
    }

    public void setDirId(long dirId) {
        this.dirId = dirId;
    }

    public long getRootDirId() {
        return rootDirId;
    }

    public void setRootDirId(long rootDirId) {
        this.rootDirId = rootDirId;
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
}
