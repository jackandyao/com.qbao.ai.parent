package com.qbao.ai.resposity.mybatis.dao;

import org.apache.ibatis.annotations.Insert;

import com.qbao.ai.resposity.mybatis.annotation.DataSource;
import com.qbao.ai.resposity.mybatis.model.QuestionMessage;
import org.springframework.stereotype.Repository;

/**
 * Created by shuaizhihu on 2016/12/1.
 */
@Repository
public interface QuestionMessageDao {

    @DataSource("aiDataSource")
    @Insert("insert into question_message (user_id,question,email,phone,status,answer,editor,update_time,create_time) values (" +
            "#{userId},#{question},#{email},#{phone},#{status},#{answer},#{editor},#{updateTime},#{createTime})")
    public void add(QuestionMessage message);
}
