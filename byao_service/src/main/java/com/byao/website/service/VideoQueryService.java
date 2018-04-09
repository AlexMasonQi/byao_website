package com.byao.website.service;

import com.byao.website.dao.VideoDao;
import com.byao.website.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoQueryService
{
    @Autowired
    private VideoDao videoDao;

    public Video selectVideoById(Integer id)
    {
        return videoDao.selectVideoById(id);
    }

    public List<Video> selectAllVideos()
    {
        return videoDao.selectAllVideos();
    }
}
