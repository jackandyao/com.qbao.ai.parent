package com.qbao.ai.resposity.mybatis.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-04-20 17:05:50 +0800 (Thu, 20 Apr 2017) $$
 * $$LastChangedRevision: 178 $$
 * $$LastChangedBy: liaijun $$
 */
public class AdBanner implements Serializable {
    private static final long serialVersionUID="$Id: AdBanner.java 178 2017-04-20 09:05:50Z liaijun $".hashCode();

    //
    private Long id;
    //
    private String imgUrl;
    //
    private String linkUrl;
    //
    private Integer status;
    //
    private Date onTime;
    //
    private Date offTime;


    // banner 名称
    private String name;
    // 备注
    private String memo;
    //
    private Date createTime;
    //
    private Date updateTime;
    // 广告主
    private String advertiser;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取：
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置：
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    /**
     * 获取：
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * 设置：
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：
     */
    public void setOnTime(Date onTime) {
        this.onTime = onTime;
    }

    /**
     * 获取：
     */
    public Date getOnTime() {
        return onTime;
    }

    /**
     * 设置：
     */
    public void setOffTime(Date offTime) {
        this.offTime = offTime;
    }

    /**
     * 获取：
     */
    public Date getOffTime() {
        return offTime;
    }


    /**
     * 设置：banner 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：banner 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 获取：备注
     */
    public String getMemo() {
        return memo;
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

    /**
     * 设置：广告主
     */
    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    /**
     * 获取：广告主
     */
    public String getAdvertiser() {
        return advertiser;
    }

    @Override
    public String toString() {
        return "AdBanner{" +
                "id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", status=" + status +
                ", onTime=" + onTime +
                ", offTime=" + offTime +
                ", name='" + name + '\'' +
                ", memo='" + memo + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", advertiser='" + advertiser + '\'' +
                '}';
    }
}
