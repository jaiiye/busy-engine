


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class SiteItem implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SITE_ITEM_ID = "SiteItemId";
        public static final String PROP_SITE_ID = "SiteId";
        public static final String PROP_ITEM_ID = "ItemId";
        

        private Integer siteItemId;
        private Integer siteId;
        private Integer itemId;
        

        public SiteItem()
        {
            this.siteItemId = 0; 
       this.siteId = 0; 
       this.itemId = 0; 
        }
        
        public SiteItem(Integer SiteItemId, Integer SiteId, Integer ItemId)
        {
            this.siteItemId = SiteItemId;
       this.siteId = SiteId;
       this.itemId = ItemId;
        } 
        
             
        
            public Integer getSiteItemId()
            {
                return this.siteItemId;
            }
            
            public void setSiteItemId(Integer SiteItemId)
            {
                this.siteItemId = SiteItemId;
            }
        
            public Integer getSiteId()
            {
                return this.siteId;
            }
            
            public void setSiteId(Integer SiteId)
            {
                this.siteId = SiteId;
            }
        
            public Integer getItemId()
            {
                return this.itemId;
            }
            
            public void setItemId(Integer ItemId)
            {
                this.itemId = ItemId;
            }
           
            
    }

