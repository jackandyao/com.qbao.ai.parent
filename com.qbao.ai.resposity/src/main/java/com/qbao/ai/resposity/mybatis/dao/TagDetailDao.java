package com.qbao.ai.resposity.mybatis.dao;

import com.qbao.ai.resposity.mybatis.annotation.DataSource;
import com.qbao.ai.resposity.mybatis.model.TagDetail;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shuaizhihu on 2016/11/30.
 */
@Repository
public interface TagDetailDao {

	@ResultMap("TagDetailMap")
    @DataSource("aiDataSource")
    List<TagDetail>   findByTagTypeId(@Param("tagTypeId") long tagTypeId);

/*
    @DataSource("aiDataSource")
    @Select("select * from question_info where root_dir_id = #{rootDirId} limit #{start},#{rows} ")
    @ResultMap("QuestionInfoMap")
    List<QuestionInfo>  findListByRootDirId(@Param("rootDirId") long rootDirId, @Param("start") int start, @Param("rows") int rows);

    @DataSource("aiDataSource")
    @Select("select count(1) from question_info where root_dir_id = #{rootDirId} ")
    int findTotalByRootDirId(@Param("rootDirId") long rootDirId);

    @DataSource("aiDataSource")
    @Select("select * from question_info where id = #{id} ")
    @ResultMap("QuestionInfoMap")
    QuestionInfo findById(@Param("id") long id);

    @DataSource("aiDataSource")
    @Insert("insert into question_info (question,answer,links,dir_id,root_dir_id,status,create_time,update_time) values(#{question},#{answer},#{linkIds},#{dirId},#{rootDirId},#{status},#{updateTime},#{createTime})")
    void  insert(QuestionInfo info);*/

}
