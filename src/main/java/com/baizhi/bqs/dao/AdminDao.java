package com.baizhi.bqs.dao;

import com.baizhi.bqs.entity.Admin;
import tk.mybatis.mapper.common.Mapper;

public interface AdminDao {

    Admin queryByUsername(String username);
}
