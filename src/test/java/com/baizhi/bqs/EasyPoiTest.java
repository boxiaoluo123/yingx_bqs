package com.baizhi.bqs;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baizhi.bqs.entity.Emp;
import com.baizhi.bqs.entity.Photo;
import com.baizhi.bqs.entity.Teacher;
import com.baizhi.bqs.entity.User;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EasyPoiTest {

    @Test
    public void test1(){
        //1.查询数据
        ArrayList<Emp> emps =new ArrayList<>();
        emps.add(new Emp("1","王主",28,new Date()));
        emps.add(new Emp("2","兔子",25,new Date()));
        emps.add(new Emp("3","猪头",27,new Date()));
        emps.add(new Emp("4","牛脾气",18,new Date()));

        //导出的参数 -----参数：标题，表名
        ExportParams exportParams = new ExportParams("计算机一班学生", "学生");
        //参数：标题，表名，------实体类类对象，导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,Emp.class, emps);

        try {
            workbook.write(new FileOutputStream(new File("D://186-easyPoi.xls")));

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void test2(){
        //1.查询数据
        ArrayList<Emp> emps =new ArrayList<>();
        emps.add(new Emp("1","王主",28,new Date()));
        emps.add(new Emp("2","兔子",25,new Date()));
        emps.add(new Emp("3","猪头",27,new Date()));
        emps.add(new Emp("4","牛脾气",18,new Date()));

        Teacher teacher1 = new Teacher("1","suns",39,emps);
        Teacher teacher2= new Teacher("2","胡新哲",55,emps);

        ArrayList<Teacher> teacherArrayList = new ArrayList<Teacher>();
        teacherArrayList.add(teacher2);
        teacherArrayList.add(teacher1);


        //导出的参数 -----参数：标题，表名
        ExportParams exportParams = new ExportParams("计算机一班学生", "学生");
        //参数：标题，表名，------实体类类对象，导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,Teacher.class, teacherArrayList);

        try {
            workbook.write(new FileOutputStream(new File("D://186-easyPoi.xls")));

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Test
    public  void test3(){

        //创建导入对象
        ImportParams params = new ImportParams();
        params.setTitleRows(1); //表格标题行数,默认0
        params.setHeadRows(2);  //表头行数,默认1

        //获取导入数据
        try {
            FileInputStream   fileInputStream = new FileInputStream(new File("D://186-easyPoi.xls"));
            List<Teacher> teachers = ExcelImportUtil.importExcel(fileInputStream,Teacher.class, params);
            for (Teacher teacher : teachers) {
                System.out.println(teacher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    @Test
    public void test4(){//导出图片
        //1.查询数据
        ArrayList<Photo> photos =new ArrayList<>();
        photos.add(new Photo("1","E:\\san\\source\\code\\yingx_bqs\\src\\main\\webapp\\upload\\photo\\1585552604369-2.jpg",28,new Date()));
        photos.add(new Photo("2","E:\\san\\source\\code\\yingx_bqs\\src\\main\\webapp\\upload\\photo\\1585552604369-2.jpg",25,new Date()));
        photos.add(new Photo("3","E:\\san\\source\\code\\yingx_bqs\\src\\main\\webapp\\upload\\photo\\1585405377808-19.jpg",27,new Date()));
        photos.add(new Photo("4","E:\\san\\source\\code\\yingx_bqs\\src\\main\\webapp\\upload\\photo\\1585405377808-19.jpg",18,new Date()));

        //导出的参数 -----参数：标题，表名
        ExportParams exportParams = new ExportParams("应学APP用户数据", "数据1");
        //参数：标题，表名，------实体类类对象，导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,Photo.class, photos);

        try {
            workbook.write(new FileOutputStream(new File("D://186-user.xls")));

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public  void test5(){//导图带图片的

        //创建导入对象
        ImportParams params = new ImportParams();
        params.setTitleRows(1); //表格标题行数,默认0
        params.setHeadRows(1);  //表头行数,默认1
        //params.setSaveUrl("D://186-cover");
       params.setNeedSave(true);

        //获取导入数据
        try {
            FileInputStream   fileInputStream = new FileInputStream(new File("D://user.xls"));
            List<User> list = ExcelImportUtil.importExcel(fileInputStream,User.class, params);
            for (User user : list){
                System.out.println("photo = " + user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public  void test6(){//
        try {
            //创建导入对象
            ImportParams params = new ImportParams();
            params.setTitleRows(1); //表格标题行数,默认0
            params.setHeadRows(1);  //表头行数,默认1
            params.setNeedSave(true);
            //获取导入数据

            FileInputStream   fileInputStream = new FileInputStream(new File("D://186-user.xls"));
            List<Photo> result = ExcelImportUtil.importExcel(fileInputStream,Photo.class, params);

            for (int i = 0; i < result.size(); i++) {
                System.out.println("ReflectionToStringBuilder.toString(result.get(i)) = " + ReflectionToStringBuilder.toString(result.get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
