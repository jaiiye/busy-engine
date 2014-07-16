


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
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
        

        public ImageType()
        {
            this.imageTypeId = 0; 
       this.typeName = ""; 
       this.description = ""; 
        }
        
        public ImageType(Integer ImageTypeId, String TypeName, String Description)
        {
            this.imageTypeId = ImageTypeId;
       this.typeName = TypeName;
       this.description = Description;
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
           
            
    }

