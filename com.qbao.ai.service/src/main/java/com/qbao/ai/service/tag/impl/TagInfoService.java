package com.qbao.ai.service.tag.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.ai.dto.tag.TagDetailDto;
import com.qbao.ai.dto.tag.TagItemDto;
import com.qbao.ai.dto.tag.TagTypeDto;
import com.qbao.ai.resposity.mybatis.dao.TagDetailDao;
import com.qbao.ai.resposity.mybatis.dao.TagTypeDao;
import com.qbao.ai.resposity.mybatis.model.TagDetail;
import com.qbao.ai.resposity.mybatis.model.TagType;
import com.qbao.ai.resposity.redis.cache.annotation.CacheType;
import com.qbao.ai.resposity.redis.cache.annotation.RedisCache;
import com.qbao.ai.service.tag.ITagInfoService;

/**
 * Created by shuaizhihu on 2016/12/1.
 */
@Service
public class TagInfoService implements ITagInfoService {

    @Autowired
    TagTypeDao tagTypeDao;
    @Autowired
    TagDetailDao tagDetailDao;

    @Override
    @RedisCache(expire = 60 * 30, clazz = TagTypeDto.class, cacheType = CacheType.LIST)
    public List<TagTypeDto> findList() {
        List<TagType> list = tagTypeDao.findList();
        List<TagTypeDto> tagTypeDtos = new LinkedList<TagTypeDto>();
        for(TagType tagType:list){
            List<TagDetailDto> tagDetailDtos = new LinkedList<TagDetailDto>();
            List<TagDetail>  tagDetailList = tagDetailDao.findByTagTypeId(tagType.getId());
            if (CollectionUtils.isNotEmpty(tagDetailList)) {
                for (TagDetail td : tagDetailList) {
                    tagDetailDtos.add(new TagDetailDto().withTagDetailId(td.getId()).withValue(td.getValue()).withDirId(td.getDirId()));
                }
            }
            tagTypeDtos.add(new TagTypeDto().withItems(tagDetailDtos).withId(tagType.getId()).withTagName(tagType.getName()));
        }


        return tagTypeDtos;
    }
    @Override
    @RedisCache(expire = 60 * 30, clazz = TagItemDto.class, cacheType = CacheType.OBJECT)
    public TagItemDto findByMenuId(Long menuId) {
            TagType tagType = tagTypeDao.findByMenuId(menuId);

            TagItemDto tagItemDto = new TagItemDto();
            if(tagType==null){
               return tagItemDto;
            }
            List<TagDetailDto> tagDetailDtos = new LinkedList<TagDetailDto>();
            List<TagDetail>  tagDetailList = tagDetailDao.findByTagTypeId(tagType.getId());
            if (CollectionUtils.isNotEmpty(tagDetailList)) {
                for (TagDetail td : tagDetailList) {
                    tagDetailDtos.add(new TagDetailDto().withTagDetailId(td.getId()).withValue(td.getValue()).withDirId(td.getDirId()));
                }
            }
            tagItemDto.withItems(tagDetailDtos).withMenuId(tagType.getMenuId()).withMenuName(tagType.getName());


        return tagItemDto;
    }

   /* @Override
    public Page<QuestionInfo> findPageByRootDirId(long rootDirId, int page, int size) {
        int start = (page-1)*size;
        int rows = size;
        List<QuestionInfo> list = questionInfoDao.findListByRootDirId(rootDirId,start,rows);
        int total = questionInfoDao.findTotalByRootDirId(rootDirId);
        return new Page<QuestionInfo>(total,page,size,list);
    }

    @Override
    public QuestionInfo findById(long id) {
       return  questionInfoDao.findById(id);
    }

    @Override
    public Page<Question> search(String kw, int page, int size) {
       return  questionSearchBiz.searchQuestions(kw,page,size);
    }*/
}
