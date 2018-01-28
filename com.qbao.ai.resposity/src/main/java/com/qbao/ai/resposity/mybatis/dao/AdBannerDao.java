package com.qbao.ai.resposity.mybatis.dao;

import com.qbao.ai.resposity.mybatis.annotation.DataSource;
import com.qbao.ai.resposity.mybatis.model.AdBanner;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-05-23 11:01:30 +0800 (Tue, 23 May 2017) $$
 * $$LastChangedRevision: 233 $$
 * $$LastChangedBy: wangping $$
 */
@Repository
public interface AdBannerDao {

    @Select("select * from ad_banner where status=1 and on_time<=#{nowTime} and off_time>=#{nowTime} limit #{limit}")
    @ResultMap("AdBannerMap")
    @DataSource("aiDataSource")
    public List<AdBanner> findBannerByLocationId(@Param("limit") int limit, @Param("nowTime") Date nowTime);



}
