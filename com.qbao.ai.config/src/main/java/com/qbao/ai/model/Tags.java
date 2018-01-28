package com.qbao.ai.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-03 17:59:07 +0800 (周五, 03 三月 2017) $$
 * $$LastChangedRevision: 53 $$
 * $$LastChangedBy: wangping $$
 */
public class Tags implements Serializable {

    private static final long serialVersionUID = "$Id: UserTags.java 53 2017-03-03 09:59:07Z wangping $".hashCode();
    private Long id;
    private Long userId;
    private Integer tagTypeId;
    private String tagDetailIds;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTagDetailIds() {
        return tagDetailIds;
    }

    public void setTagDetailIds(String tagDetailIds) {
        this.tagDetailIds = tagDetailIds;
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

    public Integer getTagTypeId() {
        return tagTypeId;
    }

    public void setTagTypeId(Integer tagTypeId) {
        this.tagTypeId = tagTypeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("userId", userId)
                .append("tagTypeId", tagTypeId)
                .append("tagDetailIds", tagDetailIds)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .toString();
    }
}
