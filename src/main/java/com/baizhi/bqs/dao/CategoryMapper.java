package com.baizhi.bqs.dao;

import com.baizhi.bqs.entity.Category;
import com.baizhi.bqs.entity.CategoryExample;
import java.util.List;

import com.baizhi.bqs.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface CategoryMapper extends Mapper<Category> {
    void  update(Category category);

}