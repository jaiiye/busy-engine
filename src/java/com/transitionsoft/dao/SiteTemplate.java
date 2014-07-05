package com.transitionsoft.dao;

import java.util.ArrayList;

public class SiteTemplate {

    public int templateId;
    public String templateName;
    public String templateType;
    public String templateStatus;
    public ArrayList<SiteTemplateUrl> templateResources;

    public SiteTemplate(int templateId, String templateName, String templateType, String templateStatus) {
        this.templateId = templateId;
        this.templateName = templateName;
        this.templateType = templateType;
        this.templateStatus = templateStatus;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getTemplateStatus() {
        return templateStatus;
    }

    public void setTemplateStatus(String templateStatus) {
        this.templateStatus = templateStatus;
    }

    public ArrayList<SiteTemplateUrl> getTemplateResources() {
        return templateResources;
    }

    public void setTemplateResources(ArrayList<SiteTemplateUrl> templateResources) {
        this.templateResources = templateResources;
    }
        
}
