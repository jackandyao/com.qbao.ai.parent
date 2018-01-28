
package com.qbao.ai.dto.tag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TagDetailDto {

    @SerializedName("tagDetailId")
    @Expose
    private Long tagDetailId;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("dirId")
    @Expose
    private Long dirId;



    public TagDetailDto withTagDetailId(Long tagDetailId) {
        this.tagDetailId = tagDetailId;
        return this;
    }



    public TagDetailDto withValue(String value) {
        this.value = value;
        return this;
    }
    public TagDetailDto withDirId(Long dirId) {
        this.dirId = dirId;
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

    public Long getDirId() {
        return dirId;
    }

    public void setDirId(Long dirId) {
        this.dirId = dirId;
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
        if ((other instanceof TagDetailDto) == false) {
            return false;
        }
        TagDetailDto rhs = ((TagDetailDto) other);
        return new EqualsBuilder().append(tagDetailId, rhs.tagDetailId).append(value, rhs.value).append(dirId, rhs.dirId).isEquals();
    }

}
