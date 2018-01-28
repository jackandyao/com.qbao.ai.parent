package com.qbao.ai.resposity.mybatis.dao;

import com.qbao.ai.resposity.mybatis.annotation.DataSource;
import com.qbao.ai.resposity.mybatis.model.QuestionDir;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shuaizhihu on 2016/11/30.
 */
@Repository
public interface QuestionDirDao {

    @DataSource("aiDataSource")
    @Select("select * from question_dir where lev = #{lev} limit #{start},#{rows} ")
    @ResultMap("QuestionDirMap")
    public List<QuestionDir> findListByLev(@Param("lev") int lev, @Param("start") int start, @Param("rows") int rows);

    @DataSource("aiDataSource")
    @Select("select count(1) from question_dir where lev = #{lev} ")
    public int findTotalByLev(@Param("lev") int lev);

    @DataSource("aiDataSource")
    @Select("select * from question_dir where dir_id = #{dirId} ")
    @ResultMap("QuestionDirMap")
    public List<QuestionDir> findChildren(long dirId);

    @DataSource("aiDataSource")
    @Select("select * from question_dir where pid = #{dirId} limit #{limit}")
    @ResultMap("QuestionDirMap")
    public List<QuestionDir> findChildrenByPid( @Param("dirId") long dirId, @Param("limit") int limit);

    @DataSource("aiDataSource")
    @Insert("insert into question_dir (dir_id,dir_name,lev,pid,update_time,create_time) values(#{dirId},#{dirName},#{lev},#{pid},#{updateTime},#{createTime} ) ")
    public void insert(QuestionDir dir);

    @DataSource("aiDataSource")
    @Select("select * from question_dir where dir_name = #{name} ")
    @ResultMap("QuestionDirMap")
    public QuestionDir findByName(String name);

    @DataSource("aiDataSource")
    @Select("select * from question_dir where dir_id = #{dirId} ")
    @ResultMap("QuestionDirMap")
    public QuestionDir findByDirId(Long dirId);




 }
