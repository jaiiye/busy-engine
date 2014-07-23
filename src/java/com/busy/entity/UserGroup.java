











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
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
        private Site site;        
        private Integer discountId;
        private Discount discount;        
                 
        ArrayList<User> userList; 
        
        

        public UserGroup()
        {
            this.userGroupId = 0; 
       this.groupName = ""; 
       this.siteId = 0; 
       this.discountId = 0; 
        
       userList = null; 
        
       }
        
        public UserGroup(Integer UserGroupId, String GroupName, Integer SiteId, Integer DiscountId)
        {
            this.userGroupId = UserGroupId;
       this.groupName = GroupName;
       this.siteId = SiteId;
       this.discountId = DiscountId;
              
       userList = null; 
        
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
            
            
                   
            public Site getSite()
                {
                    return this.site;
                }

                public void setSite(Site site)
                {
                    this.site = site;
                }
                   
            
        
            public Integer getDiscountId()
            {
                return this.discountId;
            }
            
            public void setDiscountId(Integer DiscountId)
            {
                this.discountId = DiscountId;
            }
            
            
                   
            public Discount getDiscount()
                {
                    return this.discount;
                }

                public void setDiscount(Discount discount)
                {
                    this.discount = discount;
                }
                   
            
         
        
        
            public ArrayList<User> getUserList()
            {
                return this.userList;
            }
            
            public void setUserList(ArrayList<User> userList)
            {
                this.userList = userList;
            }
        
            
    }

