package com.baizhi.bqs.serviceImpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baizhi.bqs.annotation.AddCache;
import com.baizhi.bqs.annotation.AddLog;
import com.baizhi.bqs.annotation.DelCahe;
import com.baizhi.bqs.dao.UserDao;
import com.baizhi.bqs.entity.Photo;
import com.baizhi.bqs.entity.Teacher;
import com.baizhi.bqs.entity.User;
import com.baizhi.bqs.entity.UserExample;
import com.baizhi.bqs.service.UserService;
import com.baizhi.bqs.util.AliyunOssUtil;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;


    @Override
    public String add(User user) {
        String uid = UUID.randomUUID().toString();
        user.setId(uid);
        user.setStatus("1");
        user.setCreateDate(new Date());
        userDao.insert(user);
        return uid;
    }

    @Override
    public void uploadUser(MultipartFile headImg, String id, HttpServletRequest request) {
        //根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/photo");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //2获取文件名
        String filename = headImg.getOriginalFilename();
        String newName = new Date().getTime() + "-" + filename;


        try {
            //3文件上传
            headImg.transferTo(new File(realPath, newName));

            //4图片修改
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(id);

            User user = new User();
            user.setHeadImg(newName);//设置修改的结果

            //修改数据
            userDao.updateByExampleSelective(user, example);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AddCache
    @AddLog(value = "查询用户")
    @Override
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {

        HashMap<String, Object> map = new HashMap<>();
        //封装数据
        //总条数 record
        UserExample example = new UserExample();
        Integer records = userDao.selectCountByExample(example);
        map.put("records", records);
        //总页数 total 总条数/每页展示条数  是否有余数
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);

        //当前页 page
        map.put("page", page);
        //数据
        //参数 忽略条数，获取几条
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<User> users = userDao.selectByRowBounds(new User(), rowBounds);
        map.put("rows", users);

        return map;
    }

    @DelCahe
    @Override
    public void update(User user) {

        userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public void delete(User user) {

        userDao.deleteByPrimaryKey(user);
    }

    @Override
    public void uploadUserAliyun(MultipartFile headImg, String id, HttpServletRequest request) {
        //将文件转为byte数组
        byte[] bytes = null;
        try {
            bytes = headImg.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取文件名
        String filename = headImg.getOriginalFilename();
        String newName = new Date().getTime() + "-" + filename;

        //1文件上传阿里云
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4Fi9k6HFSxn5s35mGnw1";
        String accessKeySecret = "2xzuEZoU2hqHEfLNULP2rpjODl59zt";
        String bucketName = "yingx3";  //存储空间名


        String fileName = newName;  //文件名  可以指定文件目录


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传Byte数组。
        ossClient.putObject(bucketName, fileName, new ByteArrayInputStream(bytes));

        // 关闭OSSClient。
        ossClient.shutdown();

        // 关闭OSSClient。
        ossClient.shutdown();


        //2图片信息的修改
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(id);

        User user = new User();
        user.setHeadImg("http://yingx3.oss-cn-beijing.aliyuncs.com/" + newName);//设置修改的结果     // 网络路径   http://yingx3.oss-cn-beijing.aliyuncs.com/1585729748378-25.jpg

        //修改数据
        userDao.updateByExampleSelective(user, example);


    }

    @Override
    public void uploadUserAliyuns(MultipartFile headImg, String id, HttpServletRequest request) {
        //获取文件名
        String filename = headImg.getOriginalFilename();
        String newName = new Date().getTime() + "-" + filename;

        //1.文件上传至阿里云
        AliyunOssUtil.uploadFileBytes("yingx3", headImg, newName);

        //截取视频第一帧
        //上传封面

        //2.图片信息的修改
        //修改的条件
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(id);

        User user = new User();
        user.setHeadImg("https://yingx3.oss-cn-beijing.aliyuncs.com/" + newName);  //设置修改的结果   网络路径
        //https://yingx3.oss-cn-beijing.aliyuncs.com/1585641490828-9.jpg

        //修改
        userDao.updateByExampleSelective(user, example);

    }
    //导出所有用户数据

    @Override
    public void outPutUserExcel(){

        List<User> list = userDao.selectAll();

        //导出的参数 -----参数：标题，表名
        ExportParams exportParams = new ExportParams("应学APP用户数据", "数据1");
        //参数：标题，表名，------实体类类对象，导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,User.class, list);

        try {
            workbook.write(new FileOutputStream(new File("E://user.xls")));

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


