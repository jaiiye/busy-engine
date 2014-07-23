package com.busy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Template implements Serializable
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_TEMPLATE_ID = "TemplateId";
    public static final String PROP_TEMPLATE_NAME = "TemplateName";
    public static final String PROP_MARKUP = "Markup";
    public static final String PROP_TEMPLATE_STATUS = "TemplateStatus";
    public static final String PROP_TEMPLATE_TYPE_ID = "TemplateTypeId";

    private Integer templateId;
    private String templateName;
    private String markup;
    private Integer templateStatus;
    private Integer templateTypeId;
    private TemplateType templateType;

    ArrayList<Item> itemList;
    ArrayList<Page> pageList;
    ArrayList<ResourceUrl> resourceUrlList;
    ArrayList<Site> siteList;
    ArrayList<Vendor> vendorList;

    public Template()
    {
        this.templateId = 0;
        this.templateName = "";
        this.markup = "";
        this.templateStatus = 0;
        this.templateTypeId = 0;

        itemList = null;
        pageList = null;
        resourceUrlList = null;
        siteList = null;
        vendorList = null;

    }

    public Template(Integer TemplateId, String TemplateName, String Markup, Integer TemplateStatus, Integer TemplateTypeId)
    {
        this.templateId = TemplateId;
        this.templateName = TemplateName;
        this.markup = Markup;
        this.templateStatus = TemplateStatus;
        this.templateTypeId = TemplateTypeId;

        itemList = null;
        pageList = null;
        resourceUrlList = null;
        siteList = null;
        vendorList = null;

    }

    public Integer getTemplateId()
    {
        return this.templateId;
    }

    public void setTemplateId(Integer TemplateId)
    {
        this.templateId = TemplateId;
    }

    public String getTemplateName()
    {
        return this.templateName;
    }

    public void setTemplateName(String TemplateName)
    {
        this.templateName = TemplateName;
    }

    public String getMarkup()
    {
        return this.markup;
    }

    public void setMarkup(String Markup)
    {
        this.markup = Markup;
    }

    public Integer getTemplateStatus()
    {
        return this.templateStatus;
    }

    public void setTemplateStatus(Integer TemplateStatus)
    {
        this.templateStatus = TemplateStatus;
    }

    public Integer getTemplateTypeId()
    {
        return this.templateTypeId;
    }

    public void setTemplateTypeId(Integer TemplateTypeId)
    {
        this.templateTypeId = TemplateTypeId;
    }

    public TemplateType getTemplateType()
    {
        return this.templateType;
    }

    public void setTemplateType(TemplateType templateType)
    {
        this.templateType = templateType;
    }

    public ArrayList<Item> getItemList()
    {
        return this.itemList;
    }

    public void setItemList(ArrayList<Item> itemList)
    {
        this.itemList = itemList;
    }

    public ArrayList<Page> getPageList()
    {
        return this.pageList;
    }

    public void setPageList(ArrayList<Page> pageList)
    {
        this.pageList = pageList;
    }

    public ArrayList<ResourceUrl> getResourceUrlList()
    {
        return this.resourceUrlList;
    }

    public void setResourceUrlList(ArrayList<ResourceUrl> resourceUrlList)
    {
        this.resourceUrlList = resourceUrlList;
    }

    public ArrayList<Site> getSiteList()
    {
        return this.siteList;
    }

    public void setSiteList(ArrayList<Site> siteList)
    {
        this.siteList = siteList;
    }

    public ArrayList<Vendor> getVendorList()
    {
        return this.vendorList;
    }

    public void setVendorList(ArrayList<Vendor> vendorList)
    {
        this.vendorList = vendorList;
    }

}
