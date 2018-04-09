package com.byao.website.dao;

import com.byao.website.entity.BasicInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicInfoDao
{
    BasicInfo selectBasicInfoByStatus();
}
