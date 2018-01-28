
package com.qbao.ai.model.dto;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.math.BigDecimal;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class StuffRecommendDto {
    @SerializedName("stuffId")
    private long stuffId;//统一商品id
    @SerializedName("name")
    private String name;//商品名称
    @SerializedName("price")
    private BigDecimal price;
    @SerializedName("imgUrl")
    private String imgUrl;//商品图片链接
    @SerializedName("linkUrl")
    private String linkUrl;//商品链接
    @SerializedName("saleCount")
    private Integer saleCount;//销售数量
    @SerializedName("source")
    private String source;//商品来源:taobao,tmall,jd
    @SerializedName("rebateValue")
    private String rebateValue;//返利多少

    public long getStuffId() {
        return stuffId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public String getSource() {
        return source;
    }

    public String getRebateValue() {
        return rebateValue;
    }

    public static class Builder {
        private long stuffId;//统一商品id
        private String name;//商品名称
        private BigDecimal price;
        private String imgUrl;//商品图片链接
        private String linkUrl;//商品链接
        private Integer saleCount;//销售数量
        private String source;//商品来源:taobao,tmall,jd
        private String rebateValue;//返利多少

        public Builder withStuffId(long stuffId) {
            this.stuffId = stuffId;
            return this;
        }

        public Builder withName(String name) {
            if(name!=null){
                this.name = name;
            }else{
                this.name = "";
            }

            return this;
        }
        public Builder withPrice(BigDecimal price) {
            if(price!=null){
                this.price = price;
            }else{
                this.price = new BigDecimal(0);
            }

            return this;
        }

        public Builder withImgUrl(String imgUrl) {
            if(imgUrl!=null){
                this.imgUrl = imgUrl;
            }else{
                this.imgUrl = "";
            }

            return this;
        }
        public Builder withLinkUrl(String linkUrl) {
            if(linkUrl!=null){
                this.linkUrl = linkUrl;
            }else{
                this.linkUrl = "";
            }

            return this;
        }

        public Builder withSaleCount(Integer saleCount) {
            if(saleCount!=null){
                this.saleCount = saleCount;
            }else{
                this.saleCount = 0;
            }

            return this;
        }
        public Builder withSource(String source) {
            if(source!=null){
                this.source = source;
            }else{
                source="";
            }

            return this;
        }
        public Builder withRebateValue(String rebateValue) {
            this.rebateValue = rebateValue;
            return this;
        }


        public StuffRecommendDto build() {
            StuffRecommendDto stuffRecommendDto = new StuffRecommendDto();
            stuffRecommendDto.stuffId = this.stuffId;
            stuffRecommendDto.name = this.name;
            stuffRecommendDto.price = this.price;
            stuffRecommendDto.imgUrl = this.imgUrl;
            stuffRecommendDto.linkUrl = this.linkUrl;
            stuffRecommendDto.saleCount = this.saleCount;
            stuffRecommendDto.source = this.source;
            stuffRecommendDto.rebateValue = this.rebateValue;
            return stuffRecommendDto;
        }

    }

}
