


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class Item implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_ID = "ItemId";
        public static final String PROP_ITEM_NAME = "ItemName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_LIST_PRICE = "ListPrice";
        public static final String PROP_PRICE = "Price";
        public static final String PROP_SHORT_DESCRIPTION = "ShortDescription";
        public static final String PROP_ADJUSTMENT = "Adjustment";
        public static final String PROP_SKU = "Sku";
        public static final String PROP_RATING_SUM = "RatingSum";
        public static final String PROP_VOTE_COUNT = "VoteCount";
        public static final String PROP_RANK = "Rank";
        public static final String PROP_STATUS = "Status";
        public static final String PROP_LOCALE = "Locale";
        public static final String PROP_ITEM_TYPE_ID = "ItemTypeId";
        public static final String PROP_ITEM_BRAND_ID = "ItemBrandId";
        public static final String PROP_META_TAG_ID = "MetaTagId";
        public static final String PROP_TEMPLATE_ID = "TemplateId";
        public static final String PROP_VENDOR_ID = "VendorId";
        

        private Integer itemId;
        private String itemName;
        private String description;
        private Double listPrice;
        private Double price;
        private String shortDescription;
        private Integer adjustment;
        private String sku;
        private Integer ratingSum;
        private Integer voteCount;
        private Integer rank;
        private Integer status;
        private String locale;
        private Integer itemTypeId;
        private Integer itemBrandId;
        private Integer metaTagId;
        private Integer templateId;
        private Integer vendorId;
        

        public Item()
        {
            this.itemId = 0; 
       this.itemName = ""; 
       this.description = ""; 
       this.listPrice = 0.0; 
       this.price = 0.0; 
       this.shortDescription = ""; 
       this.adjustment = 0; 
       this.sku = ""; 
       this.ratingSum = 0; 
       this.voteCount = 0; 
       this.rank = 0; 
       this.status = 0; 
       this.locale = ""; 
       this.itemTypeId = 0; 
       this.itemBrandId = 0; 
       this.metaTagId = 0; 
       this.templateId = 0; 
       this.vendorId = 0; 
        }
        
        public Item(Integer ItemId, String ItemName, String Description, Double ListPrice, Double Price, String ShortDescription, Integer Adjustment, String Sku, Integer RatingSum, Integer VoteCount, Integer Rank, Integer Status, String Locale, Integer ItemTypeId, Integer ItemBrandId, Integer MetaTagId, Integer TemplateId, Integer VendorId)
        {
            this.itemId = ItemId;
       this.itemName = ItemName;
       this.description = Description;
       this.listPrice = ListPrice;
       this.price = Price;
       this.shortDescription = ShortDescription;
       this.adjustment = Adjustment;
       this.sku = Sku;
       this.ratingSum = RatingSum;
       this.voteCount = VoteCount;
       this.rank = Rank;
       this.status = Status;
       this.locale = Locale;
       this.itemTypeId = ItemTypeId;
       this.itemBrandId = ItemBrandId;
       this.metaTagId = MetaTagId;
       this.templateId = TemplateId;
       this.vendorId = VendorId;
        } 
        
             
        
            public Integer getItemId()
            {
                return this.itemId;
            }
            
            public void setItemId(Integer ItemId)
            {
                this.itemId = ItemId;
            }
        
            public String getItemName()
            {
                return this.itemName;
            }
            
            public void setItemName(String ItemName)
            {
                this.itemName = ItemName;
            }
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
        
            public Double getListPrice()
            {
                return this.listPrice;
            }
            
            public void setListPrice(Double ListPrice)
            {
                this.listPrice = ListPrice;
            }
        
            public Double getPrice()
            {
                return this.price;
            }
            
            public void setPrice(Double Price)
            {
                this.price = Price;
            }
        
            public String getShortDescription()
            {
                return this.shortDescription;
            }
            
            public void setShortDescription(String ShortDescription)
            {
                this.shortDescription = ShortDescription;
            }
        
            public Integer getAdjustment()
            {
                return this.adjustment;
            }
            
            public void setAdjustment(Integer Adjustment)
            {
                this.adjustment = Adjustment;
            }
        
            public String getSku()
            {
                return this.sku;
            }
            
            public void setSku(String Sku)
            {
                this.sku = Sku;
            }
        
            public Integer getRatingSum()
            {
                return this.ratingSum;
            }
            
            public void setRatingSum(Integer RatingSum)
            {
                this.ratingSum = RatingSum;
            }
        
            public Integer getVoteCount()
            {
                return this.voteCount;
            }
            
            public void setVoteCount(Integer VoteCount)
            {
                this.voteCount = VoteCount;
            }
        
            public Integer getRank()
            {
                return this.rank;
            }
            
            public void setRank(Integer Rank)
            {
                this.rank = Rank;
            }
        
            public Integer getStatus()
            {
                return this.status;
            }
            
            public void setStatus(Integer Status)
            {
                this.status = Status;
            }
        
            public String getLocale()
            {
                return this.locale;
            }
            
            public void setLocale(String Locale)
            {
                this.locale = Locale;
            }
        
            public Integer getItemTypeId()
            {
                return this.itemTypeId;
            }
            
            public void setItemTypeId(Integer ItemTypeId)
            {
                this.itemTypeId = ItemTypeId;
            }
        
            public Integer getItemBrandId()
            {
                return this.itemBrandId;
            }
            
            public void setItemBrandId(Integer ItemBrandId)
            {
                this.itemBrandId = ItemBrandId;
            }
        
            public Integer getMetaTagId()
            {
                return this.metaTagId;
            }
            
            public void setMetaTagId(Integer MetaTagId)
            {
                this.metaTagId = MetaTagId;
            }
        
            public Integer getTemplateId()
            {
                return this.templateId;
            }
            
            public void setTemplateId(Integer TemplateId)
            {
                this.templateId = TemplateId;
            }
        
            public Integer getVendorId()
            {
                return this.vendorId;
            }
            
            public void setVendorId(Integer VendorId)
            {
                this.vendorId = VendorId;
            }
           
            
    }

