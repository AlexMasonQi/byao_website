package com.byao.website.dao;

import com.byao.website.entity.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoDao
{
    Video selectVideoById(@Param("id") Integer id);

    List<Video> selectAllVideos();
}
