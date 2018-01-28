
package com.qbao.ai.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class GoodsDto {

    @SerializedName("banner")
    @Expose
    private List<Banner> banner = null;
    @SerializedName("stuff")
    @Expose
    private List<Stuff> stuff = null;

    public List<Banner> getBanner() {
        return banner;
    }

    public void setBanner(List<Banner> banner) {
        this.banner = banner;
    }

    public GoodsDto withBanner(List<Banner> banner) {
        this.banner = banner;
        return this;
    }

    public List<Stuff> getStuff() {
        return stuff;
    }

    public void setStuff(List<Stuff> stuff) {
        this.stuff = stuff;
    }

    public GoodsDto withStuff(List<Stuff> stuff) {
        this.stuff = stuff;
        return this;
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
        if ((other instanceof GoodsDto) == false) {
            return false;
        }
        GoodsDto rhs = ((GoodsDto) other);
        return new EqualsBuilder().append(banner, rhs.banner).append(stuff, rhs.stuff).isEquals();
    }

}
