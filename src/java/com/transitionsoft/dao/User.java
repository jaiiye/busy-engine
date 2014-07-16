package com.transitionsoft.dao;

import com.busy.entity.Address;
import java.util.ArrayList;

public class User {

    private Integer userId;
    private String brandId;
    private String userName;
    private String userPassword;
    private String userSecurityQuestion;
    private String userSecurityAnswer;
    private String userImgUrl; 
    private String userEmail;
    private String userConfirmation;
    private String userWebUrl; 
    private ArrayList<UserAction> userActions;
    private String userRoles;    
    private Contact userContact;
    private Address userAddress;
    private ArrayList<UserService> userServices;
    
    public User() {
        this.userId = 0;
        this.userName = "";
        this.userImgUrl = "";
        userActions = new ArrayList<UserAction>();
        userServices = new ArrayList<UserService>();
        userRoles = "";
    }
        
    public User(Integer userId, String userName, String userImgUrl) {
        this.userId = userId;
        this.userName = userName;
        this.userImgUrl = userImgUrl;
        userActions = new ArrayList<UserAction>();        
        userServices = new ArrayList<UserService>();
        userRoles = "";
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserSecurityQuestion() {
        return userSecurityQuestion;
    }

    public void setUserSecurityQuestion(String userSecurityQuestion) {
        this.userSecurityQuestion = userSecurityQuestion;
    }

    public String getUserSecurityAnswer() {
        return userSecurityAnswer;
    }

    public void setUserSecurityAnswer(String userSecurityAnswer) {
        this.userSecurityAnswer = userSecurityAnswer;
    }

    public String getUserImgUrl() {
        return userImgUrl;
    }

    public void setUserImgUrl(String userImgUrl) {
        this.userImgUrl = userImgUrl;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserConfirmation() {
        return userConfirmation;
    }

    public void setUserConfirmation(String userConfirmation) {
        this.userConfirmation = userConfirmation;
    }

    public ArrayList<UserAction> getUserActions() {
        return userActions;
    }

    public void setUserActions(ArrayList<UserAction> userActions) {
        this.userActions = userActions;
    }  

    public String getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(String userRoles) {
        this.userRoles = userRoles;
    }

    public String getUserWebUrl() {
        return userWebUrl;
    }

    public void setUserWebUrl(String userWebUrl) {
        this.userWebUrl = userWebUrl;
    }

    public Contact getUserContact() {
        return userContact;
    }

    public void setUserContact(Contact userContact) {
        this.userContact = userContact;
    }

    public Address getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(Address userAddress) {
        this.userAddress = userAddress;
    }        

    public ArrayList<UserService> getUserServices() {
        return userServices;
    }

    public void setUserServices(ArrayList<UserService> userServices) {
        this.userServices = userServices;
    }        
}
