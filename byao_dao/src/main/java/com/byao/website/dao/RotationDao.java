package com.byao.website.dao;

import com.byao.website.entity.Rotation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RotationDao
{
    int selectImagesCount();

    List<Rotation> selectImageRotationByCount(@Param("count") Integer count);
}
