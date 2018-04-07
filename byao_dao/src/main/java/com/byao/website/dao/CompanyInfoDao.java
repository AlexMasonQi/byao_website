package com.byao.website.dao;

import com.byao.website.entity.CompanyInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyInfoDao
{
    CompanyInfo selectCompanyInfoById(Integer id);
}
