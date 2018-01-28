
package com.qbao.ai.model.dto.tag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TagItemInfo {

    @SerializedName("tagDetailId")
    @Expose
    private Long tagDetailId;
    @SerializedName("value")
    @Expose
    private String value;



    public TagItemInfo withTagDetailId(Long tagDetailId) {
        this.tagDetailId = tagDetailId;
        return this;
    }



    public TagItemInfo withAirQuality(String value) {
        this.value = value;
        return this;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getTagDetailId() {
        return tagDetailId;
    }

    public void setTagDetailId(Long tagDetailId) {
        this.tagDetailId = tagDetailId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(tagDetailId).append(value).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TagItemInfo) == false) {
            return false;
        }
        TagItemInfo rhs = ((TagItemInfo) other);
        return new EqualsBuilder().append(tagDetailId, rhs.tagDetailId).append(value, rhs.value).isEquals();
    }

}
