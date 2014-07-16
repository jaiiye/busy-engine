


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class ItemImage implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_IMAGE_ID = "ItemImageId";
        public static final String PROP_IMAGE_NAME = "ImageName";
        public static final String PROP_THUMBNAIL_NAME = "ThumbnailName";
        public static final String PROP_ALTERNATE_TEXT = "AlternateText";
        public static final String PROP_RANK = "Rank";
        public static final String PROP_ITEM_ID = "ItemId";
        

        private Integer itemImageId;
        private String imageName;
        private String thumbnailName;
        private String alternateText;
        private Integer rank;
        private Integer itemId;
        

        public ItemImage()
        {
            this.itemImageId = 0; 
       this.imageName = ""; 
       this.thumbnailName = ""; 
       this.alternateText = ""; 
       this.rank = 0; 
       this.itemId = 0; 
        }
        
        public ItemImage(Integer ItemImageId, String ImageName, String ThumbnailName, String AlternateText, Integer Rank, Integer ItemId)
        {
            this.itemImageId = ItemImageId;
       this.imageName = ImageName;
       this.thumbnailName = ThumbnailName;
       this.alternateText = AlternateText;
       this.rank = Rank;
       this.itemId = ItemId;
        } 
        
             
        
            public Integer getItemImageId()
            {
                return this.itemImageId;
            }
            
            public void setItemImageId(Integer ItemImageId)
            {
                this.itemImageId = ItemImageId;
            }
        
            public String getImageName()
            {
                return this.imageName;
            }
            
            public void setImageName(String ImageName)
            {
                this.imageName = ImageName;
            }
        
            public String getThumbnailName()
            {
                return this.thumbnailName;
            }
            
            public void setThumbnailName(String ThumbnailName)
            {
                this.thumbnailName = ThumbnailName;
            }
        
            public String getAlternateText()
            {
                return this.alternateText;
            }
            
            public void setAlternateText(String AlternateText)
            {
                this.alternateText = AlternateText;
            }
        
            public Integer getRank()
            {
                return this.rank;
            }
            
            public void setRank(Integer Rank)
            {
                this.rank = Rank;
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

