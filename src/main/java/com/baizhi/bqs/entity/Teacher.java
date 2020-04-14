package com.baizhi.bqs.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Teacher {

    @ExcelIgnore
    private String id;
    @Excel(name ="名字",needMerge = true)//纵向合并
    private String name;
    @Excel(name ="年龄",needMerge = true)
    private Integer age;
    //关系属性
    @ExcelCollection(name = "学生信息")
    private List<Emp> emplist;

}
