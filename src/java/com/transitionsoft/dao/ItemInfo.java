package com.transitionsoft.dao;

import java.util.ArrayList;

public class ItemInfo
{
    private ArrayList<Category> itemCategories;
    private ArrayList<File> itemFiles;
    private ArrayList<Option> itemOptions;
    private ArrayList<String> itemImages;

    public ItemInfo()
    {
        this.itemCategories = null;
        this.itemFiles = null;
        this.itemOptions = null;
        this.itemImages = null;
    }

    public ArrayList<Category> getItemCategories()
    {
        return itemCategories;
    }

    public void setItemCategories(ArrayList<Category> itemCategories)
    {
        this.itemCategories = itemCategories;
    }

    public ArrayList<File> getItemFiles()
    {
        return itemFiles;
    }

    public void setItemFiles(ArrayList<File> itemFiles)
    {
        this.itemFiles = itemFiles;
    }

    public ArrayList<Option> getItemOptions()
    {
        return itemOptions;
    }

    public void setItemOptions(ArrayList<Option> itemOptions)
    {
        this.itemOptions = itemOptions;
    }

    public ArrayList<String> getItemImages()
    {
        return itemImages;
    }

    public void setItemImages(ArrayList<String> itemImages)
    {
        this.itemImages = itemImages;
    }
}
