package com.transitionsoft.dao;

public class UserService {

    private int Id;
    private int serviceId;
    private int userId;
    private int blogId;    
    private String startDate;
    private String endDate;
    private String details;
    private String contractUrl;
    private String deliverableUrl;
    private String depositAmount;
    private Service serviceDetails;

    public UserService(int Id, int serviceId, int userId, int blogId, String startDate, String endDate, String details, String contractUrl, String deliverableUrl, String depositAmount) {
        this.Id = Id;
        this.serviceId = serviceId;
        this.userId = userId;
        this.blogId = blogId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.details = details;
        this.contractUrl = contractUrl;
        this.deliverableUrl = deliverableUrl;
        this.depositAmount = depositAmount;
        this.serviceDetails = null;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public String getDeliverableUrl() {
        return deliverableUrl;
    }

    public void setDeliverableUrl(String deliverableUrl) {
        this.deliverableUrl = deliverableUrl;
    }

    public String getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(String depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Service getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(Service serviceDetails) {
        this.serviceDetails = serviceDetails;
    }    
}
