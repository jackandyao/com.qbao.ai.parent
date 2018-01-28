package com.qbao.ai.util;

/**
 * Created by xueming on 2017/4/13.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FileCompare {
    public FileCompare() {
    }
    private final String type = ".jar";

    private void getDir(File f,Map<String, Long> map, int level) {
        File[] childs = f.listFiles();
        for (int i = 0; i < childs.length; i++) {
          /*  if (childs[i].isDirectory()) {
                this.getDir(childs[i],map,level + 1);
            }else{
                if(type.equals(childs[i].getName().substring(childs[i].getName().lastIndexOf(".")))){
                    map.put(childs[i].getName(), childs[i].length());
                }
            }*/
            map.put(childs[i].getName(), childs[i].length());
        }

    }

    /**
     * 查询两个文件夹下所有的不同文件，并保存到批定的文件中
     * @param sourceFolder 源文件夹路径
     * @param targetFolder 目标文件夹路径
     * @param saveFile     保存的文件路径
     * @author gzq
     */
    private void compareFile(String sourceFolder,String targetFolder,String saveFile){
        Map<String, Long> sourceMap = new HashMap<String, Long>();
        Map<String, Long> targetMap = new HashMap<String, Long>();
        new FileCompare().getDir(new File(sourceFolder), sourceMap,0);
        new FileCompare().getDir(new File(targetFolder), targetMap,0);

        File f = new File(saveFile);
        if (f.exists()){
            try {
                f.delete();
                f.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        else{
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        String source = null;

        byte[] bytes = null;
        OutputStream os;
        try {
            os = new FileOutputStream(saveFile);

            Set set = targetMap.entrySet();
            Iterator i = set.iterator();
            while (i.hasNext()) {
                Map.Entry<String, Long> entry1 = (Map.Entry<String, Long>) i.next();
                if (!targetMap.get(entry1.getKey()).equals(sourceMap.get(entry1.getKey()))) {
                    source = entry1.getKey()+"\r\n";
                    bytes =  source.getBytes();

                    os.write(bytes);
                }
            }
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args){
        new FileCompare().compareFile("C:\\Users\\dell\\Desktop\\lib", "C:\\Users\\dell\\Desktop\\keyipao\\com.qbao.ai.web\\WEB-INF\\lib", "C:\\Users\\dell\\Desktop\\keyipao\\compareFile.txt");
        System.out.println("0");
    }
}