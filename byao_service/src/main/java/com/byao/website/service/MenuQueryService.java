package com.byao.website.service;

import com.byao.website.dao.MenuDao;
import com.byao.website.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuQueryService
{
    @Autowired
    private MenuDao menuDao;
    
    public List<Menu> selectAllFirstMenu()
    {
        return menuDao.selectAllFirstMenu();
    }

    public List<Menu> selectSecondMenu()
    {
        return menuDao.selectAllSecondMenu();
    }

    public List<Menu> selectThirdMenu()
    {
        return menuDao.selectAllThirdMenu();
    }

    public Menu selectMenuById(Integer id)
    {
        return menuDao.selectMenuById(id);
    }

    public ArrayList<Menu> selectSonMenuByParentId(Integer id, Integer level)
    {
        return menuDao.selectSonMenuByParentId(id, level);
    }
}
