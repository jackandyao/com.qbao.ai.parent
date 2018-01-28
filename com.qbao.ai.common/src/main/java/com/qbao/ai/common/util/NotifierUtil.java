/**
 * 
 */
package com.qbao.ai.common.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.net.SMTPAppender;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liaijun
 * @createTime 17/3/27
 * $$LastChangedDate: 2017-03-29 19:09:14 +0800 (周三, 29 三月 2017) $$
 * $$LastChangedRevision: 582 $$
 * $$LastChangedBy: liaijun $$
 */
public class NotifierUtil {

    public static Set<String> msgKeys = new HashSet<>();
    static Logger logger = Logger.getLogger(NotifierUtil.class);

    public static void notifyByPhone(String msg) {

        if (!msgKeys.contains(msg)) {
            msgKeys.add(msg);
           // AlaramServiceFacotryFacade.sendAlaramMessageByPhone(MessageConstanUtil.PHONE_ERROR, msg);
        }
    }

    public static void notifyByEmail(String subject, String message) {
        if (!msgKeys.contains(subject)) {
            msgKeys.add(subject);
            SMTPAppender appender = new SMTPAppender();
            try {
                appender.setSMTPUsername("jackaiyaoforever@126.com");
                appender.setSMTPPassword("aa11ss33");
                appender.setTo("786648643@qq.com,420709335@qq.com,122715341@qq.com");
                appender.setFrom("jackaiyaoforever@126.com");
                appender.setSMTPHost("smtp.126.com");
                appender.setLocationInfo(true);
                appender.setSubject(subject);
                appender.setLayout(new PatternLayout());
                appender.activateOptions();
                logger.addAppender(appender);
                logger.error(message.toString());
            } catch (Exception e) {
                logger.error("调用LOG4J发送邮件失败", e);
            }
        }
    }
}
