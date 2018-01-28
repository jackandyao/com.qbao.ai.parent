package com.qbao.ai.resposity.mybatis.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangping
 * @createTime 17/3/7 上午9:26
 * $$LastChangedDate: 2017-04-16 18:35:30 +0800 (Sun, 16 Apr 2017) $$
 * $$LastChangedRevision: 119 $$
 * $$LastChangedBy: liaijun $$
 */
public class StuffRebate implements Serializable {

    private static final long serialVersionUID = "$Id: StuffRebate.java 119 2017-04-16 10:35:30Z liaijun $".hashCode();

    private Long id;
    private String name;
    private BigDecimal value;
    private Integer isAbsolute;
    private String remark;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getIsAbsolute() {
        return isAbsolute;
    }

    public void setIsAbsolute(Integer isAbsolute) {
        this.isAbsolute = isAbsolute;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

        if (!(o instanceof StuffRebate))
            return false;

        StuffRebate that = (StuffRebate) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getName(), that.getName())
                .append(getValue(), that.getValue())
                .append(getIsAbsolute(), that.getIsAbsolute())
                .append(getRemark(), that.getRemark())
                .append(getCreateTime(), that.getCreateTime())
                .append(getUpdateTime(), that.getUpdateTime())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getName())
                .append(getValue())
                .append(getIsAbsolute())
                .append(getRemark())
                .append(getCreateTime())
                .append(getUpdateTime())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("value", value)
                .append("isAbsolute", isAbsolute)
                .append("remark", remark)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .toString();
    }
}
