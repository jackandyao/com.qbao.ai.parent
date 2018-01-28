package com.qbao.ai.resposity.mybatis.dao;

import com.qbao.ai.resposity.mybatis.annotation.DataSource;
import com.qbao.ai.resposity.mybatis.model.StuffRebate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author wangping
 * @createTime 17/3/7 上午9:32
 * $$LastChangedDate: 2017-05-23 11:01:30 +0800 (Tue, 23 May 2017) $$
 * $$LastChangedRevision: 233 $$
 * $$LastChangedBy: wangping $$
 */
@Repository
public interface StuffRebateDao {

    @DataSource("stuffDataSource")
    @Select("select * from qbao_stuff.stuff_rebate where id = #{id} ")
    @ResultMap("StuffRebateMap")
    public StuffRebate findStuffReBate(@Param("id") long id);

    @DataSource("stuffDataSource")
    @Select("select distinct sr.*\n" +
            "from qbao_stuff.stuff_promotion sp inner join qbao_stuff.stuff_rebate sr\n" +
            "on sp.rebate_id = sr.id\n" +
            "where sr.id =  #{stuffId}")
    @ResultMap("StuffRebateMap")
    public StuffRebate findStuffReBateByStuffId(@Param("stuffId") Long stuffId);

}
