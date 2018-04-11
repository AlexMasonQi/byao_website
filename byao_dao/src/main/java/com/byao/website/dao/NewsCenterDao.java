package com.byao.website.dao;

import com.byao.website.entity.NewsCenter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsCenterDao
{
    List<NewsCenter> selectNewsCenterById(@Param("id") Integer id);

    NewsCenter selectNewsByNewsId(@Param("newsId") Integer newsId);

    List<NewsCenter> selectthreeNews();
}
