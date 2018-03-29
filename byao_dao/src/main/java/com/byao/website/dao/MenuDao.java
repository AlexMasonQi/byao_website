package com.byao.website.dao;

import com.byao.website.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao
{
    List<Menu> selectAllFirstMenu();
}
