package com.byao.website.controller;

import com.byao.website.entity.Menu;
import com.byao.website.service.MenuQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class MainPageController
{
    @Autowired
    private MenuQueryService menuQueryService;

    @RequestMapping("/index")
    public String gotoMainPage(Map model)
    {
        List<Menu> firstMenuList = menuQueryService.selectAllFirstMenu();
        model.put("firstMenuList", firstMenuList);

        List<Menu> secondMenuList = menuQueryService.selectSecondMenu();

        return "index";
    }

    @RequestMapping("/list")
    public String listPage()
    {
        return "list";
    }
}
