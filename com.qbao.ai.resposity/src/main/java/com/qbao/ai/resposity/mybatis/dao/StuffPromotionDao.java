package com.qbao.ai.resposity.mybatis.dao;

import com.qbao.ai.resposity.mybatis.annotation.DataSource;
import com.qbao.ai.resposity.mybatis.model.StuffPromotion;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-05-23 11:01:30 +0800 (Tue, 23 May 2017) $$
 * $$LastChangedRevision: 233 $$
 * $$LastChangedBy: wangping $$
 */
@Repository
public interface StuffPromotionDao {
    @Select("select * from qbao_stuff.stuff_promotion where status=1 and shop_id=#{shopId} and source=#{source} order by create_time desc limit #{stuffSize}")
    @ResultMap("StuffPromotionMap")
    @DataSource("stuffDataSource")
    public List<StuffPromotion> findStuffPromotionByShopId(@Param("shopId") long shopId, @Param("stuffSize") int stuffSize, @Param("source") String source);


    @Select("select * from qbao_stuff.stuff_promotion where status=1 and cat_id=#{catId} limit #{page},#{size}")
    @ResultMap("StuffPromotionMap")
    @DataSource("stuffDataSource")
    public List<StuffPromotion> findStuffPromotionByCatId(@Param("catId") Long catId, @Param("page") int page, @Param("size") int size);

    @Select("select * from qbao_stuff.stuff_promotion where status=1 and id = #{id}")
    @ResultMap("StuffPromotionMap")
    @DataSource("stuffDataSource")
    public StuffPromotion findStuffPromotionById(@Param("id") long id);

    // 此接口只返回id 如果返回整张表字段请调用findStuffPromotionByIdsList
    @DataSource("stuffDataSource")
    public List<StuffPromotion> findStuffPromotionByIds(@Param("ids") List<Long> ids);

    // @Select("select * from stuff_promotion where status=1 and id in limit #{page},#{size}")
    // @ResultMap("StuffPromotionMap")
    @DataSource("stuffDataSource")
    public List<StuffPromotion> findStuffPromotionByIdsAndPage(@Param("ids") List<Long> ids, @Param("page") Integer page, @Param("size") Integer size);

    @DataSource("stuffDataSource")
    public List<StuffPromotion> findStuffPromotionByIdsList(@Param("ids") List<Long> ids);

    @Select("select  p.*,r.is_absolute,r.`value` as rebate_value,p.id as stuff_id from qbao_stuff.stuff_promotion p,qbao_stuff.stuff_rebate r  where p.rebate_id=r.id and p.status=1 and p.id = #{stuffId}")
    @ResultMap("StuffPromotionMap")
    @DataSource("stuffDataSource")
    public StuffPromotion findStuffPromotionInfo(@Param("stuffId") long stuffId);

}
