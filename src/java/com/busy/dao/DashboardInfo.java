package com.busy.dao;

import java.util.ArrayList;

public class DashboardInfo
{

    private int userCount;
    private int blogPostCount;
    private int itemCount;
    private int orderCount;
    private int fileCount;
    private int imageCount;
    private int blogCount;
    private int commentCount;
    private int pageCount;
    private int formCount;
    private int sliderCount;
    private int brandCount;
    private int categoryCount;
    private int optionCount;
    private ArrayList<UserAction> activities;

    public DashboardInfo()
    {
        userCount = 0;
        blogPostCount = 0;
        itemCount = 0;
        orderCount = 0;
        fileCount = 0;
        imageCount = 0;
        blogCount = 0;
        commentCount = 0;
        pageCount = 0;
        formCount = 0;
        sliderCount = 0;
        brandCount = 0;
        categoryCount = 0;
        optionCount = 0;
        activities = new ArrayList<UserAction>();
    }

    public int getUserCount()
    {
        return userCount;
    }

    public void setUserCount(int userCount)
    {
        this.userCount = userCount;
    }

    public int getBlogPostCount()
    {
        return blogPostCount;
    }

    public void setBlogPostCount(int blogPostCount)
    {
        this.blogPostCount = blogPostCount;
    }

    public int getItemCount()
    {
        return itemCount;
    }

    public void setItemCount(int itemCount)
    {
        this.itemCount = itemCount;
    }

    public int getOrderCount()
    {
        return orderCount;
    }

    public void setOrderCount(int orderCount)
    {
        this.orderCount = orderCount;
    }

    public int getFileCount()
    {
        return fileCount;
    }

    public void setFileCount(int fileCount)
    {
        this.fileCount = fileCount;
    }

    public int getImageCount()
    {
        return imageCount;
    }

    public void setImageCount(int imageCount)
    {
        this.imageCount = imageCount;
    }

    public int getBlogCount()
    {
        return blogCount;
    }

    public void setBlogCount(int blogCount)
    {
        this.blogCount = blogCount;
    }

    public int getCommentCount()
    {
        return commentCount;
    }

    public void setCommentCount(int commentCount)
    {
        this.commentCount = commentCount;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    public int getFormCount()
    {
        return formCount;
    }

    public void setFormCount(int formCount)
    {
        this.formCount = formCount;
    }

    public int getSliderCount()
    {
        return sliderCount;
    }

    public void setSliderCount(int sliderCount)
    {
        this.sliderCount = sliderCount;
    }

    public int getBrandCount()
    {
        return brandCount;
    }

    public void setBrandCount(int brandCount)
    {
        this.brandCount = brandCount;
    }

    public int getCategoryCount()
    {
        return categoryCount;
    }

    public void setCategoryCount(int categoryCount)
    {
        this.categoryCount = categoryCount;
    }

    public int getOptionCount()
    {
        return optionCount;
    }

    public void setOptionCount(int optionCount)
    {
        this.optionCount = optionCount;
    }

    public ArrayList<UserAction> getActivities()
    {
        return activities;
    }

    public void setActivities(ArrayList<UserAction> activities)
    {
        this.activities = activities;
    }

}
