package com.transitionsoft.dao;

public class SiteAttribute
{
    private String attributeId;
    private String attributeKey;
    private String attributeValue;
    private String attributeType;

    public SiteAttribute(String attributeId, String attributeKey, String attributeValue, String attributeType) {
        this.attributeId = attributeId;
        this.attributeKey = attributeKey;
        this.attributeValue = attributeValue;
        this.attributeType = attributeType;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeKey() {
        return attributeKey;
    }

    public void setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    

}
