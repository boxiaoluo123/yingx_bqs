package com.baizhi.bqs.service;

import com.baizhi.bqs.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface UserService {

    String add(User user);

    void uploadUser(MultipartFile headImg, String id, HttpServletRequest request);

    HashMap<String,Object> queryByPage(Integer page, Integer rows);

    void update(User user);

    void  delete(User user);
    void uploadUserAliyun(MultipartFile headImg, String id, HttpServletRequest request);
    void uploadUserAliyuns(MultipartFile headImg, String id, HttpServletRequest request);

     void outPutUserExcel();
}
