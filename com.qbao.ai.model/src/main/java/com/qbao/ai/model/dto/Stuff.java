
package com.qbao.ai.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.math.BigDecimal;

public class Stuff {

    @SerializedName("stuffId")
    @Expose
    private Long stuffId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private BigDecimal price;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;
    @SerializedName("linkUrl")
    @Expose
    private String linkUrl;
    @SerializedName("saleCount")
    @Expose
    private int saleCount;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("rebateValue")
    @Expose
    private String rebateValue;

    public Long getStuffId() {
        return stuffId;
    }

    public void setStuffId(Long stuffId) {
        this.stuffId = stuffId;
    }

    public Stuff withStuffId(Long stuffId) {
        this.stuffId = stuffId;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stuff withName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Stuff withImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Stuff withLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public Stuff withSaleCount(int saleCount) {
        this.saleCount = saleCount;
        return this;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Stuff withSource(String source) {
        this.source = source;
        return this;
    }

    public String getRebateValue() {
        return rebateValue;
    }

    public void setRebateValue(String rebateValue) {
        this.rebateValue = rebateValue;
    }

    public Stuff withRebateValue(String rebateValue) {
        this.rebateValue = rebateValue;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(stuffId).append(name).append(price).append(imgUrl).append(linkUrl).append(saleCount).append(source).append(rebateValue).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Stuff) == false) {
            return false;
        }
        Stuff rhs = ((Stuff) other);
        return new EqualsBuilder().append(stuffId, rhs.stuffId).append(name, rhs.name).append(price, rhs.price).append(imgUrl, rhs.imgUrl).append(linkUrl, rhs.linkUrl).append(saleCount, rhs.saleCount).append(source, rhs.source).append(rebateValue, rhs.rebateValue).isEquals();
    }

}
