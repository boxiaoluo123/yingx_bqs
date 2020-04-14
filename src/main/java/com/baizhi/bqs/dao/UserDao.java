package com.baizhi.bqs.dao;

import com.baizhi.bqs.entity.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserDao extends Mapper<User> {
        User queryByUsername(String username);

}
