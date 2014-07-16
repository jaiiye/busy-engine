


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class UserGroup implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_USER_GROUP_ID = "UserGroupId";
        public static final String PROP_GROUP_NAME = "GroupName";
        public static final String PROP_SITE_ID = "SiteId";
        public static final String PROP_DISCOUNT_ID = "DiscountId";
        

        private Integer userGroupId;
        private String groupName;
        private Integer siteId;
        private Integer discountId;
        

        public UserGroup()
        {
            this.userGroupId = 0; 
       this.groupName = ""; 
       this.siteId = 0; 
       this.discountId = 0; 
        }
        
        public UserGroup(Integer UserGroupId, String GroupName, Integer SiteId, Integer DiscountId)
        {
            this.userGroupId = UserGroupId;
       this.groupName = GroupName;
       this.siteId = SiteId;
       this.discountId = DiscountId;
        } 
        
             
        
            public Integer getUserGroupId()
            {
                return this.userGroupId;
            }
            
            public void setUserGroupId(Integer UserGroupId)
            {
                this.userGroupId = UserGroupId;
            }
        
            public String getGroupName()
            {
                return this.groupName;
            }
            
            public void setGroupName(String GroupName)
            {
                this.groupName = GroupName;
            }
        
            public Integer getSiteId()
            {
                return this.siteId;
            }
            
            public void setSiteId(Integer SiteId)
            {
                this.siteId = SiteId;
            }
        
            public Integer getDiscountId()
            {
                return this.discountId;
            }
            
            public void setDiscountId(Integer DiscountId)
            {
                this.discountId = DiscountId;
            }
           
            
    }

