package com.baizhi.bqs.service;

import com.baizhi.bqs.entity.Video;
import com.baizhi.bqs.vo.VideoVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface VideoService {
    //查询
    HashMap<String,Object> queryByVideoPage(Integer page, Integer rows);

    String add(Video video);

    void update(Video video);

    void uploadVdieo (MultipartFile path, String id, HttpServletRequest request);

    void uploadVdieos(MultipartFile path, String id, HttpServletRequest request);

    HashMap<String, Object> delete(Video video);

    List<VideoVo> queryByReleaseTime();


    //检索
    List<Video> querySearch(String content);

    List<Video> querySearchs(String content);

}
