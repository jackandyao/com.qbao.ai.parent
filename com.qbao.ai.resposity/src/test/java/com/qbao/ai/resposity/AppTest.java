//package com.qbao.ai.resposity;
//
//
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.util.Date;
//import java.util.HashSet;
//
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.qbao.ai.resposity.mybatis.dao.QuestionDirDao;
//import com.qbao.ai.resposity.mybatis.dao.QuestionInfoDao;
//import com.qbao.ai.resposity.mybatis.model.QuestionDir;
//import com.qbao.ai.resposity.mybatis.model.QuestionInfo;
//
///**
// * Unit test for simple App.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({ "classpath:spring-resposity.xml"})
//public class AppTest
//{
//    @Autowired
//    QuestionDirDao questionDirDao;
//    @Autowired
//    QuestionInfoDao questionInfoDao;
//
//
//
//    // @Test
//    public void rootDirInit() throws Exception {
//    	// poi读取excel
//        //创建要读入的文件的输入流
//        InputStream inp = new FileInputStream("/Users/allen/Desktop/aa.xlsx");
//
//        //根据上述创建的输入流 创建工作簿对象
//        Workbook wb = WorkbookFactory.create(inp);
//        //得到第一页 sheet
//        //页Sheet是从0开始索引的
//        Sheet sheet = wb.getSheetAt(0);
//        int rootDirCount =0;
//        HashSet<String> dirSet = new HashSet<String>();
//        //利用foreach循环 遍历sheet中的所有行
//        for (Row row : sheet) {
//        	 if(dirSet.add(row.getCell(0)+"")){
//        	 rootDirCount++;
//             QuestionDir dir = new QuestionDir();
//             dir.setCreateTime(new Date());
//             dir.setUpdateTime(new Date());
//             dir.setDirName(row.getCell(0)+"");
//             dir.setLev(1);
//             dir.setPid(0);
//             dir.setDirId(1000+rootDirCount);
//             questionDirDao.insert(dir);
//          }
//        }
//        //关闭输入流
//        inp.close();
//
//    }
//
//
//
//
//    //@Test
//    public void secDirInit() throws  Exception {
//
//	// poi读取excel
//       //创建要读入的文件的输入流
//       InputStream inp = new FileInputStream("/Users/allen/Desktop/aa.xlsx");
//
//       //根据上述创建的输入流 创建工作簿对象
//       Workbook wb = WorkbookFactory.create(inp);
//       //得到第一页 sheet
//       //页Sheet是从0开始索引的
//       Sheet sheet = wb.getSheetAt(0);
//       HashSet<String> dirSet = new HashSet<String>();
//       int rootDirCount =0;
//       //利用foreach循环 遍历sheet中的所有行
//       for (Row row : sheet) {
//    	   if(dirSet.add(row.getCell(1)+"")){
//           	try{
//               rootDirCount++;
//               QuestionDir dir = new QuestionDir();
//               dir.setCreateTime(new Date());
//               dir.setUpdateTime(new Date());
//               dir.setDirName(row.getCell(1)+"");
//               dir.setLev(2);
//               QuestionDir pdir = questionDirDao.findByName(row.getCell(0)+"");
//               dir.setPid(pdir.getDirId());
//               dir.setDirId(5000+rootDirCount);
//               questionDirDao.insert(dir);
//           	}catch(Exception ex){
//           		System.out.println("dirName:"+row.getCell(1) +"findByName"+row.getCell(0));
//           		ex.printStackTrace();
//           	}
//           }
//       }
//       //关闭输入流
//       inp.close();
//
//
//    }
//
//
//
//     @Test
//    public void infoInit() throws Exception {
//    	// poi读取excel
//        //创建要读入的文件的输入流
//        InputStream inp = new FileInputStream("/Users/allen/Desktop/aa.xlsx");
//
//        //根据上述创建的输入流 创建工作簿对象
//        Workbook wb = WorkbookFactory.create(inp);
//        //得到第一页 sheet
//        //页Sheet是从0开始索引的
//        Sheet sheet = wb.getSheetAt(0);
//        //利用foreach循环 遍历sheet中的所有行
//        for (Row row : sheet) {
//        	QuestionInfo info = new QuestionInfo();
//            info.setCreateTime(new Date());
//            info.setUpdateTime(new Date());
//            QuestionDir dir = questionDirDao.findByName(row.getCell(1)+"");
//            if(dir!=null) {
//                info.setDirId(dir.getDirId());
//            }
//            QuestionDir rootdir = questionDirDao.findByName(row.getCell(0)+"");
//            if(rootdir != null) {
//                info.setRootDirId(rootdir.getDirId());
//            }
//            info.setAnswer(row.getCell(3)+"");
//            info.setQuestion(row.getCell(2)+"");
//            info.setStatus(1);
//            questionInfoDao.insert(info);
//        }
//        //关闭输入流
//        inp.close();
//
//    }
//
//}
