


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class Dashboard implements Serializable
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

