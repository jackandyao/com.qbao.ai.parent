package com.qbao.ai.model.dto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author wangping
 * @createTime 2017/4/12 上午10:22
 * $$LastChangedDate: 2017-04-12 10:25:02 +0800 (Wed, 12 Apr 2017) $$
 * $$LastChangedRevision: 88 $$
 * $$LastChangedBy: wangping $$
 */



public class AnswerDto {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("link")
    @Expose
    private String link;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AnswerDto withValue(String value) {
        this.value = value;
        return this;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public AnswerDto withLink(String link) {
        this.link = link;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(value).append(link).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AnswerDto) == false) {
            return false;
        }
        AnswerDto rhs = ((AnswerDto) other);
        return new EqualsBuilder().append(value, rhs.value).append(link, rhs.link).isEquals();
    }

}
