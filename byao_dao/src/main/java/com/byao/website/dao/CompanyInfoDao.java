package com.byao.website.dao;

import com.byao.website.entity.CompanyInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyInfoDao
{
    CompanyInfo selectCompanyInfoById(Integer id);

    List<CompanyInfo> selectAllCompanyInfo();
}
