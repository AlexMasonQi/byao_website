package com.byao.website.controller;

import com.byao.website.entity.*;
import com.byao.website.service.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
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

    @Autowired
    private MusicQueryService musicQueryService;

    @Autowired
    private BasicInfoQueryService basicInfoQueryService;

    @Autowired
    private VideoQueryService videoQueryService;

    @RequestMapping("/")
    public String gotoMainPage(Map model)
    {
        List<Menu> firstMenuList = menuQueryService.selectAllFirstMenu();
        model.put("firstMenuList", firstMenuList);

        List<Menu> secondMenuList = menuQueryService.selectSecondMenu();
        model.put("secondMenuList", secondMenuList);

        List<Menu> thirdMenuList = menuQueryService.selectThirdMenu();
        model.put("thirdMenuList", thirdMenuList);

        BasicInfo basicInfo = basicInfoQueryService.selectBasicInfoByStatus();
        model.put("basicInfo", basicInfo);

        List<Rotation> rotationList = menuQueryService.selectImagesByCount(menuQueryService.selectImagesCount());
        model.put("imageList", rotationList);

        List<Menu> secondMenus = menuQueryService.selectSonMenuByParentId(3, 2);
        model.put("secondMenus", secondMenus);

        for (var i = 0; i < secondMenus.size(); i++)
        {
            List<Media> mediaList = mediaQueryService.selectMediasByParentId(secondMenus.get(i).getId());

            List<Media> resultMediaList = new ArrayList<>();

            for (var j = 0; j < 2; j++)
            {
                resultMediaList.add(mediaList.get(j));
            }
            model.put("medias" + i, resultMediaList);
        }

        List<NewsCenter> newsList = newsCenterQueryService.selectThreeNews();
        model.put("newsList", newsList);

        return "index";
    }

    @RequestMapping("/list")
    public String listPage(Integer id, Integer parentId, Map model)
    {
        List<Menu> firstMenuList = menuQueryService.selectAllFirstMenu();
        model.put("firstMenuList", firstMenuList);

        List<Menu> secondMenuList = menuQueryService.selectSecondMenu();
        model.put("secondMenuList", secondMenuList);

        BasicInfo basicInfo = basicInfoQueryService.selectBasicInfoByStatus();
        model.put("basicInfo", basicInfo);

        List<Rotation> rotationList = menuQueryService.selectImagesByCount(menuQueryService.selectImagesCount());
        model.put("imageList", rotationList);

        List<NewsCenter> news = newsCenterQueryService.selectNewsById(id);

        List<Menu> secondMenus = new ArrayList<Menu>();

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
        model.put("parentId", parentId);
        model.put("newsList", news);
        model.put("secondId", id);

        return "list";
    }

    @RequestMapping("/showNewsPage")
    public String showNewsPage(Integer newsId, Integer secondId, Integer parentId, Map model)
    {
        List<Menu> firstMenuList = menuQueryService.selectAllFirstMenu();
        model.put("firstMenuList", firstMenuList);

        List<Menu> secondMenuList = menuQueryService.selectSecondMenu();
        model.put("secondMenuList", secondMenuList);

        BasicInfo basicInfo = basicInfoQueryService.selectBasicInfoByStatus();
        model.put("basicInfo", basicInfo);

        List<Rotation> rotationList = menuQueryService.selectImagesByCount(menuQueryService.selectImagesCount());
        model.put("imageList", rotationList);

        NewsCenter newsInfo = newsCenterQueryService.selectNewsByNewsId(newsId);
        model.put("newsInfo", newsInfo);
        model.put("parentId", parentId);
        model.put("secondId", secondId);

        List<Menu> secondMenus = new ArrayList<Menu>();

        for (var menu : firstMenuList)
        {
            if (parentId.equals(menu.getId()))
            {
                secondMenus = menuQueryService.selectSonMenuByParentId(menu.getId(), menu.getLevel() + 1);
                break;
            }
        }

        model.put("secondMenus", secondMenus);

        return "news";
    }

    @RequestMapping(value = "/companyInfo", method = RequestMethod.GET)
    public String companyInfoPage(Integer id, Integer parentId, Map model)
    {
        List<Menu> firstMenuList = menuQueryService.selectAllFirstMenu();
        model.put("firstMenuList", firstMenuList);

        List<Menu> secondMenuList = menuQueryService.selectSecondMenu();
        model.put("secondMenuList", secondMenuList);

        BasicInfo basicInfo = basicInfoQueryService.selectBasicInfoByStatus();
        model.put("basicInfo", basicInfo);

        List<Rotation> rotationList = menuQueryService.selectImagesByCount(menuQueryService.selectImagesCount());
        model.put("imageList", rotationList);

        CompanyInfo companyInformation = companyInfoQueryService.selectCompanyInfoById(id);
        List<Menu> secondMenus = new ArrayList<Menu>();

        for (var menu : firstMenuList)
        {
            if (parentId.equals(menu.getId()))
            {
                secondMenus = menuQueryService.selectSonMenuByParentId(menu.getId(), menu.getLevel() + 1);
                break;
            }
        }
        model.put("secondMenus", secondMenus);

        model.put("parentId", parentId);

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

        BasicInfo basicInfo = basicInfoQueryService.selectBasicInfoByStatus();
        model.put("basicInfo", basicInfo);

        List<Rotation> rotationList = menuQueryService.selectImagesByCount(menuQueryService.selectImagesCount());
        model.put("imageList", rotationList);

        for (var firstMenu : firstMenuList)
        {
            if (parentId.equals(firstMenu.getId()))
            {
                List<Menu> secondMenus = menuQueryService.selectSonMenuByParentId(firstMenu.getId(), firstMenu.getLevel() + 1);
                List<Media> thirdMenus = mediaQueryService.selectMediasByParentId(id);

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

        BasicInfo basicInfo = basicInfoQueryService.selectBasicInfoByStatus();
        model.put("basicInfo", basicInfo);

        List<Rotation> rotationList = menuQueryService.selectImagesByCount(menuQueryService.selectImagesCount());
        model.put("imageList", rotationList);

        for (var firstMenu : firstMenuList)
        {
            if (parentId.equals(firstMenu.getId()))
            {
                List<Menu> secondMenus = menuQueryService.selectSonMenuByParentId(firstMenu.getId(), firstMenu.getLevel() + 1);
                List<Media> thirdMenus = mediaQueryService.selectMediasByParentId(secondId);

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

                                List<Video> videoList = videoQueryService.selectAllVideos();
                                model.put("videoList", videoList);

                                result = "company_video";
                            }
                            break;

                            //歌曲发行
                            case 23:
                            {
                                model.put("secondMenus", secondMenus);
                                model.put("secondId", secondId);
                                model.put("parentId", parentId);
                                model.put("thirdMenu", thirdMenu);

                                List<Music> musicList = musicQueryService.selectAllSongs();
                                model.put("musicList", musicList);

                                result = "container";
                            }
                            break;

                            default:
                            {
                                model.put("secondMenus", secondMenus);
                                model.put("secondId", secondId);
                                model.put("parentId", parentId);
                                model.put("thirdMenu", thirdMenu);
                                result = "article";
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

    @RequestMapping("/play")
    @ResponseBody
    @CrossOrigin
    public Music playSongs(Integer id)
    {
        Music music = musicQueryService.selectSongsById(id);

        return music;
    }

//    @RequestMapping("/readLrc")
//    @ResponseBody
//    public List<String> readLrc(String fileUrl) throws IOException
//    {
//        List<String> lrcList = new ArrayList<>();
//        lrcList = FileUtils.readLines(new File(fileUrl), "utf-8");
//
//        return lrcList;
//    }

    @RequestMapping("/playVideo")
    @ResponseBody
    public Video playVideos(Integer id)
    {
        Video video = videoQueryService.selectVideoById(id);

        return video;
    }
}
