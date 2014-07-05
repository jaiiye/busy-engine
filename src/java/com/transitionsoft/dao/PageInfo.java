package com.transitionsoft.dao;

public class PageInfo {
    private String pageId;
    private String pageName;
    private String pageContent;
    private String pageSeoTitle;
    private String pageSeoDescription;
    private String pageSeoKeywords;
    private String pageFormId;
    private String pageSliderId;

    public PageInfo(String pageName, String pageContent, String pageSeoTitle, String pageSeoDescription, String pageSeoKeywords, String pageSliderId) {
        this.pageName = pageName;
        this.pageContent = pageContent;
        this.pageSeoTitle = pageSeoTitle;
        this.pageSeoDescription = pageSeoDescription;
        this.pageSeoKeywords = pageSeoKeywords;
        this.pageSliderId = pageSliderId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    public String getPageSeoTitle() {
        return pageSeoTitle;
    }

    public void setPageSeoTitle(String pageSeoTitle) {
        this.pageSeoTitle = pageSeoTitle;
    }

    public String getPageSeoDescription() {
        return pageSeoDescription;
    }

    public void setPageSeoDescription(String pageSeoDescription) {
        this.pageSeoDescription = pageSeoDescription;
    }

    public String getPageSeoKeywords() {
        return pageSeoKeywords;
    }

    public void setPageSeoKeywords(String pageSeoKeywords) {
        this.pageSeoKeywords = pageSeoKeywords;
    }

    public String getPageFormId() {
        return pageFormId;
    }

    public void setPageFormId(String pageFormId) {
        this.pageFormId = pageFormId;
    }

    public String getPageSliderId()
    {
        return pageSliderId;
    }

    public void setPageSliderId(String pageSliderId)
    {
        this.pageSliderId = pageSliderId;
    }
    
}
