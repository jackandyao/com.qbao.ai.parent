
package com.qbao.ai.dto.indexpage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.qbao.ai.dto.tag.TagDetailDto;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class MenuDto {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("dirId")
    @Expose
    private Long dirId;
    
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("linkUrl")
    @Expose
    private String linkUrl;

    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;


    public MenuDto withId(Long id) {
        this.id = id;
        return this;
    }
    public MenuDto withDirId(Long dirId) {
        this.dirId = dirId;
        return this;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDirId() {
        return dirId;
    }

    public void setDirId(Long dirId) {
        this.dirId = dirId;
    }

    public MenuDto withName(String name) {
        this.name = name;
        return this;
    }
    public MenuDto withLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }
    public MenuDto withIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(dirId).append(name).append(linkUrl).append(iconUrl).toHashCode();
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
        return new EqualsBuilder().append(id,rhs.id).append(dirId,rhs.dirId).append(name, rhs.name).append(linkUrl, rhs.linkUrl).append(iconUrl,rhs.iconUrl).isEquals();
    }


}
