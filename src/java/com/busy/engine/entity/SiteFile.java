






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class SiteFile extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SITE_FILE_ID = "SiteFileId";
        public static final String PROP_FILE_NAME = "FileName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_LABEL = "Label";
        

        private Integer siteFileId;
                
        private String fileName;
                
        private String description;
                
        private String label;
                
                 
        ArrayList<FileFolder> fileFolderList; 
        
        

        public SiteFile()
        {
            this.siteFileId = 0; 
       this.fileName = ""; 
       this.description = ""; 
       this.label = ""; 
        
       fileFolderList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return siteFileId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("siteFileId", siteFileId == null ? 0 : siteFileId);
                
            builder.add("fileName", fileName == null ? "" : fileName);
                
            builder.add("description", description == null ? "" : description);
                
            builder.add("label", label == null ? "" : label);
        
        
    
     
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteFile.PROP_SITE_FILE_ID) || column.equals(SiteFile.PROP_FILE_NAME) || column.equals(SiteFile.PROP_DESCRIPTION) || column.equals(SiteFile.PROP_LABEL) )
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
            if (column.equals(SiteFile.PROP_SITE_FILE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SiteFile process(ResultSet rs) throws SQLException
        {           
            rs.first();
            return new SiteFile(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
              
       public SiteFile(Integer SiteFileId, String FileName, String Description, String Label)
       {
            this.siteFileId = SiteFileId;
       this.fileName = FileName;
       this.description = Description;
       this.label = Label;
              
       fileFolderList = null; 
        
       } 
        
             
        
            public Integer getSiteFileId()
            {
                return this.siteFileId;
            }
            
            public void setSiteFileId(Integer SiteFileId)
            {
                this.siteFileId = SiteFileId;
            }
            
            
        
            public String getFileName()
            {
                return this.fileName;
            }
            
            public void setFileName(String FileName)
            {
                this.fileName = FileName;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
            
            
        
            public String getLabel()
            {
                return this.label;
            }
            
            public void setLabel(String Label)
            {
                this.label = Label;
            }
            
            
         
        
        
        public ArrayList<FileFolder> getFileFolderList()
            {
                return this.fileFolderList;
            }
            
            public void setFileFolderList(ArrayList<FileFolder> fileFolderList)
            {
                this.fileFolderList = fileFolderList;
            }
        
            
    }

