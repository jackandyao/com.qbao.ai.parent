package com.qbao.ai.resposity.mybatis.dao;

import com.qbao.ai.resposity.mybatis.annotation.DataSource;
import com.qbao.ai.resposity.mybatis.model.UserDislikeStuff;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;

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
public interface UserDislikeStuffDao {
    @Select("select * from qbao_stuff.user_dislike_stuff where user_id=#{userId}")
    @ResultMap("UserDislikeStuffMap")
    @DataSource("stuffDataSource")
    public UserDislikeStuff userDislikeStuff(@Param("userId") Long userId);

    @Update("update qbao_stuff.user_dislike_stuff set stuff_ids=#{stuffIds},update_time=#{updateTime} where user_id=#{userId}")
    @ResultMap("UserDislikeStuffMap")
    @DataSource("stuffDataSource")
    public void updateUserDislikeStuff(@Param("userId") Long userId, @Param("stuffIds") String stuffIds, @Param("updateTime") Date updateTime);

    @Insert("insert qbao_stuff.user_dislike_stuff ( `user_id`,`stuff_ids`,  `create_time`,  `update_time`) values ( #{userId} , #{stuffIds} , #{createTime} , #{createTime})")
    @ResultMap("UserDislikeStuffMap")
    @DataSource("stuffDataSource")
    public void insertUserDislikeStuff(@Param("userId") Long userId, @Param("stuffIds") Long stuffIds, @Param("createTime") Date createTime);




}
