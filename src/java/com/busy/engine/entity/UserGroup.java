






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class UserGroup extends AbstractEntity implements EntityItem<Integer>
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
       
       @Override
       public Integer getId()
       {
            
            return userGroupId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("userGroupId", userGroupId == null ? 0 : userGroupId);
                
            builder.add("groupName", groupName == null ? "" : groupName);
                
            builder.add("siteId", siteId == null ? 0 : siteId);
                
            builder.add("discountId", discountId == null ? 0 : discountId);
        
        
    
     
     if(site != null) site.addJson(builder);
        
     if(discount != null) discount.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(UserGroup.PROP_USER_GROUP_ID) || column.equals(UserGroup.PROP_GROUP_NAME) || column.equals(UserGroup.PROP_SITE_ID) || column.equals(UserGroup.PROP_DISCOUNT_ID) )
            {
                return column;
            }
            else
            {
                throw new SQLException("Invalid column name: " + column);
            }
        }
                
        public static void checkColumnSize(String column, int size) throws Exception
        {
            if (column.length() > size)
            {            
                throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
            }
        }
                
        public static boolean isColumnNumeric(String column)
        {
            if (column.equals(UserGroup.PROP_USER_GROUP_ID) || column.equals(UserGroup.PROP_SITE_ID) || column.equals(UserGroup.PROP_DISCOUNT_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static UserGroup process(ResultSet rs) throws SQLException
        {           
            rs.first();
            return new UserGroup(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
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

