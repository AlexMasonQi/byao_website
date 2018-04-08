package com.byao.website.dao;

import com.byao.website.entity.Media;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaDao
{
    List<Media> selectMediasByParentId(@Param("parentId") Integer parentId);
}
