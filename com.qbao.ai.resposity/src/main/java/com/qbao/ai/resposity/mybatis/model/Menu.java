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
public class Menu implements Serializable {

    private static final long serialVersionUID = "$Id: TagType.java 469 2017-03-23 02:21:11Z louxueming $".hashCode();

    private Long id;
    private Long dirId;
    private String name;
    private Integer status;

    private String linkUrl;
    private String iconUrl;
    private Integer displayOrder;

    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDirId() {
        return dirId;
    }

    public void setDirId(Long dirId) {
        this.dirId = dirId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
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

        if (!(o instanceof Menu))
            return false;

        Menu that = (Menu) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getDirId(), that.getDirId())
                .append(getName(), that.getName())

                .append(getStatus(), that.getStatus())
                .append(getLinkUrl(), that.getLinkUrl())
                .append(getIconUrl(), that.getIconUrl())
                .append(getDisplayOrder(), that.getDisplayOrder())

                .append(getCreateTime(), that.getCreateTime())
                .append(getUpdateTime(), that.getUpdateTime())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getDirId())
                .append(getName())
                .append(getStatus())
                .append(getLinkUrl())
                .append(getIconUrl())
                .append(getDisplayOrder())
                .append(getCreateTime())
                .append(getUpdateTime())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("dirId", dirId)
                .append("name", name)
                .append("status", status)
                .append("linkUrl", linkUrl)
                .append("iconUrl", iconUrl)
                .append("displayOrder", displayOrder)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .toString();
    }
}
