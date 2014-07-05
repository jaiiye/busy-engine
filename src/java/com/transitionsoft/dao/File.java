package com.transitionsoft.dao;

public class File {
    private String fileId;
    private String fileName;
    private String fileDescription;
    private String fileLabel;    
    private Folder parentFolder;    

    public File(String fileId, String fileName, String fileDescription, String fileLabel) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileDescription = fileDescription;
        this.fileLabel = fileLabel;
        parentFolder = null;
    }
     
    public File(String fileId, String fileName, String fileDescription, String fileLabel, Folder fileFolder) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileDescription = fileDescription;
        this.fileLabel = fileLabel;
        parentFolder = fileFolder;
    }
    
    
    public File(String fileId, String fileName) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileDescription = "-";
        parentFolder = null;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public Folder getParentFolder()
    {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder)
    {
        this.parentFolder = parentFolder;
    }

    public String getFileLabel()
    {
        return fileLabel;
    }

    public void setFileLabel(String fileLabel)
    {
        this.fileLabel = fileLabel;
    } 
  
}
