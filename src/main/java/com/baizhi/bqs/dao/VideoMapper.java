package com.baizhi.bqs.dao;

import com.baizhi.bqs.entity.Category;
import com.baizhi.bqs.entity.Video;
import com.baizhi.bqs.entity.VideoExample;
import java.util.List;

import com.baizhi.bqs.po.VideoPo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface VideoMapper extends Mapper<Video> {

    List<VideoPo>  queryByReleaseTime();

}