package com.byao.website.controller;

import com.byao.website.entity.Menu;
import com.byao.website.service.MenuQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController
{
    @Autowired
    private MenuQueryService menuQueryService;

    @RequestMapping("/showFirstMenu")
    @ResponseBody
    public List<Menu> showFirstMenu()
    {
        return menuQueryService.selectAllFirstMenu();
    }
}
