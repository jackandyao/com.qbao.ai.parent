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
public class TagDetail implements Serializable {

    private static final long serialVersionUID = "$Id: TagDetail.java 469 2017-03-23 02:21:11Z louxueming $".hashCode();

    private Long id;
    private String value;
    private Long dirId;
    private Long tagTypeId;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getDirId() {
        return dirId;
    }

    public void setDirId(Long dirId) {
        this.dirId = dirId;
    }

    public Long getTagTypeId() {
        return tagTypeId;
    }

    public void setTagTypeId(Long tagTypeId) {
        this.tagTypeId = tagTypeId;
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

        if (!(o instanceof TagDetail))
            return false;

        TagDetail that = (TagDetail) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getValue(), that.getValue())
                .append(getDirId(), that.getDirId())
                .append(getTagTypeId(), that.getTagTypeId())
                .append(getCreateTime(), that.getCreateTime())
                .append(getUpdateTime(), that.getUpdateTime())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getValue())
                .append(getDirId())
                .append(getTagTypeId())
                .append(getCreateTime())
                .append(getUpdateTime())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("value", value)
                .append("dirId", dirId)
                .append("tagTypeId", tagTypeId)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .toString();
    }
}
