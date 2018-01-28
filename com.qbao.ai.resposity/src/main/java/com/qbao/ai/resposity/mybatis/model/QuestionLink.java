package com.qbao.ai.resposity.mybatis.model;

import java.io.Serializable;

/**
 * Created by shuaizhihu on 2016/12/1.
 */
public class QuestionLink implements Serializable{
	
	private static final long serialVersionUID = "$Id$".hashCode();
	
    private long id;
    private String name;
    private String link;
    private int  type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
