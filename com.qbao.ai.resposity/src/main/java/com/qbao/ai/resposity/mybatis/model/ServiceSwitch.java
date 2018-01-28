package com.qbao.ai.resposity.mybatis.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 
 * @author zhangjun
 * @email sjzhangjun@qbao.com
 */
public class ServiceSwitch implements Serializable {
    private static final long serialVersionUID = "$Id$".hashCode();

    private Integer id;
    private String key;
    private Integer value;
    private Date updateTime;
    
    public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getKey() {
		return key;
	}



	public void setKey(String key) {
		this.key = key;
	}



	public Integer getValue() {
		return value;
	}



	public void setValue(Integer value) {
		this.value = value;
	}

    public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}



	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
