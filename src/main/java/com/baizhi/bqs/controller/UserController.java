package com.baizhi.bqs.controller;


import com.baizhi.bqs.entity.User;
import com.baizhi.bqs.service.UserService;
import com.baizhi.bqs.util.AliyunSendPhoneUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RequestMapping("user")
@Controller
public class UserController {

    @Resource
    UserService userService;

//查询
    @ResponseBody
    @RequestMapping("queryByPage")
    public HashMap<String,Object> queryByPage(Integer page,Integer rows){
        HashMap<String, Object> map = userService.queryByPage(page,rows);
        System.out.println("用户con = " + map);
        return map;
    }
//修改
    @ResponseBody
    @RequestMapping("edit")
    public String edit(User user,String oper){

        System.out.println("user = " + user);
        String uid =null;
        if(oper.equals("add")){

            uid = userService.add(user);
        }

        if(oper.equals("edit")){
            userService.update(user);
        }

        if(oper.equals("del")){
            userService.delete(user);
        }
        return uid;
    }
    //文件上传
    @RequestMapping("uploadUser")
    public  void uploadUser(MultipartFile headImg,String id,HttpServletRequest request){
        //userService.uploadUser(headImg,id,request);//上传本地
        userService.uploadUserAliyun(headImg,id,request);//上传阿里云

    }
    @RequestMapping("sendPhoneCode")
    @ResponseBody
    public String sendPhoneCode(String phone){

        //获取随机数
        String random = AliyunSendPhoneUtil.getRandom(6);

        System.out.println("存储验证码："+random);

        //发送验证码
        String message = AliyunSendPhoneUtil.sendCode(phone, random);

        System.out.println(message);
        return message;
    }
    //导出所有用户数据

    @ResponseBody
    @RequestMapping("outPutUser")
    public void outPutUser() {

        userService.outPutUserExcel();

    }
}
