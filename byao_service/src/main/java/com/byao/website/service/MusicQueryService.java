package com.byao.website.service;

import com.byao.website.dao.MusicDao;
import com.byao.website.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicQueryService
{
    @Autowired
    private MusicDao musicDao;

    public Music selectSongsById(Integer musicId)
    {
        return musicDao.selectSongsById(musicId);
    }

    public List<Music> selectAllSongs()
    {
        return musicDao.selectAllSongs();
    }
}
