package com.qbao.ai.resposity.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class JTagType implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public JTagType() {
        super();
        // TODO Auto-generated constructor stub
    }
    public JTagType(int id, int menuId, Date createTime, Date updateTime, String name) {
        super();
        this.id = id;
        this.menuId = menuId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.name = name;
    }
    private int id;
    private int menuId;
    private Date createTime;
    private Date updateTime;
    private String name;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getMenuId() {
        return menuId;
    }
    public void setMenuId(int menuId) {
        this.menuId = menuId;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
