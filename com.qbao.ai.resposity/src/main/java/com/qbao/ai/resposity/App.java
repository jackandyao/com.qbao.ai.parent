package com.qbao.ai.resposity;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qbao.ai.resposity.mybatis.dao.StuffPromotionDao;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");//此文件放在SRC目录下

        StuffPromotionDao h=(StuffPromotionDao)context.getBean("stuffPromotionDao");
        System.out.println(h.findStuffPromotionById(25348183111l));
    }

}
