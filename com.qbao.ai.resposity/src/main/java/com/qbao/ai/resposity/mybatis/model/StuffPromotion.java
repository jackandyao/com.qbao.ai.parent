package com.qbao.ai.resposity.mybatis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-04-16 18:35:30 +0800 (Sun, 16 Apr 2017) $$
 * $$LastChangedRevision: 119 $$
 * $$LastChangedBy: liaijun $$
 */
public class StuffPromotion implements Serializable {
    private static final long serialVersionUID = "$Id: StuffPromotion.java 119 2017-04-16 10:35:30Z liaijun $".hashCode();

    // 统一商品id
    private long id;
    // 实际商品id
    private long realStuffId;
    // 商品名称
    private String name;
    // 商品最终价格
    private BigDecimal finalPrice;
    //商品原价
    private BigDecimal reservePrice;
    
    // 返利类型 rebate 表 id
    private Integer rebateId;
    // 商品链接
    private String url;
    // 商品图片链接
    private String imgUrl;
    // stuff_category 表 的 商品类目id
    private Long catId;
    // 商品状态 0:审核,1:上架,2下架
    private Integer status;
    // 商品来源:taobao,tmall,jd
    private String source;
    // 店铺id
    private Long shopId;

    private String shopName;
    // 推广销量
    private Integer orderNum;
    // 点击量
    private Integer clickNum;
    //
    private Date createTime;
    //
    private Date updateTime;
     private Integer isAbsolute;
    private String iosPromotionUrl;
    private String androidPromotionUrl;
    private String promotionUrl;
    private String rebateValue;
    private Long stuffId;
    // 推广佣金比
    private Integer promotionRate;


    public String getRebateValue() {
        return rebateValue;
    }

    public void setRebateValue(String rebateValue) {
        this.rebateValue = rebateValue;
    }

    public Long getStuffId() {
        return stuffId;
    }

    public void setStuffId(Long stuffId) {
        this.stuffId = stuffId;
    }

    public String getPromotionUrl() {
        return promotionUrl;
    }

    public void setPromotionUrl(String promotionUrl) {
        this.promotionUrl = promotionUrl;
    }

    /**

     * 设置：统一商品id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：统一商品id
     */
    public long getId() {
        return id;
    }

    /**
     * 设置：实际商品id
     */
    public void setRealStuffId(Long realStuffId) {
        this.realStuffId = realStuffId;
    }

    /**
     * 获取：实际商品id
     */
    public long getRealStuffId() {
        return realStuffId;
    }

    /**
     * 设置：商品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：商品名称
     */
    public String getName() {
        return name;
    }

    public Integer getIsAbsolute() {
        return isAbsolute;
    }

    public void setIsAbsolute(Integer isAbsolute) {
        this.isAbsolute = isAbsolute;
    }

    /**
     * 设置：商品价格
     */
    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    /**
     * 获取：商品价格
     */
    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    /**
     * 设置：返利类型 rebate 表 id
     */
    public void setRebateId(Integer rebateId) {
        this.rebateId = rebateId;
    }

    /**
     * 获取：返利类型 rebate 表 id
     */
    public Integer getRebateId() {
        return rebateId;
    }

    /**
     * 设置：商品链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：商品链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：商品图片链接
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取：商品图片链接
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置：stuff_category 表 的 商品类目id
     */
    public void setCatId(Long catId) {
        this.catId = catId;
    }

    /**
     * 获取：stuff_category 表 的 商品类目id
     */
    public Long getCatId() {
        return catId;
    }

    /**
     * 设置：商品状态 0:审核,1:上架,2下架
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：商品状态 0:审核,1:上架,2下架
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：商品来源:taobao,tmall,jd
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取：商品来源:taobao,tmall,jd
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置：店铺id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取：店铺id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置：推广销量
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取：推广销量
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置：点击量
     */
    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    /**
     * 获取：点击量
     */
    public Integer getClickNum() {
        return clickNum;
    }

    /**
     * 设置：
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setRealStuffId(long realStuffId) {
        this.realStuffId = realStuffId;
    }

    public BigDecimal getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(BigDecimal reservePrice) {
        this.reservePrice = reservePrice;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIosPromotionUrl() {
        return iosPromotionUrl;
    }

    public void setIosPromotionUrl(String iosPromotionUrl) {
        this.iosPromotionUrl = iosPromotionUrl;
    }

    public String getAndroidPromotionUrl() {
        return androidPromotionUrl;
    }

    public void setAndroidPromotionUrl(String androidPromotionUrl) {
        this.androidPromotionUrl = androidPromotionUrl;
    }

    @Override
    public String toString() {
        return "StuffPromotion{" + "id=" + id + ", realStuffId=" + realStuffId + ", name='" + name + '\'' + ", finalPrice=" + finalPrice + ", reservePrice=" + reservePrice + ", rebateId=" + rebateId + ", url='" + url + '\'' + ", imgUrl='" + imgUrl + '\'' + ", catId=" + catId + ", status=" + status + ", source='" + source + '\'' + ", shopId=" + shopId + ", shopName='" + shopName + '\'' + ", orderNum=" + orderNum + ", clickNum=" + clickNum + ", createTime=" + createTime + ", updateTime=" + updateTime + ", isAbsolute=" + isAbsolute + ", iosPromotionUrl='" + iosPromotionUrl + '\'' + ", androidPromotionUrl='" + androidPromotionUrl + '\'' + ", rebateValue='" + rebateValue + '\'' + ", stuffId=" + stuffId + ", promotionRate=" + promotionRate + '}';
    }

    public Integer getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(Integer promotionRate) {
        this.promotionRate = promotionRate;
    }
}
