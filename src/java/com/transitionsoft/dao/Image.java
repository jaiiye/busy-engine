package com.transitionsoft.dao;

public class Image
{
    private int imageId;
    private int imageTypeId;
    private int itemId;
    private String imageFileName;
    private String imageDescription;
    private String imageLinkUrl;
    private int imageRank;

    public Image(int imageId, int imageTypeId, int itemId, String imageFileName, String imageDescription, String imageLinkUrl, int imageRank)
    {
        this.imageId = imageId;
        this.itemId = itemId;
        this.imageTypeId = imageTypeId;
        this.imageFileName = imageFileName;
        this.imageDescription = imageDescription;
        this.imageLinkUrl = imageLinkUrl;
        this.imageRank = imageRank;
    }

    public int getImageId()
    {
        return imageId;
    }

    public void setImageId(int imageId)
    {
        this.imageId = imageId;
    }

    public int getItemId()
    {
        return itemId;
    }

    public void setItemId(int itemId)
    {
        this.itemId = itemId;
    }

    public int getImageTypeId()
    {
        return imageTypeId;
    }

    public void setImageTypeId(int imageTypeId)
    {
        this.imageTypeId = imageTypeId;
    }

    public String getImageFileName()
    {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName)
    {
        this.imageFileName = imageFileName;
    }

    public String getImageDescription()
    {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription)
    {
        this.imageDescription = imageDescription;
    }

    public String getImageLinkUrl()
    {
        return imageLinkUrl;
    }

    public void setImageLinkUrl(String imageLinkUrl)
    {
        this.imageLinkUrl = imageLinkUrl;
    }

    public int getImageRank()
    {
        return imageRank;
    }

    public void setImageRank(int imageRank)
    {
        this.imageRank = imageRank;
    }
}
