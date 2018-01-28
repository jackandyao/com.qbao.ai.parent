package com.qbao.ai.resposity.mybatis.dao;

import com.qbao.ai.resposity.mybatis.annotation.DataSource;
import com.qbao.ai.resposity.mybatis.model.StuffRecommend;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 商品推荐
 * 
 * @author xueming
 * @createTime 17/3/6 下午3:17
 * $$LastChangedDate: 2017-05-23 11:01:30 +0800 (Tue, 23 May 2017) $$
 * $$LastChangedRevision: 233 $$
 * $$LastChangedBy: wangping $$
 */
@Repository
public interface StuffRecommendDao {
    @Select("select * from qbao_stuff.rec_search_stuff where user_id=#{userId}")
    @ResultMap("StuffRecommendMap")
    @DataSource("stuffDataSource")
    public StuffRecommend stuffRecommend(@Param("userId") Long userId);

    @Select("update qbao_stuff.rec_search_stuff set stuff_ids=#{stuffIds} where user_id=#{userId}")
    @ResultMap("StuffRecommendMap")
    @DataSource("stuffDataSource")
    public void updateStuffRecommend(@Param("userId") Long userId, @Param("stuffIds") String stuffIds);

    /**
     * 首页商品推荐
     * @param userId
     * @return
     */
    @Select("select * qbao_stuff.from rec_user_stuff where user_id=#{userId}")
    @ResultMap("StuffRecommendMap")
    @DataSource("stuffDataSource")
    public StuffRecommend stuffRecommendHomePage(@Param("userId") Long userId);


}
