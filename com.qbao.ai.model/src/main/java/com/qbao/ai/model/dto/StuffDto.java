
package com.qbao.ai.model.dto;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StuffDto {

    @SerializedName("banner")
    @Expose
    private List<Banner> banner = null;
    @SerializedName("stuff")
    @Expose
    private List<StuffRecommendDto> stuff = null;

    public List<Banner> getBanner() {
        return banner;
    }

    public void setBanner(List<Banner> banner) {
        this.banner = banner;
    }

    public StuffDto withBanner(List<Banner> banner) {
        this.banner = banner;
        return this;
    }

    public List<StuffRecommendDto> getStuff() {
        return stuff;
    }

    public void setStuff(List<StuffRecommendDto> stuff) {
        this.stuff = stuff;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(banner).append(stuff).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StuffDto) == false) {
            return false;
        }
        StuffDto rhs = ((StuffDto) other);
        return new EqualsBuilder().append(banner, rhs.banner).append(stuff, rhs.stuff).isEquals();
    }

}
