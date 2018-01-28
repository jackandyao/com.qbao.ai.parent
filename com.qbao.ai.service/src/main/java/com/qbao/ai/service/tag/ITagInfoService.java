package com.qbao.ai.service.tag;

import com.qbao.ai.dto.tag.TagItemDto;
import com.qbao.ai.dto.tag.TagTypeDto;

import java.util.List;

/**
 * Created by louxueming on 2016/12/1.
 */
public interface ITagInfoService {

    public List<TagTypeDto> findList();

    public TagItemDto findByMenuId(Long menuId);

  /*  public Page<QuestionInfo> fin(long rootDirId, int page, int size);

    public QuestionInfo findById(long id);

    public Page<Question> search(String kw, int page, int size);*/

}
