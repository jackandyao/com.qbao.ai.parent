package com.qbao.ai.service;

import java.util.List;

import com.qbao.ai.dto.tag.TagTypeDto;
import com.qbao.ai.model.dto.QuestionDto;
import com.qbao.ai.service.indexpage.IIndexPageService;
import com.qbao.ai.service.tag.ITagInfoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qbao.ai.resposity.common.Page;
import com.qbao.ai.resposity.mybatis.model.QuestionDir;

public class TestService {
   /* public static void main(String[] args) {
        //String[] servicePath ={"spring_service.xml","spring_repository.xml"};
        String filePath ="spring-context.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(filePath);
        IQuestionDirService questionService = context.getBean(IQuestionDirService.class);
        Page<QuestionDir> page = questionService.findByLev(1, 1, 10);
        List<QuestionDir>list =page.getItems();
        System.out.println(list.size());
    }*/
   public static void main(String[] args) {
       //String[] servicePath ={"spring_service.xml","spring_repository.xml"};
       String filePath ="spring-context.xml";
       ApplicationContext context = new ClassPathXmlApplicationContext(filePath);
       /*ITagInfoService itagInfoService = context.getBean(ITagInfoService.class);
       List<TagTypeDto> list =itagInfoService.findList();
       System.out.println(list.size());*/
       IIndexPageService indexPageService = context.getBean(IIndexPageService.class);
       List<QuestionDto> list =indexPageService.getQuestionByDirId(5001l);
       System.out.println(list.get(0).getQuestions().size());
   }
}
