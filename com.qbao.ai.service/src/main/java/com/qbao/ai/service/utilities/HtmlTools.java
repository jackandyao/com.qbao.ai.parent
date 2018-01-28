package com.qbao.ai.service.utilities;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

/**
 * @author wangping
 * @createTime 2017/4/17 上午11:46
 * $$LastChangedDate: 2017-04-17 14:51:49 +0800 (Mon, 17 Apr 2017) $$
 * $$LastChangedRevision: 128 $$
 * $$LastChangedBy: wangping $$
 */
public class HtmlTools {
    public static Logger logger = Logger.getLogger(HtmlTools.class);

    public static String getHTMLContent(String url) {
        return getHTMLContent(url, "utf-8");
    }

    public static String getHTMLContent(String url, String encoding) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream(), encoding));
            StringBuilder html = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                html.append(line).append("\n");
                line = reader.readLine();
            }
            String content = TextExtractUtil.parse(html.toString());
            return content;
        } catch (Exception e) {
            logger.debug("解析URL失败：" + url, e);
        }
        return null;
    }

    public static void copyFile(InputStream in, File outFile) {
        OutputStream out = null;
        try {
            byte[] data = readAll(in);
            out = new FileOutputStream(outFile);
            out.write(data, 0, data.length);
            out.close();
        } catch (IOException ex) {
            logger.error("文件操作失败", ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                logger.error("文件操作失败", ex);
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                logger.error("文件操作失败", ex);
            }
        }
    }

    public static byte[] readAll(InputStream in) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            for (int n; (n = in.read(buffer)) > 0; ) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            logger.error("读取失败", e);
        }
        return out.toByteArray();
    }
}
