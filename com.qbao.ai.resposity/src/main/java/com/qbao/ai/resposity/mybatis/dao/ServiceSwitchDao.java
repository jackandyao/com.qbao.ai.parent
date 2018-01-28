package com.qbao.ai.resposity.mybatis.dao;

import com.qbao.ai.resposity.mybatis.annotation.DataSource;
import com.qbao.ai.resposity.mybatis.model.ServiceSwitch;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author sjzhangjun
 * @createTime 17/3/30 上午9:32
 * $$LastChangedDate: 2017-03-11 19:31:45 +0800 (周六, 2017-03-11) $$
 * $$LastChangedRevision: 148 $$
 * $$LastChangedBy: louxueming $$
 */
@Repository
public interface ServiceSwitchDao {

    @DataSource("aiDataSource")
    @Select("select * from service_switch where `key` = #{key} ")
    @ResultMap("ServiceSwitchMap")
    public ServiceSwitch findServiceSwitchByKey(@Param("key") String key);

}
