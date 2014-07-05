package com.transitionsoft.dao;

public class SiteTemplateResource {

    private String resourceUrl;
    private String resourceType;

    public SiteTemplateResource(String resourceUrl, String resourceType) {
        this.resourceUrl = resourceUrl;
        this.resourceType = resourceType;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    
    
}
