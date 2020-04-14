package com.baizhi.bqs;

import com.baizhi.bqs.entity.Emp;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PoiTest {
    @Test
    public void test1(){
        //创建一个Excel文件
        Workbook workbook = new HSSFWorkbook();

        //创建一个工作表 参数--工作表名，也可以不写名
        Sheet sheet = workbook.createSheet("学生信息1");


        Sheet sheet2 = workbook.createSheet("学生名单");
        Row row1 = sheet2.createRow(2);
        row1.createCell(0).setCellValue("lalala");


        //创建一行
        Row row = sheet.createRow(4);

        //创建一个单元格
        Cell cell = row.createCell(2);

        cell.setCellValue("这是第5行，第3个单元格");

        ///导出单元格
        try {
            ((HSSFWorkbook) workbook).write(new FileOutputStream(new File("D://186-poi.xls")));
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


        //创建一个Excel文件
        Workbook workbook = new HSSFWorkbook();

        //创建一个工作表 参数--工作表名，也可以不写名
        Sheet sheet = workbook.createSheet("学生信息1");

        //设置列宽  参数：下标，宽度
        sheet.setColumnWidth(3,20*256);

        Font font1 = workbook.createFont();
        font1.setBold(true);
        font1.setColor(Font.COLOR_NORMAL);
        font1.setItalic(true);
        font1.setFontName("微软雅黑");
        font1.setFontHeightInPoints((short)20);
        //创建标题样式
        CellStyle titleCellStyle = workbook.createCellStyle();
        //居中
        titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
        titleCellStyle.setFont(font1);

        //创建一行
        Row titlerow1 = sheet.createRow(0);

        //行高
        titlerow1.setHeight((short)(25*20));
        //创建标题单元格
        Cell titlecell1 = titlerow1.createCell(0);
        //给单元格设置指定样式
        titlecell1.setCellStyle(titleCellStyle);
        //给标题赋值
        titlecell1.setCellValue("186班学生信息");


        //合并单元
        CellRangeAddress cellAddresses = new CellRangeAddress(0, 3, 1, 3);

        //将设置好的合并单元格策略给sheet
        sheet.addMergedRegion(cellAddresses);

        //设置字体样式
        Font font = workbook.createFont();
        font.setBold(true); //加粗
        font.setColor(Font.COLOR_RED);  //设置字体颜色
        font.setFontHeightInPoints((short) 18);  //设置字体大小
        font.setFontName("宋体");  //设置字体
        font.setItalic(true);  //设置斜体

        //创建样式对象
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setFont(font);

        //目录
        String[] titles={"ID","名字","年龄","生日"};
        //创建目录行
        Row row2 = sheet.createRow(1);
        //设置行高   参数：行高(注意：单位为1/20,short类型)
        row2.setHeight((short)(15*20));


        for (int i=2;i<titles.length;i++){

            //创建一个单元格
            Cell cell = row2.createCell(i);
            //这是第5行，第3个单元格
            cell.setCellValue(titles[i]);
            //给字体设置样式
            cell.setCellStyle(cellStyle1);

        }

        //创建日期格式
        DataFormat format = workbook.createDataFormat();
        //设置日期格式
        short formatFormat = format.getFormat("yyyy年MM月dd日");
        //创建样式对象
        CellStyle cellStyle = workbook.createCellStyle();

        cellStyle.setDataFormat(formatFormat);









        //处理数据行
        //遍历数据
        for (int i = 0; i <emps.size() ; i++) {
            //创建行
            Row row = sheet.createRow(i+1);

            //遍历一条数据 创建一行
            row.createCell(0).setCellValue(emps.get(i).getId());
            row.createCell(1).setCellValue(emps.get(i).getName());
            row.createCell(2).setCellValue(emps.get(i).getAge());
           //创建日期单元
            Cell cell = row.createCell(3);
            //给单元格指定样式
            cell.setCellStyle(cellStyle);
            //给单元格赋值
            cell.setCellValue(emps.get(i).getBir());

        }

        //创建单元格 给单元格赋值


        ///导出单元格
        try {
            ((HSSFWorkbook) workbook).write(new FileOutputStream(new File("D://186-poi.xls")));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void testpoiExport() {

        //查询数据库返回集合
        Emp stu1 = new Emp("1","小可爱",16,new Date());
        Emp stu2 = new Emp("2","小蛋黄",16,new Date());
        Emp stu3 = new Emp("3","小狗蛋",12,new Date());
        Emp stu4 = new Emp("4","小嘿嘿",10,new Date());
        Emp stu5 = new Emp("5","小小小",23,new Date());

        List<Emp> students = Arrays.asList(stu1, stu2, stu3, stu4, stu5);

        //创建一个Excel文档
        Workbook workbook = new HSSFWorkbook();

        //创建一个工作薄   参数：工作薄名字(sheet1,shet2....)
        Sheet sheet = workbook.createSheet("用户信息表1");

        //创建一个标题行  参数：行下标(下标从0开始)
        Row row0 = sheet.createRow(0);

        //设置内容
        row0.createCell(0).setCellValue("学生信息");

        //合并行   参数：起始行,结束行,起始单元格,结束单元格
        CellRangeAddress addresses = new CellRangeAddress(2,7,5,5);
        sheet.addMergedRegion(addresses);

        //设置列宽  参数：列索引，列宽(注意：单位为1/256)
        sheet.setColumnWidth(3,20*256);

        //设置字体样式
        Font font = workbook.createFont();
        font.setBold(true); //加粗
        font.setColor(Font.COLOR_RED);  //设置字体颜色
        font.setFontHeightInPoints((short) 24);  //设置字体大小
        font.setFontName("宋体");  //设置字体
        font.setItalic(true);  //设置斜体

        //创建样式对象
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setFont(font);

        //创建一行  参数：行下标(下标从0开始)
        Row row = sheet.createRow(1);

        //设置行高   参数：行高(注意：单位为1/20,short类型)
        row.setHeight((short) 900);

        //给目录行设置数据
        String[] title={"ID","名字","年龄","生日"};
        for (int i = 0; i < title.length; i++) {
            //创建单元格
            Cell cell = row.createCell(i);
            //设置单元格内容
            cell.setCellValue(title[i]);
            //给字体设置样式
            cell.setCellStyle(cellStyle1);
        }

        //创建一个日期格式对象
        DataFormat dataFormat = workbook.createDataFormat();
        //创建一个样式对象
        CellStyle cellStyle = workbook.createCellStyle();
        //将日期格式放入样式对象中
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy年MM月dd日"));

        //处理数据行数据
        for (int i = 0; i < students.size(); i++) {
            //创建行
            Row row1 = sheet.createRow(i + 2);

            //创建单元格
            Cell cell = row1.createCell(0);
            //设置单元格内容
            cell.setCellValue(students.get(i).getId());
            //创建单元格并设置单元格内容
            row1.createCell(1).setCellValue(students.get(i).getName());
            row1.createCell(2).setCellValue(students.get(i).getAge());
            //处理日期数据
            Cell cell1 = row1.createCell(3);
            cell1.setCellValue(students.get(i).getBir());  //设置单元格内容
            cell1.setCellStyle(cellStyle);  //设置单元格日期样式
        }

        //导出单元格
        try {
            workbook.write(new FileOutputStream(new File("F://TestPoi.xls")));

            //释放资源
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public  void  inport(){
        try {
            //获取导入文件
            Workbook workbook = new HSSFWorkbook(new FileInputStream(new File("D://186-poi.xls")));
            Sheet sheet = workbook.getSheet("学生信息1");
           // int lastRowNum = sheet.getLastRowNum();


            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                //获取行
                Row row = sheet.getRow(i);

                String id = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                double ages = row.getCell(2).getNumericCellValue();
                int age= (int) ages;
                Date bir = row.getCell(3).getDateCellValue();

                Emp emp = new Emp(id, name, age, bir);
                System.out.println("向数据库插入 = " + emp);


            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
