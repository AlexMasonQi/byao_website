package com.byao.website.controller;

import com.byao.website.entity.CompanyInfo;
import com.byao.website.entity.Menu;
import com.byao.website.entity.NewsCenter;
import com.byao.website.service.CompanyInfoQueryService;
import com.byao.website.service.MediaQueryService;
import com.byao.website.service.MenuQueryService;
import com.byao.website.service.NewsCenterQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private MediaQueryService mediaQueryService;

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
    public String listPage(Integer id, Integer parentId, Map model)
    {
        List<Menu> firstMenuList = menuQueryService.selectAllFirstMenu();
        model.put("firstMenuList", firstMenuList);

        List<Menu> secondMenuList = menuQueryService.selectSecondMenu();
        model.put("secondMenuList", secondMenuList);

        var news = newsCenterQueryService.selectNewsById(id);
        var secondMenus = new ArrayList<Menu>();

        for (var menu : firstMenuList)
        {
            if (parentId.equals(menu.getId()))
            {
                secondMenus = menuQueryService.selectSonMenuByParentId(menu.getId(), menu.getLevel() + 1);
                model.put("parentId", menu.getId());
                break;
            }
        }

        model.put("secondMenus", secondMenus);
        model.put("newsList", news);
        model.put("secondId", id);

        return "list";
    }

    @RequestMapping(value = "/companyInfo", method = RequestMethod.GET)
    public String companyInfoPage(Integer id, Integer parentId, Map model)
    {
        List<Menu> firstMenuList = menuQueryService.selectAllFirstMenu();
        model.put("firstMenuList", firstMenuList);

        List<Menu> secondMenuList = menuQueryService.selectSecondMenu();
        model.put("secondMenuList", secondMenuList);

        CompanyInfo companyInformation = companyInfoQueryService.selectCompanyInfoById(id);
        var secondMenus = new ArrayList<Menu>();

        for (Menu menu : firstMenuList)
        {
            if (parentId.equals(menu.getId()))
            {
                secondMenus = menuQueryService.selectSonMenuByParentId(menu.getId(), menu.getLevel() + 1);
                break;
            }
        }
        model.put("secondMenus", secondMenus);

        model.put("companyInformation", companyInformation);

        return "companyInfo";
    }

    @RequestMapping("/media")
    public String mediaPage(Integer id, Integer parentId, Map model)
    {
        List<Menu> firstMenuList = menuQueryService.selectAllFirstMenu();
        model.put("firstMenuList", firstMenuList);

        List<Menu> secondMenuList = menuQueryService.selectSecondMenu();
        model.put("secondMenuList", secondMenuList);

        for (var firstMenu : firstMenuList)
        {
            if (parentId.equals(firstMenu.getId()))
            {
                var secondMenus = menuQueryService.selectSonMenuByParentId(firstMenu.getId(), firstMenu.getLevel() + 1);
                var thirdMenus = mediaQueryService.selectMediasByParentId(id);

                model.put("secondMenus", secondMenus);
                model.put("secondId", id);
                model.put("parentId", parentId);
                model.put("thirdMenus", thirdMenus);

                break;
            }
        }

        return "media";
    }

    @RequestMapping("/showArticalPage")
    public String showArticalPage(Integer mediaId, Integer secondId, Integer parentId, Map model)
    {
        String result = "article";

        List<Menu> firstMenuList = menuQueryService.selectAllFirstMenu();
        model.put("firstMenuList", firstMenuList);

        List<Menu> secondMenuList = menuQueryService.selectSecondMenu();
        model.put("secondMenuList", secondMenuList);

        for (var firstMenu : firstMenuList)
        {
            if (parentId.equals(firstMenu.getId()))
            {
                var secondMenus = menuQueryService.selectSonMenuByParentId(firstMenu.getId(), firstMenu.getLevel() + 1);
                var thirdMenus = mediaQueryService.selectMediasByParentId(secondId);

                for (var thirdMenu : thirdMenus)
                {
                    if (mediaId.equals(thirdMenu.getMediaId()))
                    {
                        switch (mediaId)
                        {
                            //MV
                            case 16:
                            {
                                model.put("secondMenus", secondMenus);
                                model.put("secondId", secondId);
                                model.put("parentId", parentId);
                                model.put("thirdMenu", thirdMenu);
                                result = "mv";
                            }
                            break;

                            //歌曲发行
                            case 23:
                            {
                                model.put("secondMenus", secondMenus);
                                model.put("secondId", secondId);
                                model.put("parentId", parentId);
                                model.put("thirdMenu", thirdMenu);
                                result = "container";
                            }
                            break;

                            default:
                            {
                                model.put("secondMenus", secondMenus);
                                model.put("secondId", secondId);
                                model.put("parentId", parentId);
                                model.put("thirdMenu", thirdMenu);
                                result = "artical";
                            }
                        }
                        break;
                    }
                }

                break;
            }
        }

        return result;
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
