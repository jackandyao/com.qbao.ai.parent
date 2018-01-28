
package com.qbao.ai.model.dto.indexpage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.qbao.ai.model.dto.tag.TagItemInfo;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class MenuDto {

    @SerializedName("items")
    @Expose
    private List<MenuInfo> items;






    public List<MenuInfo> getItems() {
        return items;
    }

    public void setItems(List<MenuInfo> items) {
        this.items = items;
    }

    public MenuDto withItems(List<MenuInfo> items) {
        this.items = items;
        return this;
    }




    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(items).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MenuDto) == false) {
            return false;
        }
        MenuDto rhs = ((MenuDto) other);
        return new EqualsBuilder().append(items, rhs.items).isEquals();
    }


}
