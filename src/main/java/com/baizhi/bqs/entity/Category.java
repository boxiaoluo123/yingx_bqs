package com.baizhi.bqs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "yx_category")
public class Category implements Serializable {
    private String id;

    @Column(name = "cate_name")
    private String cateName;

    private Integer levels;

    @Column(name = "parent_id")
    private String parentId;

}