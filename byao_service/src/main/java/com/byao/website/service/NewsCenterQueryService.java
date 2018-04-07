package com.byao.website.service;

import com.byao.website.dao.NewsCenterDao;
import com.byao.website.entity.NewsCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsCenterQueryService
{
    @Autowired
    private NewsCenterDao newsCenterDao;

    public List<NewsCenter> selectNewsById(Integer id)
    {
        return newsCenterDao.selectNewsCenterById(id);
    }
}
