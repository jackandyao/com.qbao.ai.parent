package com.qbao.ai.model.dto;

import org.dozer.Mapping;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author shuaizhihu
 * @createTime 2017/3/11 12:16
 * $$LastChangedDate: 2017-04-18 16:23:44 +0800 (Tue, 18 Apr 2017) $$
 * $$LastChangedRevision: 150 $$
 * $$LastChangedBy: liaijun $$
 */
public class ZwStuffDto implements Serializable{

    @Mapping("id")
    private long stuffId;
    private String name;
    @Mapping("finalPrice")
    private BigDecimal price;
    private String imgUrl;
    @Mapping("url")
    private String linkUrl;
    @Mapping("orderNum")
    private int saleCount;
    private String source;
    private String rebateValue;
    DecimalFormat df = new DecimalFormat("0.00");

    public long getStuffId() {
        return stuffId;
    }

    public void setStuffId(long stuffId) {
        this.stuffId = stuffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return new BigDecimal(df.format(price));
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

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRebateValue() {
        return rebateValue;
    }

    public void setRebateValue(String rebateValue) {
        this.rebateValue = rebateValue;
    }

}
