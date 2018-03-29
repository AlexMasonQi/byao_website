package com.byao.website.controller;

import com.byao.website.service.MenuQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MainPageController
{
    @Autowired
    private MenuQueryService menuQueryService;

    @RequestMapping("/index")
    public String gotoMainPage(Map model)
    {
        model.put("firstMenuList", menuQueryService.selectAllFirstMenu());
        return "index";
    }

    @RequestMapping("/list")
    public String listPage()
    {
        return "list";
    }
}
