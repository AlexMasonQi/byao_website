package com.byao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController
{
    @RequestMapping("/audio")
    public String testAudio(Map model)
    {
        model.put("src","");
        return "TestPage";
    }
}
