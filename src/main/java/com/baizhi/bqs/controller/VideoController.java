package com.baizhi.bqs.controller;

import com.baizhi.bqs.entity.Category;
import com.baizhi.bqs.entity.User;
import com.baizhi.bqs.entity.Video;
import com.baizhi.bqs.service.CategoryService;
import com.baizhi.bqs.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("video")
public class VideoController {

    @Resource
    VideoService videoService;

    //查询
    @ResponseBody
    @RequestMapping("queryByVideoPage")
    public HashMap<String,Object> queryByVideoPage(Integer page, Integer rows){
        HashMap<String, Object> map = videoService.queryByVideoPage(page, rows);
        System.out.println("map = " + map);
        return map;
    }


    //修改
    @ResponseBody
    @RequestMapping("edit")//-------------------oper操作
    public Object edit(Video video, String oper){

        if (oper.equals("add")){
            String id = videoService.add(video);
            return id;
        }
        if (oper.equals("edit")){
            videoService.update(video);
        }
        if (oper.equals("del")){
            HashMap<String, Object> delete = videoService.delete(video);
        }
        return "";
    }

    @ResponseBody
    @RequestMapping("uploadVdieo")
    public void uploadVdieo(MultipartFile path, String id, HttpServletRequest request) {
        videoService.uploadVdieos(path, id, request);
    }


    @ResponseBody
    @RequestMapping("querySearch")
    public List<Video> querySearch(String content) {

        List<Video> videos = videoService.querySearchs(content);

    return videos;
    }

}
