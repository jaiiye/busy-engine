











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class ImageType implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_IMAGE_TYPE_ID = "ImageTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        public static final String PROP_DESCRIPTION = "Description";
        

        private Integer imageTypeId;
                
        private String typeName;
                
        private String description;
                
                 
        ArrayList<SiteImage> siteImageList; 
        
        

        public ImageType()
        {
            this.imageTypeId = 0; 
       this.typeName = ""; 
       this.description = ""; 
        
       siteImageList = null; 
        
       }
        
        public ImageType(Integer ImageTypeId, String TypeName, String Description)
        {
            this.imageTypeId = ImageTypeId;
       this.typeName = TypeName;
       this.description = Description;
              
       siteImageList = null; 
        
       } 
        
             
        
            public Integer getImageTypeId()
            {
                return this.imageTypeId;
            }
            
            public void setImageTypeId(Integer ImageTypeId)
            {
                this.imageTypeId = ImageTypeId;
            }
            
            
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
            
            
         
        
        
            public ArrayList<SiteImage> getSiteImageList()
            {
                return this.siteImageList;
            }
            
            public void setSiteImageList(ArrayList<SiteImage> siteImageList)
            {
                this.siteImageList = siteImageList;
            }
        
            
    }

