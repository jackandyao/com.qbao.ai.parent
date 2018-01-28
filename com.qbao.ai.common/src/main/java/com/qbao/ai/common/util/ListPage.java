package com.qbao.ai.common.util;

/**
 *  List分页测试
 * Created by xueming on 2017/3/14.
 */
import java.util.ArrayList;
import java.util.List;

public class ListPage{

/*    public static void main(String[] args) throws Exception {
        List<Object> p = new ArrayList<Object>();
        for(int i = 1; i <= 100; i++){
            p.add(i);
            System.out.println(i+ "-" + p.get(i-1));
        }
        List<Object> result = page(3,15,p);
        for(int i = 0; i < result.size(); i ++){
            System.out.println(result.get(i));
        }
    }*/

    /**
     *
     * @param pageNo 当前页码
     * @param pageSize 页数
     * @param list  所有集合
     * @return
     * @throws Exception
     */
    public static <T> List<T> page(int pageNo,int pageSize,List<T> list){
        if((list.size()+pageSize)<pageNo*pageSize){
            return new ArrayList<T>();
        }
        List<T> result = new ArrayList<T>();
        if(list != null && list.size() > 0){
            int allCount = list.size();
            int pageCount = (allCount + pageSize-1) / pageSize;
            if(pageNo >= pageCount){
                pageNo = pageCount;
            }
            int start = (pageNo-1) * pageSize;
            int end = pageNo * pageSize;
            if(end >= allCount){
                end = allCount;
            }
            for(int i = start; i < end; i ++){
                result.add(list.get(i));
            }


        }
        return (result != null && result.size() > 0) ? result : new ArrayList<T>();
    }
}
