package com.qbao.ai.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-04-16 23:33:18 +0800 (Sun, 16 Apr 2017) $$
 * $$LastChangedRevision: 124 $$
 * $$LastChangedBy: liaijun $$
 */
public class StuffPromotionDto implements Serializable {
    private static final long serialVersionUID="$Id: StuffPromotionDto.java 124 2017-04-16 15:33:18Z liaijun $".hashCode();
    // 统一商品id
    private long id;

    // 推广销量
    private Integer orderNum;

    //商品名称
    @SerializedName("name")
    private String name;
    //商品实际价格
    @SerializedName("final_price")
    private BigDecimal finalPrice;

    @SerializedName("img_url")
    private String imgUrl;

    @SerializedName("rebate_value")
    private String rebateValue;
    @SerializedName("source")
    private String source;

    @SerializedName("url")
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRebateValue() {
        return rebateValue;
    }

    public void setRebateValue(String rebateValue) {
        this.rebateValue = rebateValue;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "StuffPromotionDto{" +
                "id=" + id +
                ", orderNum=" + orderNum +
                ", name='" + name + '\'' +
                ", finalPrice=" + finalPrice +
                ", imgUrl='" + imgUrl + '\'' +
                ", rebateValue='" + rebateValue + '\'' +
                ", source='" + source + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
