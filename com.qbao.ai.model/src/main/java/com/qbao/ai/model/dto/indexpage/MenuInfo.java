
package com.qbao.ai.model.dto.indexpage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class MenuInfo {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;
    @SerializedName("linkUrl")
    @Expose
    private String linkUrl;
    @SerializedName("name")
    @Expose
    private String name;




    public MenuInfo withId(Long id) {
        this.id = id;
        return this;
    }

    public MenuInfo withIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }
    public MenuInfo withLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }
    public MenuInfo withName(String name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(iconUrl).append(linkUrl).append(name).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MenuInfo) == false) {
            return false;
        }
        MenuInfo rhs = ((MenuInfo) other);
        return new EqualsBuilder().append(id, rhs.id).append(iconUrl, rhs.iconUrl).append(linkUrl, rhs.linkUrl).append(name, rhs.name).isEquals();
    }

}
