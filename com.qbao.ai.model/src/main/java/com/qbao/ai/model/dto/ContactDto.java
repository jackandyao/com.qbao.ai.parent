package com.qbao.ai.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangping
 * @createTime 2017/4/11 下午10:06
 * $$LastChangedDate: 2017-04-11 23:10:27 +0800 (Tue, 11 Apr 2017) $$
 * $$LastChangedRevision: 85 $$
 * $$LastChangedBy: wangping $$
*/


public class ContactDto implements Serializable{

    @SerializedName("qq")
    @Expose
    private List<Long> qq = null;

    public List<Long> getQq() {
        return qq;
    }

    public void setQq(List<Long> qq) {
        this.qq = qq;
    }

    public ContactDto withQq(List<Long> qq) {
        this.qq = qq;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(qq).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ContactDto) == false) {
            return false;
        }
        ContactDto rhs = ((ContactDto) other);
        return new EqualsBuilder().append(qq, rhs.qq).isEquals();
    }

}