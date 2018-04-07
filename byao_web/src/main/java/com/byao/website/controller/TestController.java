package com.byao.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/test")
public class TestController
{
    @RequestMapping("/music")
    public String testAudio()
    {
        return "Test";
    }

    @RequestMapping("/testEditor")
    @ResponseBody
    public String testEditor(HttpServletRequest request, String ckeditor)
    {
        System.out.println(request.getServletContext().getRealPath(""));
        System.out.println(ckeditor);

        return "OK";
    }

}
