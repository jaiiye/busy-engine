











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
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
        public static final String PROP_ITEM_STATUS = "ItemStatus";
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
                
        private Integer itemStatus;
                
        private String locale;
                
        private Integer itemTypeId;
        private ItemType itemType;        
        private Integer itemBrandId;
        private ItemBrand itemBrand;        
        private Integer metaTagId;
        private MetaTag metaTag;        
        private Integer templateId;
        private Template template;        
        private Integer vendorId;
        private Vendor vendor;        
                 
        ArrayList<ItemAttribute> itemAttributeList; 
        ArrayList<ItemCategory> itemCategoryList; 
        ArrayList<ItemDiscount> itemDiscountList; 
        ArrayList<ItemFile> itemFileList; 
        ArrayList<ItemImage> itemImageList; 
        ArrayList<ItemLocation> itemLocationList; 
        ArrayList<ItemReview> itemReviewList; 
        ArrayList<OptionAvailability> optionAvailabilityList; 
        ArrayList<OrderItem> orderItemList; 
        ArrayList<SiteItem> siteItemList; 
        
        

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
       this.itemStatus = 0; 
       this.locale = ""; 
       this.itemTypeId = 0; 
       this.itemBrandId = 0; 
       this.metaTagId = 0; 
       this.templateId = 0; 
       this.vendorId = 0; 
        
       itemAttributeList = null; 
        itemCategoryList = null; 
        itemDiscountList = null; 
        itemFileList = null; 
        itemImageList = null; 
        itemLocationList = null; 
        itemReviewList = null; 
        optionAvailabilityList = null; 
        orderItemList = null; 
        siteItemList = null; 
        
       }
        
        public Item(Integer ItemId, String ItemName, String Description, Double ListPrice, Double Price, String ShortDescription, Integer Adjustment, String Sku, Integer RatingSum, Integer VoteCount, Integer Rank, Integer ItemStatus, String Locale, Integer ItemTypeId, Integer ItemBrandId, Integer MetaTagId, Integer TemplateId, Integer VendorId)
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
       this.itemStatus = ItemStatus;
       this.locale = Locale;
       this.itemTypeId = ItemTypeId;
       this.itemBrandId = ItemBrandId;
       this.metaTagId = MetaTagId;
       this.templateId = TemplateId;
       this.vendorId = VendorId;
              
       itemAttributeList = null; 
        itemCategoryList = null; 
        itemDiscountList = null; 
        itemFileList = null; 
        itemImageList = null; 
        itemLocationList = null; 
        itemReviewList = null; 
        optionAvailabilityList = null; 
        orderItemList = null; 
        siteItemList = null; 
        
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
            
            
        
            public Integer getItemStatus()
            {
                return this.itemStatus;
            }
            
            public void setItemStatus(Integer ItemStatus)
            {
                this.itemStatus = ItemStatus;
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
            
            
                   
            public ItemType getItemType()
                {
                    return this.itemType;
                }

                public void setItemType(ItemType itemType)
                {
                    this.itemType = itemType;
                }
                   
            
        
            public Integer getItemBrandId()
            {
                return this.itemBrandId;
            }
            
            public void setItemBrandId(Integer ItemBrandId)
            {
                this.itemBrandId = ItemBrandId;
            }
            
            
                   
            public ItemBrand getItemBrand()
                {
                    return this.itemBrand;
                }

                public void setItemBrand(ItemBrand itemBrand)
                {
                    this.itemBrand = itemBrand;
                }
                   
            
        
            public Integer getMetaTagId()
            {
                return this.metaTagId;
            }
            
            public void setMetaTagId(Integer MetaTagId)
            {
                this.metaTagId = MetaTagId;
            }
            
            
                   
            public MetaTag getMetaTag()
                {
                    return this.metaTag;
                }

                public void setMetaTag(MetaTag metaTag)
                {
                    this.metaTag = metaTag;
                }
                   
            
        
            public Integer getTemplateId()
            {
                return this.templateId;
            }
            
            public void setTemplateId(Integer TemplateId)
            {
                this.templateId = TemplateId;
            }
            
            
                   
            public Template getTemplate()
                {
                    return this.template;
                }

                public void setTemplate(Template template)
                {
                    this.template = template;
                }
                   
            
        
            public Integer getVendorId()
            {
                return this.vendorId;
            }
            
            public void setVendorId(Integer VendorId)
            {
                this.vendorId = VendorId;
            }
            
            
                   
            public Vendor getVendor()
                {
                    return this.vendor;
                }

                public void setVendor(Vendor vendor)
                {
                    this.vendor = vendor;
                }
                   
            
         
        
        
            public ArrayList<ItemAttribute> getItemAttributeList()
            {
                return this.itemAttributeList;
            }
            
            public void setItemAttributeList(ArrayList<ItemAttribute> itemAttributeList)
            {
                this.itemAttributeList = itemAttributeList;
            }
        
            public ArrayList<ItemCategory> getItemCategoryList()
            {
                return this.itemCategoryList;
            }
            
            public void setItemCategoryList(ArrayList<ItemCategory> itemCategoryList)
            {
                this.itemCategoryList = itemCategoryList;
            }
        
            public ArrayList<ItemDiscount> getItemDiscountList()
            {
                return this.itemDiscountList;
            }
            
            public void setItemDiscountList(ArrayList<ItemDiscount> itemDiscountList)
            {
                this.itemDiscountList = itemDiscountList;
            }
        
            public ArrayList<ItemFile> getItemFileList()
            {
                return this.itemFileList;
            }
            
            public void setItemFileList(ArrayList<ItemFile> itemFileList)
            {
                this.itemFileList = itemFileList;
            }
        
            public ArrayList<ItemImage> getItemImageList()
            {
                return this.itemImageList;
            }
            
            public void setItemImageList(ArrayList<ItemImage> itemImageList)
            {
                this.itemImageList = itemImageList;
            }
        
            public ArrayList<ItemLocation> getItemLocationList()
            {
                return this.itemLocationList;
            }
            
            public void setItemLocationList(ArrayList<ItemLocation> itemLocationList)
            {
                this.itemLocationList = itemLocationList;
            }
        
            public ArrayList<ItemReview> getItemReviewList()
            {
                return this.itemReviewList;
            }
            
            public void setItemReviewList(ArrayList<ItemReview> itemReviewList)
            {
                this.itemReviewList = itemReviewList;
            }
        
            public ArrayList<OptionAvailability> getOptionAvailabilityList()
            {
                return this.optionAvailabilityList;
            }
            
            public void setOptionAvailabilityList(ArrayList<OptionAvailability> optionAvailabilityList)
            {
                this.optionAvailabilityList = optionAvailabilityList;
            }
        
            public ArrayList<OrderItem> getOrderItemList()
            {
                return this.orderItemList;
            }
            
            public void setOrderItemList(ArrayList<OrderItem> orderItemList)
            {
                this.orderItemList = orderItemList;
            }
        
            public ArrayList<SiteItem> getSiteItemList()
            {
                return this.siteItemList;
            }
            
            public void setSiteItemList(ArrayList<SiteItem> siteItemList)
            {
                this.siteItemList = siteItemList;
            }
        
            
    }

