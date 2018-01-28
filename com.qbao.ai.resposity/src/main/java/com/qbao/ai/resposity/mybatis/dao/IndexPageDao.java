package com.qbao.ai.resposity.mybatis.dao;

import com.qbao.ai.resposity.mybatis.annotation.DataSource;
import com.qbao.ai.resposity.mybatis.model.Menu;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shuaizhihu on 2016/11/30.
 */
@Repository
public interface IndexPageDao {

	@ResultMap("MenuMap")
    @DataSource("aiDataSource")
    List<Menu>  findList();


}
