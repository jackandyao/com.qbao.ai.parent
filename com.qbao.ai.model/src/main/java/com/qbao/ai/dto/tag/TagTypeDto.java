
package com.qbao.ai.dto.tag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class TagTypeDto {

    @SerializedName("id")
    @Expose
    private Long id;
    
    @SerializedName("tagName")
    @Expose
    private String tagName;
    
    @SerializedName("items")
    @Expose
    private List<TagDetailDto> items;






    public List<TagDetailDto> getItems() {
        return items;
    }

    public void setItems(List<TagDetailDto> items) {
        this.items = items;
    }

    public TagTypeDto withItems(List<TagDetailDto> items) {
        this.items = items;
        return this;
    }

    public TagTypeDto withId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TagTypeDto withTagName(String tagName) {
        this.tagName = tagName;
        return this;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(items).append(id).append(tagName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TagTypeDto) == false) {
            return false;
        }
        TagTypeDto rhs = ((TagTypeDto) other);
        return new EqualsBuilder().append(items, rhs.items).append(id,rhs.id).append(tagName,rhs.tagName).isEquals();
    }


}
