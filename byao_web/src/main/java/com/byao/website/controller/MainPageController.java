package com.byao.website.controller;

import com.byao.website.entity.CompanyInfo;
import com.byao.website.entity.Menu;
import com.byao.website.entity.NewsCenter;
import com.byao.website.service.CompanyInfoQueryService;
import com.byao.website.service.MenuQueryService;
import com.byao.website.service.NewsCenterQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainPageController
{
    @Autowired
    private MenuQueryService menuQueryService;

    @Autowired
    private CompanyInfoQueryService companyInfoQueryService;

    @Autowired
    private NewsCenterQueryService newsCenterQueryService;

    @RequestMapping("/index")
    public String gotoMainPage(Map model)
    {
        List<Menu> firstMenuList = menuQueryService.selectAllFirstMenu();
        model.put("firstMenuList", firstMenuList);

        List<Menu> secondMenuList = menuQueryService.selectSecondMenu();
        model.put("secondMenuList", secondMenuList);

        List<Menu> thirdMenuList = menuQueryService.selectThirdMenu();
        model.put("thirdMenuList", thirdMenuList);

        return "index";
    }

    @RequestMapping("/list")
    public String listPage(Integer id, Map model)
    {
        List<Menu> firstMenuList = menuQueryService.selectAllFirstMenu();
        model.put("firstMenuList", firstMenuList);

        List<Menu> secondMenuList = menuQueryService.selectSecondMenu();
        model.put("secondMenuList", secondMenuList);

        var news = newsCenterQueryService.selectNewsById(id);
        var secondMenus = new ArrayList<Menu>();

        for (var center : news)
        {
            for (var menu : firstMenuList)
            {
                if (center.getParentId() == menu.getId())
                {
                    secondMenus = menuQueryService.selectSecondMenuByParentId(menu.getId(), menu.getLevel() + 1);
                    model.put("parentId", menu.getId());
                    break;
                }
            }
        }

        model.put("secondMenus", secondMenus);
        model.put("newsList", news);
        model.put("secondId", id);

        return "list";
    }

    @RequestMapping(value = "/companyInfo", method = RequestMethod.GET)
    public String containerPage(Integer id, Map model)
    {
        List<Menu> firstMenuList = menuQueryService.selectAllFirstMenu();
        model.put("firstMenuList", firstMenuList);

        List<Menu> secondMenuList = menuQueryService.selectSecondMenu();
        model.put("secondMenuList", secondMenuList);

        CompanyInfo companyInfomation = companyInfoQueryService.selectCompanyInfoById(id);
        var secondMenus = new ArrayList<Menu>();

        for (Menu menu : firstMenuList)
        {
            if (companyInfomation.getParentId() == menu.getId())
            {
                secondMenus = menuQueryService.selectSecondMenuByParentId(menu.getId(), menu.getLevel() + 1);
                break;
            }
        }
        model.put("secondMenus", secondMenus);

        model.put("companyInformation", companyInfomation);

        return "companyInfo";
    }

    @RequestMapping("/media")
    public String mediaPage(Map model)
    {
        List<Menu> firstMenuList = menuQueryService.selectAllFirstMenu();
        model.put("firstMenuList", firstMenuList);

        List<Menu> secondMenuList = menuQueryService.selectSecondMenu();
        model.put("secondMenuList", secondMenuList);

        return "media";
    }

    @RequestMapping("/container")
    public String containerPage(Map model)
    {
        List<Menu> firstMenuList = menuQueryService.selectAllFirstMenu();
        model.put("firstMenuList", firstMenuList);

        List<Menu> secondMenuList = menuQueryService.selectSecondMenu();
        model.put("secondMenuList", secondMenuList);

        return "container";
    }
}
