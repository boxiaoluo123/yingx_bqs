package com.baizhi.bqs.service;

import com.baizhi.bqs.entity.Admin;

import java.util.HashMap;

public interface AdminService {

    HashMap<String,Object> login(Admin admin, String enCode);


}
