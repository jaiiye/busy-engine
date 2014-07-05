package com.transitionsoft.dao;

public class SiteInfo {
    private String adminName;
    private String adminEmail;
    private String notificationEmail;
    private String siteUrl;
    private String siteTestingUrl;
    private int siteMode;
    private String testingEmail;
    private String testingEmailPassword;
    

    public SiteInfo(String adminName, String adminEmail, String notificationEmail, String siteUrl, String siteTestingUrl, int siteMode, String testingEmail, String testingEmailPassword) {
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.notificationEmail = notificationEmail;
        this.siteUrl = siteUrl;
        this.siteTestingUrl = siteTestingUrl;
        this.siteMode = siteMode;
        this.testingEmail = testingEmail;
        this.testingEmailPassword = testingEmailPassword;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getNotificationEmail() {
        return notificationEmail;
    }

    public void setNotificationEmail(String notificationEmail) {
        this.notificationEmail = notificationEmail;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getSiteTestingUrl() {
        return siteTestingUrl;
    }

    public void setSiteTestingUrl(String siteTestingUrl) {
        this.siteTestingUrl = siteTestingUrl;
    }

    public int getSiteMode() {
        return siteMode;
    }

    public void setSiteMode(int siteMode) {
        this.siteMode = siteMode;
    }

    public String getTestingEmail() {
        return testingEmail;
    }

    public void setTestingEmail(String testingEmail) {
        this.testingEmail = testingEmail;
    }

    public String getTestingEmailPassword() {
        return testingEmailPassword;
    }

    public void setTestingEmailPassword(String testingEmailPassword) {
        this.testingEmailPassword = testingEmailPassword;
    }
    
    
    
}
