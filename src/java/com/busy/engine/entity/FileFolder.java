






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class FileFolder extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_FILE_FOLDER_ID = "FileFolderId";
        public static final String PROP_SITE_FILE_ID = "SiteFileId";
        public static final String PROP_SITE_FOLDER_ID = "SiteFolderId";
        

        private Integer fileFolderId;
                
        private Integer siteFileId;
        private SiteFile siteFile;        
        private Integer siteFolderId;
        private SiteFolder siteFolder;        
                 
        
        

        public FileFolder()
        {
            this.fileFolderId = 0; 
       this.siteFileId = 0; 
       this.siteFolderId = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return fileFolderId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("fileFolderId", fileFolderId == null ? 0 : fileFolderId);
                
            builder.add("siteFileId", siteFileId == null ? 0 : siteFileId);
                
            builder.add("siteFolderId", siteFolderId == null ? 0 : siteFolderId);
        
        
    
     if(siteFile != null) siteFile.addJson(builder);
        
     if(siteFolder != null) siteFolder.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(FileFolder.PROP_FILE_FOLDER_ID) || column.equals(FileFolder.PROP_SITE_FILE_ID) || column.equals(FileFolder.PROP_SITE_FOLDER_ID) )
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
            if (column.equals(FileFolder.PROP_FILE_FOLDER_ID) || column.equals(FileFolder.PROP_SITE_FILE_ID) || column.equals(FileFolder.PROP_SITE_FOLDER_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static FileFolder process(ResultSet rs) throws SQLException
        {           
            rs.first();
            return new FileFolder(rs.getInt(1), rs.getInt(2), rs.getInt(3));
        }
              
       public FileFolder(Integer FileFolderId, Integer SiteFileId, Integer SiteFolderId)
       {
            this.fileFolderId = FileFolderId;
       this.siteFileId = SiteFileId;
       this.siteFolderId = SiteFolderId;
              
       
       } 
        
             
        
            public Integer getFileFolderId()
            {
                return this.fileFolderId;
            }
            
            public void setFileFolderId(Integer FileFolderId)
            {
                this.fileFolderId = FileFolderId;
            }
            
            
        
            public Integer getSiteFileId()
            {
                return this.siteFileId;
            }
            
            public void setSiteFileId(Integer SiteFileId)
            {
                this.siteFileId = SiteFileId;
            }
            
            
                   
            public SiteFile getSiteFile()
                {
                    return this.siteFile;
                }

                public void setSiteFile(SiteFile siteFile)
                {
                    this.siteFile = siteFile;
                }
                   
            
        
            public Integer getSiteFolderId()
            {
                return this.siteFolderId;
            }
            
            public void setSiteFolderId(Integer SiteFolderId)
            {
                this.siteFolderId = SiteFolderId;
            }
            
            
                   
            public SiteFolder getSiteFolder()
                {
                    return this.siteFolder;
                }

                public void setSiteFolder(SiteFolder siteFolder)
                {
                    this.siteFolder = siteFolder;
                }
                   
            
         
        
        
            
    }

