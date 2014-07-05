package com.transitionsoft.dao;

public class Blog
{
    private String blogId;
    private String blogName;
    private String blogType;

    public Blog(String blogId, String blogName, String blogType)
    {
        this.blogId = blogId;
        this.blogName = blogName;
        this.blogType = blogType;
    }

    public String getBlogId()
    {
        return blogId;
    }

    public void setBlogId(String blogId)
    {
        this.blogId = blogId;
    }

    public String getBlogName()
    {
        return blogName;
    }

    public void setBlogName(String blogName)
    {
        this.blogName = blogName;
    }
    public String getBlogType()
    {
        return blogType;
    }

    public void setBlogType(String blogType)
    {
        this.blogType = blogType;
    }
    
    
    
}
