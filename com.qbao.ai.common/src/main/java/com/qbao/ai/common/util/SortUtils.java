package com.qbao.ai.common.util;

import org.apache.commons.lang.StringUtils;

/**
 * @author shuaizhihu
 * @createTime 2017/3/9 16:19
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
public class SortUtils {
    public static String getSortField(String sort){
        if(StringUtils.isEmpty(sort)){
            return "";
        }else if(sort.startsWith("sort:")){
            String[] ss = sort.split(":");
            if(ss.length==3){
                return ss[1];
            }else{
                return "";
            }
        }else{
            return "";
        }
    }


    public static String getSortOrderBy(String sort){
        if(StringUtils.isEmpty(sort)){
            return "";
        }else if(sort.startsWith("sort:")){
            String[] ss = sort.split(":");
            if(ss.length==3){
                return ss[2];
            }else{
                return "";
            }
        }else{
            return "";
        }
    }
}
