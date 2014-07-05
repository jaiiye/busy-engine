package com.transitionsoft.dao;

public class SiteTemplateUrl {

    public int Id;
    public String url;
    public String templateId;
    public String siteResourceTypeId;

    public SiteTemplateUrl(int Id, String url, String templateId, String siteResourceTypeId) {
        this.Id = Id;
        this.url = url;
        this.templateId = templateId;
        this.siteResourceTypeId = siteResourceTypeId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getSiteResourceTypeId() {
        return siteResourceTypeId;
    }

    public void setSiteResourceTypeId(String siteResourceTypeId) {
        this.siteResourceTypeId = siteResourceTypeId;
    }
    
    
}
