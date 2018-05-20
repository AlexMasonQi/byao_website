package com.byao.website.entity;

import java.io.Serializable;

public class CompanyInfo implements Serializable
{
    private Integer id;

    private String content;

    private String menuName;

    private Integer parentId;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getMenuName()
    {
        return menuName;
    }

    public void setMenuName(String menuName)
    {
        this.menuName = menuName;
    }

    public Integer getParentId()
    {
        return parentId;
    }

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }

    @Override
    public String toString()
    {
        return "CompanyInfo{" + "id=" + id + ", content='" + content + '\'' + ", menuName='" + menuName + '\'' + ", parentId=" + parentId + '}';
    }
}
