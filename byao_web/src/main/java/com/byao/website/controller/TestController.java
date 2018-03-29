package com.byao.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController
{
    @RequestMapping("/music")
    public String testAudio()
    {
        return "Test";
    }
}
