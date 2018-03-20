package com.byao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController
{
    @RequestMapping("/index")
    public String gotoMainPage()
    {
        return "index";
    }

    @RequestMapping("/list")
    public String listPage()
    {
        return "list";
    }
}
