package com.qbao.ai.resposity.mybatis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * @author shuaizhihu
 * @createTime 2017/3/11 12:04
 * $$LastChangedDate: 2017-05-15 17:25:25 +0800 (Mon, 15 May 2017) $$
 * $$LastChangedRevision: 218 $$
 * $$LastChangedBy: liaijun $$
 * 站外搜索 商品实体
 */
public class ZwStuff implements Serializable{

    private static final long serialVersionUID="$Id: ZwStuff.java 218 2017-05-15 09:25:25Z liaijun $".hashCode();

    private long id;

    //商品源id
    private long realStuffId;

    //商品名称
    private String name;

    //原价格
    private double reservePrice;

    //最终折扣价格
    private BigDecimal finalPrice;

    //返券模板id
    private long rebateId;

    //主图地址
    private String imgUrl;

    //商品原地址
    private String url;

    //商品推广地址
    private String iosPromotionUrl;

    private String androidPromotionUrl;

    //推广佣金
    private long promotionRate;

    //商品类目
    private String catId;

    //商品状态
    private int status;

    //商品来源
    private String source;

    //店铺id

    private long shopId;

    //店铺名称
    private String shopName;

    //30天销量
    private int orderNum;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;
    private String catIdPath;
    private String catName;
    private String catNamePath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRealStuffId() {
        return realStuffId;
    }

    public void setRealStuffId(long realStuffId) {
        this.realStuffId = realStuffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(double reservePrice) {
        this.reservePrice = reservePrice;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        DecimalFormat df = new DecimalFormat("0.00");
        this.finalPrice = new BigDecimal(df.format(finalPrice));
    }

    public long getRebateId() {
        return rebateId;
    }

    public void setRebateId(long rebateId) {
        this.rebateId = rebateId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIosPromotionUrl() {
        return iosPromotionUrl;
    }

    public void setIosPromotionUrl(String iosPromotionUrl) {
        this.iosPromotionUrl = iosPromotionUrl;
    }

    public long getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(long promotionRate) {
        this.promotionRate = promotionRate;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCatIdPath() {
        return catIdPath;
    }

    public String getAndroidPromotionUrl() {
        return androidPromotionUrl;
    }

    public void setAndroidPromotionUrl(String androidPromotionUrl) {
        this.androidPromotionUrl = androidPromotionUrl;
    }

    public void setCatIdPath(String catIdPath) {
        this.catIdPath = catIdPath;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatNamePath() {
        return catNamePath;
    }

    public void setCatNamePath(String catNamePath) {
        this.catNamePath = catNamePath;
    }
}
