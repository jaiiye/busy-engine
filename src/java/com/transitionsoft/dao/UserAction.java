
package com.transitionsoft.dao;

/**
 *
 * @author Sourena
 */
public class UserAction 
{
    private String userId;
    private String userActionDate;
    private String userActionType;
    private String userActionDetails;

    public UserAction(String userId, String userActionDate, String userActionType, String userActionDetails) {
        this.userId = userId;
        this.userActionDate = userActionDate;
        this.userActionType = userActionType;
        this.userActionDetails = userActionDetails;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserActionDate() {
        return userActionDate;
    }

    public void setUserActionDate(String userActionDate) {
        this.userActionDate = userActionDate;
    }

    public String getUserActionType() {
        return userActionType;
    }

    public void setUserActionType(String userActionType) {
        this.userActionType = userActionType;
    }

    public String getUserActionDetails() {
        return userActionDetails;
    }

    public void setUserActionDetails(String userActionDetails) {
        this.userActionDetails = userActionDetails;
    }
    
    
    
}
