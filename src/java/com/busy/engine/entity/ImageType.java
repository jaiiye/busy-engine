






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class ImageType extends AbstractEntity implements EntityItem<Integer>
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
       
       @Override
       public Integer getId()
       {
            
            return imageTypeId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("imageTypeId", imageTypeId == null ? 0 : imageTypeId);
                
            builder.add("typeName", typeName == null ? "" : typeName);
                
            builder.add("description", description == null ? "" : description);
        
        
    
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ImageType.PROP_IMAGE_TYPE_ID) || column.equals(ImageType.PROP_TYPE_NAME) || column.equals(ImageType.PROP_DESCRIPTION) )
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
            if (column.equals(ImageType.PROP_IMAGE_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ImageType process(ResultSet rs) throws SQLException
        {           
            rs.first();
            return new ImageType(rs.getInt(1), rs.getString(2), rs.getString(3));
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

