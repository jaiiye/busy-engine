package com.transitionsoft.dao;

public class SliderItem
{
    private int itemId;
    private int sliderId;
    private String itemTitle;
    private String itemDescription;
    private String itemUrl;
    private String itemImageName;
    private String itemImageAlt;
    private int itemRank;

    public SliderItem(int itemId, int sliderId, String itemTitle, String itemDescription, String itemUrl, String itemImageName, String itemImageAlt, int itemRank)
    {
        this.itemId = itemId;
        this.sliderId = sliderId;
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemUrl = itemUrl;
        this.itemImageName = itemImageName;
        this.itemImageAlt = itemImageAlt;
        this.itemRank = itemRank;
    }

    public int getItemId()
    {
        return itemId;
    }

    public void setItemId(int itemId)
    {
        this.itemId = itemId;
    }

    public int getSliderId()
    {
        return sliderId;
    }

    public void setSliderId(int sliderId)
    {
        this.sliderId = sliderId;
    }

    public String getItemTitle()
    {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle)
    {
        this.itemTitle = itemTitle;
    }

    public String getItemDescription()
    {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription)
    {
        this.itemDescription = itemDescription;
    }

    public String getItemUrl()
    {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl)
    {
        this.itemUrl = itemUrl;
    }

    public String getItemImageName()
    {
        return itemImageName;
    }

    public void setItemImageName(String itemImageName)
    {
        this.itemImageName = itemImageName;
    }

    public String getItemImageAlt()
    {
        return itemImageAlt;
    }

    public void setItemImageAlt(String itemImageAlt)
    {
        this.itemImageAlt = itemImageAlt;
    }

    public int getItemRank()
    {
        return itemRank;
    }

    public void setItemRank(int itemRank)
    {
        this.itemRank = itemRank;
    }        
}
