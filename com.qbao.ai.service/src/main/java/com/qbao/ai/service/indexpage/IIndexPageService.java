package com.qbao.ai.service.indexpage;

import com.qbao.ai.dto.indexpage.MenuDto;
import com.qbao.ai.dto.tag.TagItemDto;
import com.qbao.ai.dto.tag.TagTypeDto;
import com.qbao.ai.model.dto.QuestionDto;

import java.util.List;

/**
 * Created by louxueming on 2016/12/1.
 */
public interface IIndexPageService {

    public List<MenuDto> findList();

    public List<QuestionDto> menuDetail(Long dirId,Integer limit);

    public List<QuestionDto> getQuestionByDirId(Long dirId);



}
