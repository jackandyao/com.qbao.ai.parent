package com.qbao.ai.rest;

import com.alibaba.dubbo.container.Main;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by shuaizhihu on 2016/11/29.
 */
public class AiRestMain {
    public static void main(String[] args) throws IOException {
        String[]path=new String[]{"dubbo_rest.xml"};
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(path);
        context.start();
        Main.main(args);
    }

}
