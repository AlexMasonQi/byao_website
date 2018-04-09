package com.byao.website.service;

import com.byao.website.dao.BasicInfoDao;
import com.byao.website.entity.BasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicInfoQueryService
{
    @Autowired
    private BasicInfoDao basicInfoDao;

    public BasicInfo selectBasicInfoByStatus()
    {
        return basicInfoDao.selectBasicInfoByStatus();
    }
}
