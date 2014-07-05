package com.transitionsoft.dao;

import java.util.ArrayList;

public class Folder
{
    private int folderId;
    private String folderName;
    private String folderDescription;
    private int folderRank;
    private ArrayList<File> files;

    public Folder(int folderId, String folderName, String folderDescription, int folderRank)
    {
        this.folderId = folderId;
        this.folderName = folderName;
        this.folderDescription = folderDescription;
        this.folderRank = folderRank;
    }

    public int getFolderId()
    {
        return folderId;
    }

    public void setFolderId(int folderId)
    {
        this.folderId = folderId;
    }

    public String getFolderName()
    {
        return folderName;
    }

    public void setFolderName(String folderName)
    {
        this.folderName = folderName;
    }

    public String getFolderDescription()
    {
        return folderDescription;
    }

    public void setFolderDescription(String folderDescription)
    {
        this.folderDescription = folderDescription;
    }

    public int getFolderRank()
    {
        return folderRank;
    }

    public void setFolderRank(int folderRank)
    {
        this.folderRank = folderRank;
    }

    public ArrayList<File> getFiles()
    {
        return files;
    }

    public void setFiles(ArrayList<File> files)
    {
        this.files = files;
    }
}
