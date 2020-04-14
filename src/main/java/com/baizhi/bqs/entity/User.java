package com.baizhi.bqs.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "yx_user")
public class User implements Serializable {
    @Id
    @Excel(name = "ID")
    private String id;
    @Excel(name = "名字")
    private String username;
    @ExcelIgnore
    private String phone;
    @Column(name = "head_img")
    @Excel(name = "头像")
    private String headImg;
    @ExcelIgnore
    private String sign;
    @Excel(name = "微信")
    private String wechat;
    @ExcelIgnore
    private String status;
    @Column(name = "create_date")
    @Excel(name = "生日",format = "yyyy-MM-dd")
    private Date createDate;

}