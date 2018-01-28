package com.qbao.ai.service.utilities;

import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangping
 * @createTime 2017/4/17 上午11:48
 * $$LastChangedDate: 2017-04-17 14:51:49 +0800 (Mon, 17 Apr 2017) $$
 * $$LastChangedRevision: 128 $$
 * $$LastChangedBy: wangping $$
 */
public class TextExtractUtil {

    private final static int blocksWidth;
    public static Logger logger = Logger.getLogger(TextExtractUtil.class);
    private static List<String> lines;
    private static int threshold;
    private static String html;
    private static boolean flag;
    private static int start;
    private static int end;
    private static StringBuilder text;
    private static ArrayList<Integer> indexDistribution;

    static {
        lines = new ArrayList<>();
        indexDistribution = new ArrayList<>();
        text = new StringBuilder();
        blocksWidth = 3;
        flag = false;
        /* 当待抽取的网页正文中遇到成块的新闻标题未剔除时，只要增大此阈值即可。*/
        /* 阈值增大，准确率提升，召回率下降；值变小，噪声会大，但可以保证抽到只有一句话的正文 */
        threshold = 86;
    }

    public static void setthreshold(int value) {
        threshold = value;
    }

    /**
     * 抽取网页正文，不判断该网页是否是目录型。即已知传入的肯定是可以抽取正文的主题类网页。
     *
     * @param _html 网页HTML字符串
     *
     * @return 网页正文string
     */
    public static String parse(String _html) {
        return parse(_html, false);
    }

    /**
     * 判断传入HTML，若是主题类网页，则抽取正文；否则输出<b>"unkown"</b>。
     *
     * @param _html 网页HTML字符串
     * @param _flag true进行主题类判断, 省略此参数则默认为false
     *
     * @return 网页正文string
     */
    public static String parse(String _html, boolean _flag) {
        flag = _flag;
        html = _html;
        preProcess();
        logger.debug(html);
        return getText();
    }

    private static void preProcess() {
        html = html.replaceAll("(?is)<!DOCTYPE.*?>", "");
        html = html.replaceAll("(?is)<!--.*?-->", "");                // remove html comment
        html = html.replaceAll("(?is)<script.*?>.*?</script>", ""); // remove javascript
        html = html.replaceAll("(?is)<style.*?>.*?</style>", "");   // remove css
        html = html.replaceAll("&.{2,5};|&#.{2,5};", " ");            // remove special char
        html = html.replaceAll("(?is)<.*?>", "");
        //<!--[if !IE]>|xGv00|9900d21eb16fa4350a3001b3974a9415<![endif]-->
    }

    private static String getText() {
        lines = Arrays.asList(html.split("\n"));
        indexDistribution.clear();

        for (int i = 0; i < lines.size() - blocksWidth; i++) {
            int wordsNum = 0;
            for (int j = i; j < i + blocksWidth; j++) {
                lines.set(j, lines.get(j).replaceAll("\\s+", ""));
                wordsNum += lines.get(j).length();
            }
            indexDistribution.add(wordsNum);
            logger.debug(wordsNum + "");
        }

        start = -1;
        end = -1;
        boolean boolstart = false, boolend = false;
        text.setLength(0);

        for (int i = 0; i < indexDistribution.size() - 1; i++) {
            if (indexDistribution.get(i) > threshold && !boolstart) {
                if (indexDistribution.get(i + 1).intValue() != 0
                        || indexDistribution.get(i + 2).intValue() != 0
                        || indexDistribution.get(i + 3).intValue() != 0) {
                    boolstart = true;
                    start = i;
                    continue;
                }
            }
            if (boolstart) {
                if (indexDistribution.get(i).intValue() == 0
                        || indexDistribution.get(i + 1).intValue() == 0) {
                    end = i;
                    boolend = true;
                }
            }
            StringBuilder tmp = new StringBuilder();
            if (boolend) {
                logger.debug(start + 1 + "\t\t" + end + 1);
                for (int ii = start; ii <= end; ii++) {
                    if (lines.get(ii).length() < 5) {
                        continue;
                    }
                    tmp.append(lines.get(ii)).append("\n");
                }
                String str = tmp.toString();
                logger.debug(str);
                if (str.contains("Copyright") || str.contains("版权所有")) {
                    continue;
                }
                text.append(str);
                boolstart = boolend = false;
            }
        }
        return text.toString();
    }
}
