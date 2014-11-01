






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Dashboard extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_DASHBOARD_ID = "DashboardId";
        public static final String PROP_USER_COUNT = "UserCount";
        public static final String PROP_BLOG_POST_COUNT = "BlogPostCount";
        public static final String PROP_ITEM_COUNT = "ItemCount";
        public static final String PROP_ORDER_COUNT = "OrderCount";
        public static final String PROP_SITE_FILE_COUNT = "SiteFileCount";
        public static final String PROP_IMAGE_COUNT = "ImageCount";
        public static final String PROP_BLOG_COUNT = "BlogCount";
        public static final String PROP_COMMENT_COUNT = "CommentCount";
        public static final String PROP_PAGE_COUNT = "PageCount";
        public static final String PROP_FORM_COUNT = "FormCount";
        public static final String PROP_SLIDER_COUNT = "SliderCount";
        public static final String PROP_ITEM_BRAND_COUNT = "ItemBrandCount";
        public static final String PROP_CATEGORY_COUNT = "CategoryCount";
        public static final String PROP_ITEM_OPTION_COUNT = "ItemOptionCount";
        

        private Integer dashboardId;
                
        private Integer userCount;
                
        private Integer blogPostCount;
                
        private Integer itemCount;
                
        private Integer orderCount;
                
        private Integer siteFileCount;
                
        private Integer imageCount;
                
        private Integer blogCount;
                
        private Integer commentCount;
                
        private Integer pageCount;
                
        private Integer formCount;
                
        private Integer sliderCount;
                
        private Integer itemBrandCount;
                
        private Integer categoryCount;
                
        private Integer itemOptionCount;
                
                 
        
        

        public Dashboard()
        {
            this.dashboardId = 0; 
       this.userCount = 0; 
       this.blogPostCount = 0; 
       this.itemCount = 0; 
       this.orderCount = 0; 
       this.siteFileCount = 0; 
       this.imageCount = 0; 
       this.blogCount = 0; 
       this.commentCount = 0; 
       this.pageCount = 0; 
       this.formCount = 0; 
       this.sliderCount = 0; 
       this.itemBrandCount = 0; 
       this.categoryCount = 0; 
       this.itemOptionCount = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return dashboardId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("dashboardId", dashboardId == null ? 0 : dashboardId);
                
            builder.add("userCount", userCount == null ? 0 : userCount);
                
            builder.add("blogPostCount", blogPostCount == null ? 0 : blogPostCount);
                
            builder.add("itemCount", itemCount == null ? 0 : itemCount);
                
            builder.add("orderCount", orderCount == null ? 0 : orderCount);
                
            builder.add("siteFileCount", siteFileCount == null ? 0 : siteFileCount);
                
            builder.add("imageCount", imageCount == null ? 0 : imageCount);
                
            builder.add("blogCount", blogCount == null ? 0 : blogCount);
                
            builder.add("commentCount", commentCount == null ? 0 : commentCount);
                
            builder.add("pageCount", pageCount == null ? 0 : pageCount);
                
            builder.add("formCount", formCount == null ? 0 : formCount);
                
            builder.add("sliderCount", sliderCount == null ? 0 : sliderCount);
                
            builder.add("itemBrandCount", itemBrandCount == null ? 0 : itemBrandCount);
                
            builder.add("categoryCount", categoryCount == null ? 0 : categoryCount);
                
            builder.add("itemOptionCount", itemOptionCount == null ? 0 : itemOptionCount);
        
        
    
     
     
     
     
     
     
     
     
     
     
     
     
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Dashboard.PROP_DASHBOARD_ID) || column.equals(Dashboard.PROP_USER_COUNT) || column.equals(Dashboard.PROP_BLOG_POST_COUNT) || column.equals(Dashboard.PROP_ITEM_COUNT) || column.equals(Dashboard.PROP_ORDER_COUNT) || column.equals(Dashboard.PROP_SITE_FILE_COUNT) || column.equals(Dashboard.PROP_IMAGE_COUNT) || column.equals(Dashboard.PROP_BLOG_COUNT) || column.equals(Dashboard.PROP_COMMENT_COUNT) || column.equals(Dashboard.PROP_PAGE_COUNT) || column.equals(Dashboard.PROP_FORM_COUNT) || column.equals(Dashboard.PROP_SLIDER_COUNT) || column.equals(Dashboard.PROP_ITEM_BRAND_COUNT) || column.equals(Dashboard.PROP_CATEGORY_COUNT) || column.equals(Dashboard.PROP_ITEM_OPTION_COUNT) )
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
            if (column.equals(Dashboard.PROP_DASHBOARD_ID) || column.equals(Dashboard.PROP_USER_COUNT) || column.equals(Dashboard.PROP_BLOG_POST_COUNT) || column.equals(Dashboard.PROP_ITEM_COUNT) || column.equals(Dashboard.PROP_ORDER_COUNT) || column.equals(Dashboard.PROP_SITE_FILE_COUNT) || column.equals(Dashboard.PROP_IMAGE_COUNT) || column.equals(Dashboard.PROP_BLOG_COUNT) || column.equals(Dashboard.PROP_COMMENT_COUNT) || column.equals(Dashboard.PROP_PAGE_COUNT) || column.equals(Dashboard.PROP_FORM_COUNT) || column.equals(Dashboard.PROP_SLIDER_COUNT) || column.equals(Dashboard.PROP_ITEM_BRAND_COUNT) || column.equals(Dashboard.PROP_CATEGORY_COUNT) || column.equals(Dashboard.PROP_ITEM_OPTION_COUNT) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Dashboard process(ResultSet rs) throws SQLException
        {           
            if(rs.getRow() == 0)
            {
                rs.first();
            }
            return new Dashboard(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15));
        }
              
       public Dashboard(Integer DashboardId, Integer UserCount, Integer BlogPostCount, Integer ItemCount, Integer OrderCount, Integer SiteFileCount, Integer ImageCount, Integer BlogCount, Integer CommentCount, Integer PageCount, Integer FormCount, Integer SliderCount, Integer ItemBrandCount, Integer CategoryCount, Integer ItemOptionCount)
       {
            this.dashboardId = DashboardId;
       this.userCount = UserCount;
       this.blogPostCount = BlogPostCount;
       this.itemCount = ItemCount;
       this.orderCount = OrderCount;
       this.siteFileCount = SiteFileCount;
       this.imageCount = ImageCount;
       this.blogCount = BlogCount;
       this.commentCount = CommentCount;
       this.pageCount = PageCount;
       this.formCount = FormCount;
       this.sliderCount = SliderCount;
       this.itemBrandCount = ItemBrandCount;
       this.categoryCount = CategoryCount;
       this.itemOptionCount = ItemOptionCount;
              
       
       } 
        
             
        
            public Integer getDashboardId()
            {
                return this.dashboardId;
            }
            
            public void setDashboardId(Integer DashboardId)
            {
                this.dashboardId = DashboardId;
            }
            
            
        
            public Integer getUserCount()
            {
                return this.userCount;
            }
            
            public void setUserCount(Integer UserCount)
            {
                this.userCount = UserCount;
            }
            
            
        
            public Integer getBlogPostCount()
            {
                return this.blogPostCount;
            }
            
            public void setBlogPostCount(Integer BlogPostCount)
            {
                this.blogPostCount = BlogPostCount;
            }
            
            
        
            public Integer getItemCount()
            {
                return this.itemCount;
            }
            
            public void setItemCount(Integer ItemCount)
            {
                this.itemCount = ItemCount;
            }
            
            
        
            public Integer getOrderCount()
            {
                return this.orderCount;
            }
            
            public void setOrderCount(Integer OrderCount)
            {
                this.orderCount = OrderCount;
            }
            
            
        
            public Integer getSiteFileCount()
            {
                return this.siteFileCount;
            }
            
            public void setSiteFileCount(Integer SiteFileCount)
            {
                this.siteFileCount = SiteFileCount;
            }
            
            
        
            public Integer getImageCount()
            {
                return this.imageCount;
            }
            
            public void setImageCount(Integer ImageCount)
            {
                this.imageCount = ImageCount;
            }
            
            
        
            public Integer getBlogCount()
            {
                return this.blogCount;
            }
            
            public void setBlogCount(Integer BlogCount)
            {
                this.blogCount = BlogCount;
            }
            
            
        
            public Integer getCommentCount()
            {
                return this.commentCount;
            }
            
            public void setCommentCount(Integer CommentCount)
            {
                this.commentCount = CommentCount;
            }
            
            
        
            public Integer getPageCount()
            {
                return this.pageCount;
            }
            
            public void setPageCount(Integer PageCount)
            {
                this.pageCount = PageCount;
            }
            
            
        
            public Integer getFormCount()
            {
                return this.formCount;
            }
            
            public void setFormCount(Integer FormCount)
            {
                this.formCount = FormCount;
            }
            
            
        
            public Integer getSliderCount()
            {
                return this.sliderCount;
            }
            
            public void setSliderCount(Integer SliderCount)
            {
                this.sliderCount = SliderCount;
            }
            
            
        
            public Integer getItemBrandCount()
            {
                return this.itemBrandCount;
            }
            
            public void setItemBrandCount(Integer ItemBrandCount)
            {
                this.itemBrandCount = ItemBrandCount;
            }
            
            
        
            public Integer getCategoryCount()
            {
                return this.categoryCount;
            }
            
            public void setCategoryCount(Integer CategoryCount)
            {
                this.categoryCount = CategoryCount;
            }
            
            
        
            public Integer getItemOptionCount()
            {
                return this.itemOptionCount;
            }
            
            public void setItemOptionCount(Integer ItemOptionCount)
            {
                this.itemOptionCount = ItemOptionCount;
            }
            
            
         
        
        
            
    }

