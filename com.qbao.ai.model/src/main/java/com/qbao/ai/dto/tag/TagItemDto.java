
package com.qbao.ai.dto.tag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class TagItemDto {


    @SerializedName("menuId")
    @Expose
    private Long menuId;

    @SerializedName("menuName")
    @Expose
    private String menuName;
    
    @SerializedName("items")
    @Expose
    private List<TagDetailDto> items;






    public List<TagDetailDto> getItems() {
        return items;
    }

    public void setItems(List<TagDetailDto> items) {
        this.items = items;
    }

    public TagItemDto withItems(List<TagDetailDto> items) {
        this.items = items;
        return this;
    }



    public TagItemDto withMenuId(Long menuId) {
        this.menuId = menuId;
        return this;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public TagItemDto withMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(items).append(menuId).append(menuName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TagItemDto) == false) {
            return false;
        }
        TagItemDto rhs = ((TagItemDto) other);
        return new EqualsBuilder().append(items, rhs.items).append(menuId,rhs.menuId).append(menuName,rhs.menuName).isEquals();
    }


}
