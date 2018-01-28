package com.qbao.ai.resposity;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qbao.ai.resposity.mybatis.dao.QuestionDirDao;

/**
 * Created by xueming on 2017/4/13.
 */
public class SpringTest {
    /*public static void main(String[] args) {
        String path ="spring-context.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(path);
        JHPTagTypeDao jhpTagTypeDao = context.getBean(JHPTagTypeDao.class);
        System.out.println(jhpTagTypeDao.findList().size());
      
    }*/
    public static void main(String[] args) {
        String path ="spring-context.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(path);
        //TagTypeDao tagTypeDao = context.getBean(TagTypeDao.class);
        //System.out.println(tagTypeDao.findList().size());

        QuestionDirDao questionDirDao = context.getBean(QuestionDirDao.class);
        System.out.println(questionDirDao.findChildrenByPid(1001,1).size());

    }
}
