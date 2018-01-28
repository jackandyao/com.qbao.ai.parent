package com.qbao.ai.resposity.mybatis.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author louxueming
 * @createTime 17/3/7 上午9:26
 * $$LastChangedDate: 2017-03-23 10:21:11 +0800 (周四, 23 三月 2017) $$
 * $$LastChangedRevision: 469 $$
 * $$LastChangedBy: louxueming $$
 */
public class TagType implements Serializable {

    private static final long serialVersionUID = "$Id: TagType.java 469 2017-03-23 02:21:11Z louxueming $".hashCode();

    private Long id;
    private Long menuId;
    private String name;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof TagType))
            return false;

        TagType that = (TagType) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getMenuId(), that.getMenuId())
                .append(getName(), that.getName())
                .append(getCreateTime(), that.getCreateTime())
                .append(getUpdateTime(), that.getUpdateTime())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getMenuId())
                .append(getName())
                .append(getCreateTime())
                .append(getUpdateTime())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("menuId", menuId)
                .append("name", name)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .toString();
    }
}
